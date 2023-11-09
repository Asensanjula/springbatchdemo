package com.pappzi.springbatchdemo.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class BaseConfig {

    private JobRepository jobRepository;

    public BaseConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public JobLauncher simpleJobLauncher() {

        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        return simpleJobLauncher;

    }

    @Bean
    public ExecutionContext executionContext() {

        return new ExecutionContext();
    }
}
