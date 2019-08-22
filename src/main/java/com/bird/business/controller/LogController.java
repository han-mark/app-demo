package com.bird.business.controller;

import com.bird.business.annotation.SysLog;
import com.bird.business.domain.TbLog;
import com.bird.business.domain.TbLogExample;
import com.bird.business.service.ITbLogService;
import com.bird.business.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class LogController {
    @Resource
    private ITbLogService tbLogService;

    /**
     * 查询日志列表
     * @param paramMap
     * @param request
     * @return
     */
    @SysLog(value = "查看日志列表")
    @RequiresPermissions("log:list")
    @RequestMapping(value = "/getLogList",method= RequestMethod.POST)
    @ResponseBody
    public ResultUtil getLogList(@RequestParam Map<String,Object> paramMap, HttpServletRequest request){
        //获取分页参数
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        String username = (String) paramMap.get("username");
        String starttime = (String) paramMap.get("starttime");
        String endtime = (String) paramMap.get("endtime");
        //设置分页
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        //获取分页数据
        TbLogExample tbLogExample = new TbLogExample();
        TbLogExample.Criteria criteria = tbLogExample.or();
        try {
            if (!StringUtils.isBlank(username)) {
                criteria.andUsernameEqualTo(username);
            }
            if (!StringUtils.isBlank(starttime)) {
                criteria.andCreateTimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(starttime));
            }
            if (!StringUtils.isBlank(endtime)) {
                criteria.andCreateTimeLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime));
            }
            tbLogExample.setOrderByClause("create_time desc");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<TbLog> logs = tbLogService.selectByExample(tbLogExample);

        PageInfo<TbLog> pageInfo=new PageInfo<TbLog>(logs);
        return ResultUtil.ok(pageInfo.getTotal(),logs);
    }
}