package com.bird.business.controller;

import com.bird.business.annotation.DataSource;
import com.bird.business.annotation.SysLog;
import com.bird.business.domain.SysUser;
import com.bird.business.service.ISysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 该类用于测试多数据源
 */

@Controller
public class SysUserForDataSource1Controller {
    @Resource
    private ISysUserService iSysUserService;

    /**
     * 查询学生列表
     * @param paramMap
     * @param request
     * @return
     */
    @SysLog(value = "查看学生列表")
    @DataSource(dataSource = "dataSource1")
    @RequiresPermissions("stu:list1")
    @RequestMapping(value = "/findAll1",method= RequestMethod.POST)
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
     * 删除学生
     * @param paramMap
     */
    @SysLog(value = "删除学生")
    @DataSource(dataSource = "dataSource1")
    @RequiresPermissions("stu:delete1")
    @RequestMapping(value = "/deleteUserByUuid1",method= RequestMethod.POST)
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
    @SysLog(value = "查看学生")
    @DataSource(dataSource = "dataSource1")
    @RequiresPermissions("stu:update1")
    @RequestMapping(value = "/getUserByUuid1",method= RequestMethod.POST)
    @ResponseBody
    public SysUser getUserByUuid(@RequestParam Map<String,Object> paramMap){
        SysUser user = iSysUserService.getUserByUuid(paramMap);
        return user;
    }

    /**
     * 新增/修改用户
     * @param paramMap
     */
    @SysLog(value = "新增/修改学生")
    @DataSource(dataSource = "dataSource1")
    @RequiresPermissions(value = {"stu:add1", "stu:update1"}, logical = Logical.OR)
    @RequestMapping(value = "/addOrUpdateUser1",method= RequestMethod.POST)
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