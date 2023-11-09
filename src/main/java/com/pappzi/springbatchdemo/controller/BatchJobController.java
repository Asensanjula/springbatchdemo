package com.pappzi.springbatchdemo.controller;

import com.pappzi.springbatchdemo.runner.JobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BatchJobController {

    private JobRunner jobRunner;

    public BatchJobController(JobRunner jobRunner) {
        this.jobRunner = jobRunner;
    }

    @GetMapping("/runJob")
    public String runBatchJob() {

        jobRunner.runJBatchJob();
        return "Demo 1 started ....";

    }

    @GetMapping("/runFixedLengthJob")
    public String runFixedLengthBatchJob() {

        jobRunner.runJFixedLengthBatchJob();
        return "Fixed Length Job started ....";

    }
}
