package com.pappzi.springbatchdemo.job;

import com.pappzi.springbatchdemo.dto.EmployeeDto;
import com.pappzi.springbatchdemo.job.skippolicy.JobSkipPolicy;
import com.pappzi.springbatchdemo.listener.DemoJobListener;
import com.pappzi.springbatchdemo.mapper.EmployeeFieldRowMapper;
import com.pappzi.springbatchdemo.model.entity.Employee;
import com.pappzi.springbatchdemo.processor.EmployeeProcessor;
import com.pappzi.springbatchdemo.writer.EmployeeWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class FixedLengthJob {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private EmployeeProcessor employeeProcessor;
    private EmployeeWriter employeeWriter;
    private JobSkipPolicy jobSkipPolicy;

    // constructor injection
    public FixedLengthJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EmployeeProcessor employeeProcessor, EmployeeWriter employeeWriter , JobSkipPolicy jobSkipPolicy) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.employeeProcessor = employeeProcessor;
        this.employeeWriter = employeeWriter;
        this.jobSkipPolicy = jobSkipPolicy;
    }

    // create the Job
    @Bean
    @Qualifier(value = "demo2")
    public Job demo2() throws Exception {

        return this.jobBuilderFactory.get("demo2")
                .start(startDemo2())
//                .next(startDemo2()) // next steps
//                .listener(demoJobListener())
                .build();
    }

    @Bean
    public Step startDemo2() throws Exception {

        return this.stepBuilderFactory.get("step1")
                .<EmployeeDto, Employee>chunk(5)
                .reader( employeeReader2() )
                .processor( employeeProcessor )
                .writer( employeeWriter )
//                .taskExecutor(simpletaskexecutor())
               // .faultTolerant().skipPolicy(jobSkipPolicy)
                .build();
    }

    // muti threaded execution
//    @Bean
//    public TaskExecutor simpletaskexecutor() {
//
//        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        simpleAsyncTaskExecutor.setConcurrencyLimit(5); // depended on the resources
//        return  simpleAsyncTaskExecutor;
//    }

    @Bean
    @StepScope
    public Resource inputFileResourceFixedLength( @Value( "#{ jobParameters[fileName] }" ) final String fileName) throws Exception{

//        if (fileName == null) {
//            throw new IllegalArgumentException("File name must not be null");
//        }

        return new ClassPathResource(fileName);
    }


    // employee Reader
    @Bean
    @StepScope
    public FlatFileItemReader<EmployeeDto> employeeReader2() throws Exception {

            FlatFileItemReader<EmployeeDto> reader = new FlatFileItemReader<>();
            reader.setResource( inputFileResourceFixedLength(null) );

            reader.setLineMapper(
                    new DefaultLineMapper<EmployeeDto>() {{
                             setLineTokenizer(new FixedLengthTokenizer() {{
                                    setNames("employeeId", "firstName", "lastName", "email", "age");
                                    setColumns(new Range(1,5), new Range(6,10), new Range(11,15), new Range(16,37), new Range(38,39));
                             }});

                        setFieldSetMapper(new EmployeeFieldRowMapper() );
                    }}
            );

            return  reader;
    }

//    @Bean
//    public DemoJobListener demoJobListener() {
//
//        return new DemoJobListener();
//    }


}
