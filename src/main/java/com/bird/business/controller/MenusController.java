package com.bird.business.controller;

import com.bird.business.annotation.SysLog;
import com.bird.business.domain.Menus;
import com.bird.business.domain.TbMenus;
import com.bird.business.service.IAdminManagerService;
import com.bird.business.service.ITbMenusService;
import com.bird.business.utils.ResultUtil;
import com.bird.business.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenusController {

    @Autowired
    private IAdminManagerService adminManagerService;

    @Autowired
    private ITbMenusService tbMenusService;

    /**
     * 获取菜单导航列表(左侧导航栏)
     *
     * @return
     */
    @RequestMapping("/getMenusList")
    @ResponseBody
    public ResultUtil getMenusList() {
        String userName = (String) ShiroUtils.getSubject().getPrincipal();
        ResultUtil resultUtil = new ResultUtil();
        try {
            //获取用户菜单
            List<Menus> menusList = adminManagerService.getMenusList(userName);
            resultUtil.setCode(0);
            resultUtil.setData(menusList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultUtil;
    }

    /**
     * 获取菜单导航列表(编辑角色用)
     *
     * @return
     */
    @RequiresPermissions(value = {"roles:add", "roles:update"}, logical = Logical.OR)
    @RequestMapping("/getTreeMenusList")
    @ResponseBody
    public ResultUtil getTreeMenusList(@RequestParam(value="roleId", defaultValue="-1") Long roleId) {
        //获取树形菜单
        List<TbMenus> menusList = adminManagerService.getTreeMenusList(roleId);
        return ResultUtil.ok(null,menusList);
    }

    /**
     * 获取菜单导航列表(和上述接口一致,仅返回数据格式不一致,用于树形表格-菜单管理列表)
     *
     * @return
     */
    @SysLog(value = "查看菜单列表")
    @RequiresPermissions("menus:list")
    @RequestMapping("/getTreeMenusListData")
    @ResponseBody
    public List<TbMenus> getTreeMenusListData(@RequestParam(value="roleId", defaultValue="-1") Long roleId) {
        //获取树形菜单
        List<TbMenus> menusList = adminManagerService.getTreeMenusList(roleId);
        return menusList;
    }

    /**
     * 删除菜单(级联删除菜单)
     * @param menuId
     */
    @SysLog(value = "删除菜单")
    @RequiresPermissions("menus:delete")
    @RequestMapping(value = "/deleteMenus",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil deleteUser(@RequestParam Long menuId){
        try{
            tbMenusService.deleteMenus(menuId);
            return ResultUtil.ok("刪除成功");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 查找菜单
     * @param menuId
     */
    @SysLog(value = "查看菜单")
    @RequiresPermissions("menus:update")
    @RequestMapping(value = "/getMenus",method= RequestMethod.GET)
    @ResponseBody
    public ResultUtil getMenus(@RequestParam Long menuId){
        try {
            TbMenus tbMenus = tbMenusService.selectByPrimaryKey(menuId);
            return ResultUtil.ok(tbMenus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }

    /**
     * 新增/修改菜单
     * @param tbMenus
     */
    @SysLog(value = "新增/修改菜单")
    @RequiresPermissions(value = {"menus:add", "menus:update"}, logical = Logical.OR)
    @RequestMapping(value = "/addOrModifyMenus",method= RequestMethod.POST)
    @ResponseBody
    public ResultUtil addOrModifyMenus(@RequestBody TbMenus tbMenus){
        try{
            Long menuId = tbMenus.getMenuId();
            if (menuId == null){
                tbMenusService.insert(tbMenus);
            } else {
                tbMenusService.updateByPrimaryKeySelective(tbMenus);
            }
            return ResultUtil.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error();
    }
}