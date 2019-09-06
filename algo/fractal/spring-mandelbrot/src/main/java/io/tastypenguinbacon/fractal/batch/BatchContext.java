package io.tastypenguinbacon.fractal.batch;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class BatchContext {
    private boolean newLine;

    public void setNewLine(boolean newLine) {
        this.newLine = newLine;

    }

    public boolean isNewLine() {
        return newLine;
    }
}
