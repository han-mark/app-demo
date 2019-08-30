$(document).ready(function(){
    // 初始化内容
    var dom = $("#container").get(0);
    var myChart = echarts.init(dom);
    $.ajax({
        url: ctx + "/getUserCount",
        type: "get",
        async: "true",
        dataType: "json",
        success: function(data){
            if (data.code == 0) {
                console.log(data.data)
                option = null;
                option = {
                    title : {
                        text: '网站用户性别统计',
                        // subtext: '纯属虚构',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['男','女']
                    },
                    series : [
                        {
                            name: '性别占比',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data: data.data,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                ;
                myChart.setOption(option, true);
            }
        },
        error : function () {
        }
    });
});