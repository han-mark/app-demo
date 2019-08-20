package com.bird.business.controller;

import com.bird.business.domain.TbUser;
import com.bird.business.domain.TbUserExample;
import com.bird.business.service.ITbUserService;
import com.bird.business.utils.ResultUtil;
import com.bird.business.utils.ShiroUtils;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;

@Controller
public class IndexController {

    //注入前需要实例化DefaultKaptcha
    @Autowired
    private Producer captchaProducer = null;

    @Autowired
    private ITbUserService tbUserService;

    /**
     * 管理员登陆
     *
     * @param req
     * @param username
     * @param password
     * @param vcode
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultUtil login(HttpServletRequest req, String username, String password, String vcode, String rememberMe) {
        if(StringUtils.isEmpty(username)|| StringUtils.isEmpty(password)||StringUtils.isEmpty(vcode)){
            return ResultUtil.error("参数不能为空");
        }
        if(!vcode.toLowerCase().equals(((String)req.getSession().getAttribute("kaptcha")).toLowerCase())){
            return ResultUtil.error("验证码不正确");
        }
        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //是否启动cookie
            if (rememberMe != null){
                token.setRememberMe(true);
            }
            subject.login(token);
        }catch (UnknownAccountException e) {
            return ResultUtil.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return ResultUtil.error(e.getMessage());
        }catch (LockedAccountException e) {
            return ResultUtil.error(e.getMessage());
        }catch (AuthenticationException e) {
            return ResultUtil.error("账户验证失败");
        }
        return ResultUtil.ok();
    }

    /**
     * 登陆成功跳转
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request, Model model){
        //获取当前登录信息,并放入request作用域中
        String username = (String) ShiroUtils.getSubject().getPrincipal();
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserService.selectByExample(tbUserExample);
        request.setAttribute("user",tbUsers.get(0));
        return "index";
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value="/loginOut")
    public String loginOut(){
        ShiroUtils.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 验证码
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/vcode")
    public void vcode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取验证码
        String text = captchaProducer.createText();
        //获取验证码图片
        BufferedImage image = captchaProducer.createImage(text);
        //验证码保存到session中
        req.getSession().setAttribute("kaptcha",text);
        // 把图片写到指定流中
        ImageIO.write(image, "JPEG", resp.getOutputStream());
    }
}