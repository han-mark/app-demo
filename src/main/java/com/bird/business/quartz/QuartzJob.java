package com.bird.business.quartz;

import com.bird.business.service.ITbLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class QuartzJob {

    @Autowired
    ITbLogService tbLogService;

    private  static  final Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    @Scheduled(cron = "0 0 * * * ?")
    public void clearLogInfo(){
        logger.info("每小时清理一次日志信息(已清理)");
        tbLogService.deleteByExample(null);
    }
}
