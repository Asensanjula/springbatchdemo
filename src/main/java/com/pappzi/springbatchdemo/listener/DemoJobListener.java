package com.pappzi.springbatchdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class DemoJobListener  implements JobExecutionListener {

    @Override
    // two different execution contenxt
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before Job Execution Method...");
        jobExecution.getExecutionContext().putString("testKey", "executionBefore");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After Job Execution Method...");

    }
}
