package com.bird.business.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bird.business.domain.SysUser;
import com.bird.business.service.ISysUserService;

@Controller
@RequestMapping("/student")
public class SysUserController {
    @Resource  
    private ISysUserService iSysUserService;  
      
    @RequestMapping("/studentView")
    public String toIndex(HttpServletRequest request, Model model){  
        String username = request.getParameter("username");  
        SysUser user = this.iSysUserService.getSysUserById(username);  
        model.addAttribute("user", user);
        return "studentView";  
    } 
}