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

    <link rel="stylesheet" href="${ctx }/css/user_test.css">
    <link rel="stylesheet" href="${ctx }/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx }/layui/layui.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- 工具条 -->
<script type="text/html" id="barDemo">
    <shiro:hasPermission name="stu:update">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="stu:update">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="stu:delete">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
</script>
<div class="top_tool">
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label" style="">姓名：</label>
        <div class="layui-input-inline">
            <input type="text" name="searchname" id="searchname" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">性别：</label>
        <div class="layui-input-inline">
            <select name="searchsex" id="searchsex" lay-verify="required" class="sex_select">
                <option value=""></option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
    </div>
    <button class="layui-btn btn_extend" data-type="reload" id="search-button">
        <i class="layui-icon">&#xe615;</i> 搜索
    </button>
    <shiro:hasPermission name="stu:add">
    <!-- 新增按钮 -->
    <button class="layui-btn btn_extend" type="button" id="add">
        <i class="layui-icon">&#xe608;</i> 添加
    </button>
    </shiro:hasPermission>
</div>
<!-- 表格 -->
<table id="demo" lay-filter="test"></table>

<script type="text/javascript" src="${ctx }/js/user_test.js"></script>
</body>
</html>