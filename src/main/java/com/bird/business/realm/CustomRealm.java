package com.bird.business.realm;

import com.bird.business.dao.TbUserMapper;
import com.bird.business.dao.AdminManagerDao;
import com.bird.business.domain.TbUser;
import com.bird.business.domain.TbUserExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    /**
     * 注入mapper层，注入service层会报错，过滤器在service层实例之前
     */
    @Autowired
    private TbUserMapper tbUserService;

    @Autowired
    private AdminManagerDao userManagerService;

    @Override
    public String getName() {
        return "CustomRealm";
    }

    /**
     * 认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();
        //从数据库获取角色信息
        Set<String> roles = userManagerService.getRolesByUsername(username);
        Set<String> permissions = null;
        //获取权限信息
        if (roles != null && roles.size() != 0){
            permissions = userManagerService.getPermsByRoles(username);
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    /**
     * 授权
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //从主体获取提交的认证信息
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        //从数据库验证
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserService.selectByExample(tbUserExample);

        // 账号不存在
        if (tbUsers == null || tbUsers.size() == 0) {
            throw new UnknownAccountException("账号不存在!");
        }
        //密码MD5加密,使用用户名加盐
        password = new Md5Hash(password,username).toString();
        // 密码错误
        if (!password.equals(tbUsers.get(0).getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确!");
        }

        // 账号未分配角色
        if (tbUsers.get(0).getRoleId() == null || tbUsers.get(0).getRoleId() == 0) {
            throw new UnknownAccountException("账号未分配角色!");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, tbUsers.get(0).getPassword(), getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return simpleAuthenticationInfo;
    }

    public static void main(String args[]) {
        //Md5加密
//        Md5Hash md5Hash = new Md5Hash("admin");
        //Md5加密加盐
        Md5Hash md5Hash = new Md5Hash("123456", "cuihui");
        System.out.println(md5Hash.toString());
    }
}