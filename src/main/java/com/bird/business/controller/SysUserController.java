package com.bird.business.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/user")
public class SysUserController {
    @Resource  
    private ISysUserService iSysUserService;

    /**
     * 查询用户列表
     * @param paramMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAll",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserList(@RequestParam Map<String,Object> paramMap, HttpServletRequest request){
        //获取分页参数
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        paramMap.put("page",page);
        paramMap.put("limit",limit);

        List<SysUser> users = this.iSysUserService.getUserList(paramMap);
        String count = iSysUserService.getUserListForCount(paramMap);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",Integer.parseInt(count));
        resultMap.put("data",users);
        return resultMap;
    }

    /**
     * 删除用户
     * @param paramMap
     */
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
    @RequestMapping(value = "/addOrUpdateUser",method= RequestMethod.POST)
    @ResponseBody
    public String addOrUpdateUser(@RequestParam Map<String,Object> paramMap){
        String flag = "0";
        try{
            String uuid = (String)paramMap.get("uuid");
            if (uuid == null || uuid == ""){
                uuid = UUID.randomUUID().toString();
            }
            paramMap.put("uuid",uuid);
            iSysUserService.addOrUpdateUser(paramMap);
            flag = "1";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}