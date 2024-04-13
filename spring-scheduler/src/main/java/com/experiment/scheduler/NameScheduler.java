package com.experiment.scheduler;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NameScheduler {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;
    @Scheduled(cron = "20 * * * * *")
    @SchedulerLock(
            name = "${spring.application.name}_1",
            lockAtLeastFor = "PT100s",
            lockAtMostFor = "PT200s"
    )
    public void print2(){
        System.out.println("Running Scheduler 1 at "+webServerAppCtxt.getWebServer().getPort()+
                " at time "+ LocalDateTime.now());
    }

    @Scheduled(cron = "20 * * * * *")
    @SchedulerLock(
            name = "${spring.application.name}_2",
            lockAtLeastFor = "PT100s",
            lockAtMostFor = "PT200s"
    )
    public void print(){
        System.out.println("Running Scheduler 2 at "+webServerAppCtxt.getWebServer().getPort()+
                " at time "+ LocalDateTime.now());
    }

}
