package com.bird.business.controller;

import com.bird.business.domain.TbUser;
import com.bird.business.domain.TbUserExample;
import com.bird.business.service.ITbUserService;
import com.bird.business.utils.ResultUtil;
import com.bird.business.utils.ShiroUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private ITbUserService tbUserService;

    /**
     * 查询用户列表
     * @param paramMap
     * @param request
     * @return
     */
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
}