package com.pappzi.springbatchdemo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JobRunner {

    private JobLauncher simpleJobLauncher;
    private Job demo1;
    private Job demo2;
    private ExecutionContext executionContext;

    public JobRunner(JobLauncher jobLauncher, Job demo1 , ExecutionContext executionContext, Job demo2) {
        this.simpleJobLauncher = jobLauncher;
        this.demo1 = demo1;
        this.executionContext = executionContext;
        this.demo2 = demo2;
    }

    @Async
    public void runJBatchJob()  {

        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addString("fileName", "employees.csv");
        parametersBuilder.addDate("date", new Date(), true);
        executionContext.putString("customFileName", "emp.csv");
        runJob(demo1, parametersBuilder.toJobParameters());

    }

    @Async
    public void runJFixedLengthBatchJob()  {

        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addString("fileName", "employeesFixedLength.csv");
        parametersBuilder.addDate("date", new Date(), true);
        executionContext.putString("customFileName", "fixedLengh.csv");
        runJob(demo2, parametersBuilder.toJobParameters());

    }


    private void runJob(Job job, JobParameters jobParameters) {
        try {

            log.info("Starting Job :  {}" , job.getName());
            JobExecution jobExecution = simpleJobLauncher.run(job, jobParameters);

        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
