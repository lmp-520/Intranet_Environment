package com.xdmd.IntranetEnvironment.demo.config;

import com.xdmd.IntranetEnvironment.demo.mapper.TimingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration("myScheduled")
@EnableScheduling
public class TimingTasks {

    @Autowired
    private TimingMapper timingMapper;

    @Scheduled(cron = "0 11 11 ? * *")
    public void reporCurrentTime() throws Exception {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        TestMail.sendMail();
    }
}
