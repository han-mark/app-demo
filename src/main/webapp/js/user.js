layui.use(['table','jquery','form'], function(){
    var table = layui.table;
    var form = layui.form;
    var $ = layui.$;//重点处
    //第一个实例
    table.render({
        elem: '#demo'
        ,height: 470
        ,url: $("#PageContext").val() + '/user/findAll' //数据接口,默认会带？page=1,limit=10,返回的数据有格式要求
        ,method: 'post'
        ,even: true //开启隔行背景
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'uuid', title: '用户ID', width:300, sort: true, fixed: 'left'}
            ,{field: 'uname', title: '用户名', width:300}
            ,{field: 'sex', title: '性别', width:300, sort: true}
            ,{field: 'age', title: '年龄', width:300}
            ,{fixed: 'right', title: '操作', width:300, align:'center', toolbar: '#barDemo'} //这里的toolbar绑定工具条
        ]]
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'detail'){ //查看
            //do somehing
            $.ajax({
                type : 'POST',
                url : $("#PageContext").val() + '/user/getUserByUuid',
                dataType : 'json',
                data : {
                    'uuid' : data.uuid
                },
                success : function(e){
                    //e为后台返回的数据
                    $("#form").css({
                        "display" : "block"
                    });
                    $("#submit").hide();
                    $("#addsubmit").hide();
                    $("#reset").hide();
                    $("#confim").show();
                    $("#uname").attr("disabled","disabled");
                    $("#age").attr("disabled","disabled");
                    $("input[name='sex']").attr("disabled",true);
                    $("input[name='sex']").attr("disabled","disabled");
                    $("#uuid").val(e.uuid);
                    $("#uname").val(e.uname);
                    $("#age").val(e.age);
                    var selects = document.getElementsByName("sex");
                    for (var i=0; i<selects.length; i++){
                        if (selects[i].value == e.sex) {
                            selects[i].checked= true;
                            break;
                        }
                    }
                },
                error : function(){
                    layer.msg("服务器开小差了，请稍后再试...");
                }
            });
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除吗', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
                console.log(data);
                $.ajax({
                    type : 'POST',
                    url : $("#PageContext").val() + '/user/deleteUserByUuid',
                    dataType : 'json',
                    data : {
                        'uuid' : data.uuid
                    },
                    success : function(e){
                        //e为后台返回的数据
                        if(e == "1"){
                            layer.msg("用户删除成功");
                        }else{
                            layer.msg("用户删除失败，请稍后再试...");
                        }
                    },
                    error : function(){
                        layer.msg("服务器开小差了，请稍后再试...");
                    }
                });
            });
        } else if(layEvent === 'edit'){ //编辑
            $("#form").css({
                "display" : "block"
            });
            $("#confirm").hide();
            $("#addsubmit").hide();
            //do something
            $.ajax({
                type : 'POST',
                url : $("#PageContext").val() + '/user/getUserByUuid',
                dataType : 'json',
                data : {
                    'uuid' : data.uuid
                },
                success : function(e){
                    //e为后台返回的数据
                    $("#uuid").val(e.uuid);
                    $("#uname").val(e.uname);
                    $("#age").val(e.age);
                    var selects = document.getElementsByName("sex");
                    for (var i=0; i<selects.length; i++){
                        if (selects[i].value == e.sex) {
                            selects[i].checked= true;
                            break;
                        }
                    }
                },
                error : function(){
                    layer.msg("服务器开小差了，请稍后再试...");
                }
            });
            //监听提交
            form.on('submit(formDemo)', function(data){
                var paramData = {
                    "uuid" : data.field.uuid,
                    "uname" : data.field.uname,
                    "sex" : data.field.sex,
                    "age" : data.field.age
                };
                $.ajax({
                    type : 'POST',
                    url : $("#PageContext").val() + '/user/addOrUpdateUser',
                    dataType : 'json',
                    data : paramData,
                    success : function(e){
                        //e为后台返回的数据
                        if(e == "1"){
                            $("#form").css({
                                "display" : "none"
                            });
                            layer.msg("修改成功");
                        }else{
                            layer.msg("修改失败");
                        }
                    },
                    error : function(){
                        layer.msg("服务器开小差了，请稍后再试...");
                    }
                });
                //同步更新缓存对应的值
                obj.update({
                    uuid: data.field.uuid
                    ,uname: data.field.uname
                    ,age: data.field.age
                    ,sex: data.field.sex
                });
            });
        }
    });

    $("#add").bind("click",function(){
        $("#form").css({
            "display" : "block"
        });
        $("#confirm").hide();
        $("#submit").hide();
    });

    //使用了一个单独的新增按钮
    $("#addsubmit").bind("click",function(){
        var sex = "";
        var selects = document.getElementsByName("sex");
        for (var i=0; i<selects.length; i++){
            if (selects[i].checked) {
                sex = selects[i].value;
                break;
            }
        }
        layer.msg("开始处理");
        var paramData = {
            "uname" : $("#uname").val(),
            "age" : $("#age").val(),
            "sex" : sex
        };
        $.ajax({
            type : 'POST',
            url : $("#PageContext").val() + '/user/addOrUpdateUser',
            dataType : 'json',
            data : paramData,
            success : function(e){
                //e为后台返回的数据
                if(e == "1"){
                    $("#form").css({
                        "display" : "none"
                    });
                    layer.msg("新增成功");
                }else{
                    layer.msg("新增失败");
                }
            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    });
});