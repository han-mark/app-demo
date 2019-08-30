<%@ page language="java" contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8"%>
<%@ include file="/pages/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>后台权限管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="${ctx}/css/menus.css">
    <link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx}/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/js/util.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- 表单 -->
<form class="layui-form" id="form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">菜单ID</label>
        <div class="layui-input-block">
            <input type="text" id="menuId" name="menuId" style="width:100%" disabled="disabled" placeholder="系统自动生成" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input type="text" id="title" name="title" style="width:100%" required  lay-verify="required" lay-reqtext="名称不能为空" placeholder="请输入名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">图标</label>
        <div class="layui-input-block">
            <input type="text" id="icon" name="icon" style="width:100%" lay-verify="title" placeholder="请输入图标（仅新增目录和页面时输入）" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">默认页面</label>
        <div class="layui-input-block">
            <input type="text" id="href" name="href" style="width:100%" lay-verify="title" placeholder="请输入链接（仅新增页面时输入）" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">权限</label>
        <div class="layui-input-block">
            <input type="text" id="perms" name="perms" style="width:100%"  lay-verify="title" placeholder="请输入权限（仅新增权限时输入）" autocomplete="off" class="layui-input">
        </div>
    </div>
    <input id="spread" name="spread" type="hidden" value="false" />
    <div class="layui-form-item">
        <label class="layui-form-label">父级ID</label>
        <div class="layui-input-block">
            <input type="text" id="parentId" name="parentId" style="width:100%" value="0" disabled="disabled"  lay-verify="title" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" id="sorting" name="sorting" style="width:100%"  lay-verify="title" placeholder="请输入序号（仅新增目录和页面时输入）" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <!-- button自带提交属性，影响ajax提交，增加type="button" -->
            <button id="submit" type="button" class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx }/js/updatemenus.js"></script>
</body>
</html>