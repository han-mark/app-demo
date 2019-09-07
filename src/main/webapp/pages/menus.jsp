<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ include file="/pages/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>后台权限管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="${ctx}/css/menus.css">
    <link rel="stylesheet" href="${ctx}/layui-tree/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx}/layui-tree/layui/layui.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<div id="main">
    <shiro:hasPermission name="menus:add">
    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm add-top" title="新增一级菜单">新增菜单</button>
    </shiro:hasPermission>
    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm open-all">全部展开</button>
    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm close-all">全部关闭</button>
    <label style="margin-top: 8px;display: inline-block;color: red;">（左侧导航栏目录仅支持到二级,添加菜单时请注意）</label>
    <table class="layui-table layui-form" id="tree-table" lay-size="sm"></table>
</div>
<script type="text/javascript" src="${ctx }/js/menus.js"></script>
<script type="application/javascript">
    //因js中shiro标签不生效,故放在jsp中渲染
    function getHandleButton(item){
        return '<shiro:hasPermission name="menus:add"><a onclick="addDownMenus('+ item.menuId +')" title="添加下级菜单" href="#">添加</a></shiro:hasPermission> ' +
            '| <shiro:hasPermission name="menus:update"><a onclick="editMenus('+ item.menuId +')" title="编辑当前菜单" href="#">编辑</a></shiro:hasPermission> ' +
            '| <shiro:hasPermission name="menus:delete"><a onclick="deleteMenus('+ item.menuId +')" title="级联删除菜单" href="#">删除</a></shiro:hasPermission>';
    }
</script>
</body>
</html>