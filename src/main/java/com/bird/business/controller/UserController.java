package com.bird.business.controller;

import com.bird.business.annotation.SysLog;
import com.bird.business.domain.TbUser;
import com.bird.business.domain.TbUserExample;
import com.bird.business.service.ITbUserService;
import com.bird.business.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private ITbUserService tbUserService;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private MailUtil mailUtil;

    /**
     * 查询用户列表
     * @param paramMap
     * @param request
     * @return
     */
    @SysLog(value = "查看用户列表")
    @RequiresPermissions("user:list")
    @RequestMapping(value = "/getUserList",method= RequestMethod.POST)
    @ResponseBody
    public ResultUtil getUserList(@RequestParam Map<String,Object> paramMap, HttpServletRequest request){
        //获取分页参数
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        //设置分页
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        //获取分页数据
        TbUserExample tbUserExample = new TbUserExample();
        List<TbUser> users = this.tbUserService.selectByExample(tbUserExample);
        PageInfo<TbUser> pageInfo=new PageInfo<>(users);
        return ResultUtil.ok(pageInfo.getTotal(),users);
    }

    /**
     * 删除用户
     * @param id
     */
    @SysLog(value = "删除用户")
    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/deleteUser",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil deleteUser(@RequestParam Long id){
        try{
            tbUserService.deleteByPrimaryKey(id);
            return ResultUtil.ok("刪除成功");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 查找用户
     * @param id
     */
    @SysLog(value = "查看用户")
//    @RequiresPermissions("user:update") //此处不加权限,更改用户基本信息也需要该接口
    @RequestMapping(value = "/getUser",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil getUser(@RequestParam Long id){
        try {
            TbUser user = tbUserService.selectByPrimaryKey(id);
            return ResultUtil.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 新增/修改用户
     * @param tbUser
     */
    @SysLog(value = "新增/修改用户")
    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @RequestMapping(value = "/addOrModifyUser",method= RequestMethod.POST)
    @ResponseBody
    public ResultUtil addOrModifyUser(@RequestBody TbUser tbUser){
        try{
            Long id = tbUser.getId();
            if (id == null){
                tbUserService.insert(tbUser);
            } else {
                tbUserService.updateByPrimaryKeySelective(tbUser);
            }
            return ResultUtil.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 校验用户名是否被占用
     * @param username
     */
    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @RequestMapping(value = "/checkUserName/{username}",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil checkUserName(@PathVariable("username") String username){
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserService.selectByExample(tbUserExample);
        if (CollectionUtils.isEmpty(tbUsers)) {
            return new ResultUtil(0);
        }
        return ResultUtil.error("用户名已被占用");
    }

    /**
     * 校验邮箱是否被占用(适用于新增和修改)
     * @param username
     */
    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @RequestMapping(value = "/checkUserEmail",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil checkUserEmail(String username, String eMail){
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameNotEqualTo(username);
        }
        criteria.andEMailEqualTo(eMail);
        List<TbUser> tbUsers = tbUserService.selectByExample(tbUserExample);
        if (CollectionUtils.isEmpty(tbUsers)) {
            return new ResultUtil(0);
        }
        return ResultUtil.error("邮箱已被占用");
    }

    /**
     * 校验密码是否正确
     * @param opassword
     */
    @RequestMapping(value = "/checkUserPwd/{opassword}",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil checkUserPwd(@PathVariable("opassword") String opassword){
        String username = (String) ShiroUtils.getSubject().getPrincipal();
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserService.selectByExample(tbUserExample);
        String password = tbUsers.get(0).getPassword();
        String ipassword = new Md5Hash(opassword, username).toString();
        if (password.equals(ipassword)) {
            return ResultUtil.ok();
        }
        return ResultUtil.error("密码错误");
    }

    /**
     * 修改密码
     * @param password
     */
    @SysLog(value = "修改密码")
    @RequestMapping(value = "/modifyPassword/{password}",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil modifyPassword(@PathVariable("password") String password){
        String username = (String) ShiroUtils.getSubject().getPrincipal();
        String npassword = new Md5Hash(password, username).toString();
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andUsernameEqualTo(username);
        TbUser tbUser = tbUserService.selectByExample(tbUserExample).get(0);
        tbUser.setPassword(npassword);
        try {
            tbUserService.updateByPrimaryKey(tbUser);
            return ResultUtil.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error("修改失败");
    }

    /**
     * 按性别统计用户
     */
    @SysLog(value = "用户统计")
    @RequestMapping(value = "/getUserCount",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil getUserCount(){
        //使用生成的代码简单统计下
        List<Map<String,String>> resultList = new ArrayList<>();
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andSexEqualTo("1");
        long manCount = tbUserService.countByExample(tbUserExample);
        Map<String,String> manMap = new HashMap<String,String>();
        manMap.put("name","男");
        manMap.put("value", String.valueOf(manCount));
        resultList.add(manMap);

        TbUserExample tbUserExample1 = new TbUserExample();
        tbUserExample1.or().andSexEqualTo("2");
        long womanCount = tbUserService.countByExample(tbUserExample1);
        Map<String,String> womanMap = new HashMap<String,String>();
        womanMap.put("name","女");
        womanMap.put("value", String.valueOf(womanCount));
        resultList.add(womanMap);
        return ResultUtil.ok(resultList);
    }

    /**
     * 获取忘记密码时的验证码
     * @param email
     * @return
     */
    @SysLog(value = "获取找回密码所需的验证码")
    @RequestMapping(value = "/getForgotPwdCode", method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil getForgotPwdCode(String email){
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andEMailEqualTo(email);
        long count = tbUserService.countByExample(tbUserExample);
        if(count == 0){
            //找不到该邮箱的账号
            return ResultUtil.error("不存在的账号!");
        }else{
            try {
                //生成长度为4的随机数字
                String code = RandomStringUtil.getRandomCode(4, 0);
                //存到redis缓存中，有效时间为5分钟
                //先删除原来已存在的验证码
                jedisUtil.delete(email.getBytes());
                jedisUtil.set(email.getBytes(), code.getBytes());
                jedisUtil.expire(email.getBytes(),300);
                //发送邮件
                mailUtil.send(email, "找回密码", "验证码：" + code + "(验证码有效期为5分钟)");
                return ResultUtil.ok("获取验证码成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.ok("获取验证码失败！");
            }
        }
    }

    /**
     * 重设密码
     * @param email
     * @param code
     * @param password
     * @return
     */
    @SysLog(value = "重设密码")
    @RequestMapping("/resetPwd")
    @ResponseBody
    public ResultUtil resetPwd(String email, String code, String password){
        try {
            //先根据邮箱获取相应用户的信息
            TbUserExample tbUserExample = new TbUserExample();
            tbUserExample.or().andEMailEqualTo(email);
            List<TbUser> tbUsers = tbUserService.selectByExample(tbUserExample);
            if(CollectionUtils.isEmpty(tbUsers)){
                //找不到该邮箱的账号
                return ResultUtil.error("不存在的账号!");
            }else{
                TbUser user = tbUsers.get(0);
                //从redis缓存中获取验证码
                if(jedisUtil.exists(email.getBytes())){
                    String redisCode = new String(jedisUtil.get(email.getBytes()));
                    if(redisCode.equals(code)){
                        //验证码正确，修改密码
                        String npwd = new Md5Hash(password, user.getUsername()).toString();
                        user.setPassword(npwd);
                        tbUserService.updateByPrimaryKey(user);
                        return ResultUtil.ok("修改成功!");
                    }else{
                        return ResultUtil.error("验证码错误！");
                    }
                }else{
                    return ResultUtil.error("验证码已过期，请重新获取！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("服务器出了小差，请稍等...");
        }
    }
}