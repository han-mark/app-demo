layui.use(['table','jquery','form'], function(){
    var table = layui.table;
    var form = layui.form;
    var $ = layui.$;//重点处
    //第一个实例
    table.render({
        elem: '#table-user' //对应表格元素
        ,id: 'table-user'
        ,height: 558
        ,url: ctx + '/getUserList' //数据接口,默认会带？page=1,limit=10,返回的数据有格式要求
        ,method: 'post'
        ,even: true //开启隔行背景
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: '用户ID', width:150, sort: true, fixed: 'left'}
            ,{field: 'username', title: '用户', width:150}
            ,{field: 'fullname', title: '姓名', width:150, sort: true}
            ,{field: 'eMail', title: '邮箱', width:150}
            ,{field: 'sex', title: '性别', width:150, templet: '#sexTxt'}
            ,{field: 'birthday', title: '生日', width:150}
            ,{field: 'address', title: '地址', width:150}
            ,{field: 'phone', title: '电话', width:150}
            ,{field: 'roleName', title: '角色', width:150}
            ,{fixed: 'right', title: '操作', width:200, align:'center', toolbar: '#barUser'} //这里的toolbar绑定工具条
        ]]
    });

    //监听行双击事件
    table.on('rowDouble(table-user)', function(obj){
        //obj 同上
        //弹出层处理
        layer.open({
            type:2, //2表示frame
            title:'用户信息',
            area:['100%','100%'],
            shadeClose:false,
            closeBtn:1,
            content:ctx + '/pages/updateuser.jsp?id='+obj.data.id + '&flag=1', //flag有值为查看详情页
            end:function(){
                // table.reload('table-user'); //对应table.render中定义的id
            }
        });
    });

    //监听工具条
    table.on('tool(table-user)', function(obj){ //注：tool是工具条事件名，table-user是table lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'detail'){ //查看
            //弹出层处理
            layer.open({
                type:2, //2表示frame
                title:'用户信息',
                area:['100%','100%'],
                shadeClose:false,
                closeBtn:1,
                content:ctx + '/pages/updateuser.jsp?id='+data.id + '&flag=1', //flag有值为查看详情页
                end:function(){
                    // table.reload('table-user'); //对应table.render中定义的id
                }
            });
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除吗', function(index){
                layer.close(index);
                //向服务端发送删除指令
                console.log(data);
                $.ajax({
                    type : 'GET',
                    url : ctx + '/deleteUser?id=' + data.id,
                    dataType : 'json',
                    success : function(e){
                        //e为后台返回的数据
                        if(e.code == 0){
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.msg(e.msg);
                        }else{
                            layer.msg(e.msg);
                        }
                    },
                    error : function(){
                        layer.msg("服务器开小差了，请稍后再试...");
                    }
                });
            });
        } else if(layEvent === 'edit'){ //编辑
            //弹出层处理
            layer.open({
                type:2, //2表示frame
                title:'用户信息',
                area:['100%','100%'],
                shadeClose:false,
                closeBtn:1,
                content:ctx + '/pages/updateuser.jsp?id='+data.id,
                end:function(){
                    table.reload('table-user'); //对应table.render中定义的id
                }
            });
        }
    });

    //新增按钮点击事件
    $("#add").bind("click",function(){
        //弹出层处理
        layer.open({
            type:2, //2表示frame
            title:'用户信息',
            area:['100%','100%'],
            shadeClose:false,
            closeBtn:1,
            content:ctx + '/pages/updateuser.jsp',
            end:function(){
                table.reload('table-user'); //对应table.render中定义的id
            }
        });
    });
});