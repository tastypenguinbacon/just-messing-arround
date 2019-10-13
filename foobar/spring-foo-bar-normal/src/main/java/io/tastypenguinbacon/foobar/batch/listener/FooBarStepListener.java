package io.tastypenguinbacon.foobar.batch.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.context.annotation.Scope;

@Scope(value = "prototype")
public class FooBarStepListener implements StepExecutionListener {
    private static final Logger LOG = LogManager.getLogger(FooBarStepListener.class);
    private long startTime;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        long stepDuration = System.currentTimeMillis() - startTime;
        LOG.info("Step duration: {}", stepDuration);
        return null;
    }
}
