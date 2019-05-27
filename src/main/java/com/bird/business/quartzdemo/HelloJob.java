package com.bird.business.quartzdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.TriggerKey;

@PersistJobDataAfterExecution   //多次调用job，数据持久化，即有状态的job
public class HelloJob implements Job{
	
	// jobdatamap传值进来默认会调用set方法赋值，因此取值是可直接用messege；
	 private Integer count;
	 

	public void setCount(Integer count) {
		this.count = count;
	}


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//输出当前时间
		Date date = new Date();
		
		//获取jobDetail信息
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("工作任务名称：" + jobKey.getName() + "  ;组：" +jobKey.getGroup());
		
		//从jobdetail对象中获取jobdatamap数据
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		System.out.println("jobdetail传过来的值：" + jobDataMap.getString("message"));
		
		//从jobdetail对象中获取jobdatamap数据
		JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
		System.out.println("trigger传过来的值：" + triggerDataMap.getString("message"));
		
		//获取trigger信息
		TriggerKey triggerKey = context.getTrigger().getKey();
		System.out.println("触发器名称：" + triggerKey.getName() + "  组：" + triggerKey.getGroup());
		//triger里获取jobkey
		System.out.println("triger获取的job组：" + context.getTrigger().getJobKey().getGroup());
		//triger的开始时间、结束时间
		System.out.println("triger的开始时间：" + context.getTrigger().getStartTime() + "   结束时间：" + context.getTrigger().getEndTime());
		
		//获取其他信息
		System.out.println("当前任务执行时间：" + context.getFireTime());
		System.out.println("下次任务执行时间：" + context.getNextFireTime());
		
		
		//测试打印
		System.out.println("job执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
		//
		++count;
		System.out.println("count:" + count);
		context.getJobDetail().getJobDataMap().put("count", count); //默认是无状态的job，即每次启动job都是一个新的jobdatamap，有状态的job见类注解
	}
	
}
