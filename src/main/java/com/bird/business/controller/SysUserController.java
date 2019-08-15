package com.bird.business.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bird.business.domain.SysUser;
import com.bird.business.service.ISysUserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class SysUserController {
    @Resource  
    private ISysUserService iSysUserService;

    /**
     * 查询用户列表
     * @param paramMap
     * @param request
     * @return
     */
    @RequiresPermissions("stu:list")
    @RequestMapping(value = "/findAll",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserList(@RequestParam Map<String,Object> paramMap, HttpServletRequest request){
        //获取分页参数
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        //设置分页
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        //获取分页数据
        List<SysUser> users = this.iSysUserService.getUserList(paramMap);

        PageInfo<SysUser> pageInfo=new PageInfo<SysUser>(users);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",pageInfo.getTotal());
        resultMap.put("data",users);
        return resultMap;
    }

    /**
     * 删除用户
     * @param paramMap
     */
    @RequiresPermissions("stu:delete")
    @RequestMapping(value = "/deleteUserByUuid",method= RequestMethod.POST)
    @ResponseBody
    public String deleteUserByUuid(@RequestParam Map<String,Object> paramMap){
        String flag = "0";
        try{
            iSysUserService.deleteUserByUuid(paramMap);
            flag = "1";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查找用户
     * @param paramMap
     */
    @RequiresPermissions("stu:update")
    @RequestMapping(value = "/getUserByUuid",method= RequestMethod.POST)
    @ResponseBody
    public SysUser getUserByUuid(@RequestParam Map<String,Object> paramMap){
        SysUser user = iSysUserService.getUserByUuid(paramMap);
        return user;
    }

    /**
     * 新增/修改用户
     * @param paramMap
     */
    @RequiresPermissions(value = {"stu:add", "stu:update"}, logical = Logical.OR)
    @RequestMapping(value = "/addOrUpdateUser",method= RequestMethod.POST)
    @ResponseBody
    public String addOrUpdateUser(@RequestParam Map<String,Object> paramMap){
        String flag = "0";
        try{
            String uuid = (String)paramMap.get("uuid");
            if (uuid == null || uuid == ""){
                uuid = UUID.randomUUID().toString();
                paramMap.put("uuid",uuid);
                iSysUserService.addUser(paramMap);
            } else {
                iSysUserService.updateUser(paramMap);
            }
            flag = "1";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}