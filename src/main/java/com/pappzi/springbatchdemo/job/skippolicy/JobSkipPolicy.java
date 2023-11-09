package com.pappzi.springbatchdemo.job.skippolicy;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

@Component
public class JobSkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip( Throwable throwable, int failedCount ) throws SkipLimitExceededException {

        return true;
    }
}
