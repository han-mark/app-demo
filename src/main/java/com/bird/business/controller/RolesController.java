package com.bird.business.controller;

import com.bird.business.annotation.SysLog;
import com.bird.business.domain.TbRoles;
import com.bird.business.domain.TbRolesExample;
import com.bird.business.service.ITbRolesService;
import com.bird.business.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RolesController {
    @Resource
    private ITbRolesService tbRolesService;

    /**
     * 查询角色列表
     * @return
     */
    @SysLog(value = "查看角色列表")
//    @RequiresPermissions("roles:list")  //此处不加权限,用户更改基本信息用
    @RequestMapping(value = "/getRolesList")
    @ResponseBody
    public ResultUtil getRolesList(HttpServletRequest request){
        //获取分页参数
        String page = StringUtils.isEmpty(request.getParameter("page"))? "1" : request.getParameter("page");
        String limit = StringUtils.isEmpty(request.getParameter("limit"))? "999" : request.getParameter("limit");

        //设置分页
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        //获取分页数据
        TbRolesExample tbRolesExample= new TbRolesExample();
        List<TbRoles> tbRoleses = this.tbRolesService.selectByExample(tbRolesExample);
        PageInfo<TbRoles> pageInfo=new PageInfo<>(tbRoleses);
        return ResultUtil.ok(pageInfo.getTotal(),tbRoleses);
    }

    /**
     * 删除角色
     * @param roleId
     */
    @SysLog(value = "删除角色")
    @RequiresPermissions("roles:delete")
    @RequestMapping(value = "/deleteRoles",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil deleteUser(@RequestParam Long roleId){
        try{
            tbRolesService.deleteByPrimaryKey(roleId);
            return ResultUtil.ok("刪除成功");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 查找角色
     * @param roleId
     */
    @SysLog(value = "查看角色")
    @RequiresPermissions("roles:update")
    @RequestMapping(value = "/getRoles",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil getUser(@RequestParam Long roleId){
        try {
            TbRoles tbRoles = tbRolesService.selectByPrimaryKey(roleId);
            return ResultUtil.ok(tbRoles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 新增/修改角色
     * @param tbRoles
     */
    @SysLog(value = "新增/修改角色")
    @RequiresPermissions(value = {"roles:add", "roles:update"}, logical = Logical.OR)
    @RequestMapping(value = "/addOrModifyRoles",method= RequestMethod.POST)
    @ResponseBody
    public ResultUtil addOrModifyUser(@RequestBody TbRoles tbRoles){
        try{
            Long roleId = tbRoles.getRoleId();
            if (roleId == null){
                tbRolesService.insert(tbRoles);

                //获取新增角色的主键号
                TbRolesExample tbRolesExample = new TbRolesExample();
                tbRolesExample.or().andRoleNameEqualTo(tbRoles.getRoleName());
                List<TbRoles> tbRoles1 = tbRolesService.selectByExample(tbRolesExample);
                Long roleId1 = tbRoles1.get(0).getRoleId();
                //perms中的roleId使用的占位-1
                tbRolesService.addRolesMenus(roleId1, tbRoles.getPerms());
            } else {
                tbRolesService.updateByPrimaryKeySelective(tbRoles);
                tbRolesService.addRolesMenus(roleId, tbRoles.getPerms());
            }
            return ResultUtil.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 校验角色名是否被占用
     * @param roleName
     */
    @RequiresPermissions(value = {"roles:add", "roles:update"}, logical = Logical.OR)
    @RequestMapping(value = "/checkRolesName/{roleName}",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil checkUserName(@PathVariable("roleName") String roleName){
        TbRolesExample tbRolesExample= new TbRolesExample();
        tbRolesExample.or().andRoleNameEqualTo(roleName);
        List<TbRoles> tbRoles = tbRolesService.selectByExample(tbRolesExample);
        if (CollectionUtils.isEmpty(tbRoles)) {
            return new ResultUtil(0);
        }
        return ResultUtil.error("用户名已被占用");
    }
}