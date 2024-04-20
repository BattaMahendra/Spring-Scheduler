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

    @Value("${spring.instances}")
    private String instance;
    /*
    * Please refer to this article for deeper info
    * https://rieckpil.de/lock-scheduled-tasks-with-shedlock-and-spring-boot/*/
    @Scheduled(cron = "* * * * * *")
    @SchedulerLock(
            //name should be same for every instance otherwise unique number of tables will be created in DB
            name = "${spring.application.name}_${spring.instances}",
            lockAtLeastFor = "PT10s",
            lockAtMostFor = "PT120s"
    )
    public void print2(){
        System.out.println("Running Scheduler "+instance+" at "+webServerAppCtxt.getWebServer().getPort()+
                " at time "+ LocalDateTime.now());
    }

//    @Scheduled(cron = "15 * * * * *")
//    @SchedulerLock(
//            name = "${spring.application.name}_${spring.instances}",
//            lockAtLeastFor = "PT100s",
//            lockAtMostFor = "PT200s"
//    )
//    public void print(){
//        System.out.println("Running Scheduler 2 at "+webServerAppCtxt.getWebServer().getPort()+
//                " at time "+ LocalDateTime.now());
//    }

}
