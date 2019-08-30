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

    <link rel="stylesheet" href="${ctx }/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx }/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx }/echarts/echarts.js"></script>
    <script type="text/javascript" src="${ctx }/jquery/jquery-3.4.1.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>系统基本参数</legend>
</fieldset>

<table class="layui-table" lay-even="" lay-skin="nob" style="margin-left: 10px">
    <thead>
    <tr>
        <th>当前版本</th>
        <th>V1.0</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>开发作者</td>
        <td>崔辉</td>
    </tr>
    <tr>
        <td>服务器</td>
        <td>apache-tomcat-8.0.53</td>
    </tr>
    <tr>
        <td>数据库</td>
        <td>mysql-8.0.16.0</td>
    </tr>
    <tr>
        <td>Maven</td>
        <td>apache-maven-3.6.0</td>
    </tr>
    <tr>
        <td>开发工具</td>
        <td>idea</td>
    </tr>
    </tbody>
</table>

<fieldset class="layui-elem-field layui-field-title">
    <legend>用户性别占比</legend>
</fieldset>
<div id="container" style="height: 500px; width: 1000px; margin-left: 20px"></div>
<fieldset class="layui-elem-field layui-field-title">
    <legend>更新历史记录</legend>
</fieldset>
<ul class="layui-timeline" style="margin-left: 10px">
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h4 class="layui-timeline-title">Aug 30, 2019</h4>
            <p>
                增加email找回密码功能
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h4 class="layui-timeline-title">Aug 23, 2019</h4>
            <p>
                增加多数据源(aop)
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h4 class="layui-timeline-title">Aug 22, 2019</h4>
            <p>
                增加日志管理(aop)，集成quartz框架定时清除日志
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h4 class="layui-timeline-title">Aug 15, 2019</h4>
            <p>
                集成shiro框架，控制权限，redis缓存&session共享
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h4 class="layui-timeline-title">Jul 22, 2019</h4>
            <p>
                V1.0版本发布，代码使用模板生成
            </p>
        </div>
    </li>
</ul>

<script type="text/javascript" src="${ctx }/js/home.js"></script>

</body>
</html>