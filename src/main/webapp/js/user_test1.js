layui.use(['table','jquery','form'], function(){
    var table = layui.table;
    var form = layui.form;
    var $ = layui.$;//重点处
    //第一个实例
    table.render({
        elem: '#demo' //对应表格元素
        ,id: 'test'
        ,height: 558
        ,url: ctx + '/findAll1' //数据接口,默认会带？page=1,limit=10,返回的数据有格式要求
        ,method: 'post'
        ,even: true //开启隔行背景
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'uuid', title: '用户ID', width:300, sort: true, fixed: 'left'}
            ,{field: 'uname', title: '用户名', width:300}
            ,{field: 'sex', title: '性别', width:300, sort: true}
            ,{field: 'age', title: '年龄', width:300}
            ,{fixed: 'right', title: '操作', width:200, align:'center', toolbar: '#barDemo'} //这里的toolbar绑定工具条
        ]]
    });

    //监听行双击事件
    table.on('rowDouble(test)', function(obj){
        //obj 同上
        //弹出层处理
        layer.open({
            type:2, //2表示frame
            title:'用户信息',
            area:['500px','50%'],
            shadeClose:false,
            closeBtn:1,
            content:ctx + '/pages/updateuser_test1.jsp?uuid='+obj.data.uuid + '&flag=1', //flag有值为查看详情页
            end:function(){
                // table.reload('test'); //对应table.render中定义的id
            }
        });
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'detail'){ //查看
            //弹出层处理
            layer.open({
                type:2, //2表示frame
                title:'用户信息',
                area:['500px','50%'],
                shadeClose:false,
                closeBtn:1,
                content:ctx + '/pages/updateuser_test1.jsp?uuid='+data.uuid + '&flag=1', //flag有值为查看详情页
                end:function(){
                    // table.reload('test'); //对应table.render中定义的id
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
                    url : ctx + '/deleteUserByUuid1',
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
            //弹出层处理
            layer.open({
                type:2, //2表示frame
                title:'用户信息',
                area:['500px','50%'],
                shadeClose:false,
                closeBtn:1,
                content:ctx + '/pages/updateuser_test1.jsp?uuid='+data.uuid,
                end:function(){
                    table.reload('test'); //对应table.render中定义的id
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
            area:['500px','50%'],
            shadeClose:false,
            closeBtn:1,
            content:ctx + '/pages/updateuser_test1.jsp',
            end:function(){
                table.reload('test'); //对应table.render中定义的id
            }
        });
    });

    //搜索功能
    $('#search-button').on('click', function(){
        //执行重载
        table.reload('test', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                'uname': $('#searchname').val(),
                'sex':$('#searchsex option:selected').val()
            }
        });
    });
});