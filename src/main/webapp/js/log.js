layui.use(['table','jquery','form','laydate'], function(){
    var table = layui.table;
    var form = layui.form;
    var $ = layui.$;//重点处
    //渲染时间控件
    var laydate = layui.laydate;
    //执行一个laydate实例
    var start = laydate.render({
        elem: '#starttime',
        type: 'datetime',
        max: new Date().valueOf(),
        btns: ['clear', 'confirm'],
        done: function(value, date){
            endMax = end.config.max;
            end.config.min = date;
            end.config.min.month = date.month -1;
        }
    });
    var end = laydate.render({
        elem: '#endtime',
        type: 'datetime',
        max: new Date().valueOf(),
        done: function(value, date){
            if($.trim(value) == ''){
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth()+1, 'year': curDate.getFullYear()};
            }
            start.config.max = date;
            start.config.max.month = date.month -1;
        }
    })

    //第一个实例
    table.render({
        elem: '#table-log' //对应表格元素
        ,id: 'table-log'
        ,height: 558
        ,url: ctx + '/getLogList' //数据接口,默认会带？page=1,limit=10,返回的数据有格式要求
        ,method: 'post'
        ,even: true //开启隔行背景
        ,page: true //开启分页
        ,cols: [[ //表头
             {field: 'id', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'operation', title: '操作'}
            ,{field: 'method', title: '请求路径', sort: true}
            ,{field: 'params', title: '请求参数'}
            ,{field: 'ip', title: 'IP'}
            ,{field: 'username', title: '操作人'}
            ,{field: 'createTime', title: '操作时间'}
        ]]
    });

    //搜索功能
    $('#search-button').on('click', function(){
        //执行重载
        table.reload('table-log', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                'username': $('#username').val(),
                'starttime':$('#starttime').val(),
                'endtime':$('#endtime').val()
            }
        });
    });
});