package com.skills.tute.cron;

import com.skills.tute.repository.InterviewQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CronJobService {

    @Autowired
    private InterviewQuestionRepository questionRepository;

    //@Scheduled(cron = "*/15 * * * * *")
    @Scheduled(cron = "0 0 1 * * *", zone = "Asia/Kolkata")
    public void runCron() {
        LocalDateTime start = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        long startMillis = System.currentTimeMillis();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

        System.out.println("====================================");
        System.out.println("Cron Job Started At : " + start.format(formatter));
        System.out.println("Next Schedule       : Daily 01:00 AM");
        System.out.println("Job Running...");

        int res = questionRepository.reducePoint();

        LocalDateTime end = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        long endMillis = System.currentTimeMillis();

        long totalMs = endMillis - startMillis;
        long totalSec = Duration.ofMillis(totalMs).toSeconds();

        System.out.println("Reduce point response : " + res);
        System.out.println("Cron Job Ended At     : " + end.format(formatter));
        System.out.println("Execution Time        : " + totalMs + " ms");
        System.out.println("Execution Time        : " + totalSec + " sec");
        System.out.println("====================================");
    }
}
