package com.bird.business.quartzdemo;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloSchedulerDemo {
	public static void main(String[] args) throws SchedulerException {
		//开始时间
		Date startDate = new Date();
		startDate.setTime(startDate.getTime() + 3000);
		
		//结束时间
		Date endDate = new Date();
		endDate.setTime(endDate.getTime() + 10000);
		
		//调度器：scheduler
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		//任务实例：jobDetail
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1") //任务名称、组名称
				.usingJobData("message", "jobdetail打印日志") //传递数据
				.usingJobData("count", 0) //传递数据
				.build();
		//获取job信息
		System.out.println("job名称：" + jobDetail.getKey().getName());
		System.out.println("job组名：" + jobDetail.getKey().getGroup());
		System.out.println("job类名：" + jobDetail.getJobClass().getName());
		
		//触发器：trigger
		//SimpleTrigger,简单触发器：开始时间、结束时间、重复间隔、重复次数
		/**
		@SuppressWarnings("static-access")
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1") //触发器名称、组名称
//				.startNow() //立即启动
				.startAt(startDate) //只指定startAt则只会执行一次
				.endAt(endDate) //结束时间优于重复次数
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()  
						.repeatSecondlyForever(5)
						.withRepeatCount(3)) //只重复执行4次,参数为数字或SimpleTrigger.REPEAT_INDEFINITELY等
				.usingJobData("message", "trigger打印日志") //传递数据
				.build();
		**/
		
		//CronTrigger,日程触发器：可以有startAt、endAt
		@SuppressWarnings("static-access")
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1") //触发器名称、组名称
				.startNow() //立即启动
//				.startAt(startDate) 
//				.endAt(endDate)
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * 14 4 ?")) //4月14日，每五秒执行一次
				.usingJobData("message", "trigger打印日志") //传递数据
				.build();
		//让调度器关联任务和触发器
		scheduler.scheduleJob(jobDetail, trigger);
		
		//启动
		scheduler.start();
	}
}
