package io.tastypenguinbacon.fractal;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    private static final String CTXT_LOCATION = "classpath:/application.xml";

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        try (ClassPathXmlApplicationContext ctxt = new ClassPathXmlApplicationContext(CTXT_LOCATION)) {
            Job job = ctxt.getBean("generate-mandelbrot", Job.class);
            JobLauncher jobLauncher = ctxt.getBean("jobLauncher", JobLauncher.class);
            JobExecution run = jobLauncher.run(job, new JobParameters());
            System.out.println(run.getExitStatus().getExitCode());
        }
    }
}
