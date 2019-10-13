package io.tastypenguinbacon.foobar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final String CTXT_LOCATION = "classpath:/spring/application.xml";

    public static void main(String[] args) throws Exception {
        try (ClassPathXmlApplicationContext ctxt = new ClassPathXmlApplicationContext(CTXT_LOCATION)) {
            Job job = ctxt.getBean("foo-bar-job", Job.class);
            JobLauncher jobLauncher = ctxt.getBean(JobLauncher.class);
            jobLauncher.run(job, jobParameters());
        }
    }

    private static JobParameters jobParameters() {
        return new JobParametersBuilder()
                .toJobParameters();
    }
}
