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

    <link rel="stylesheet" href="${ctx }/css/log.css">
    <link rel="stylesheet" href="${ctx }/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx }/layui/layui.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<div class="top_tool">
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label" style="">操作人：</label>
        <div class="layui-input-inline">
            <input type="text" name="username" id="username" placeholder="请输入操作人" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-inline">
            <input type="text" id="starttime" name="starttime" placeholder="yyyy-MM-dd" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-input-inline">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-inline">
            <input type="text" id="endtime" name="endtime" placeholder="yyyy-MM-dd" class="layui-input">
        </div>
    </div>
    <button class="layui-btn btn_extend" data-type="reload" id="search-button">
        <i class="layui-icon">&#xe615;</i> 搜索
    </button>
</div>
<!-- 表格 -->
<table id="table-log" lay-filter="table-log"></table>

<script type="text/javascript" src="${ctx }/js/log.js"></script>
</body>
</html>