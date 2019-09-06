package io.tastypenguinbacon.fractal.batch;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@StepScope
public class ImaginaryNumberReader implements ItemReader<Tuple2<Double, Double>> {
    private final double minRe;
    private final double maxRe;
    private final double minIm;
    private final double maxIm;
    private final double step;
    private double currentRe;
    private double currentIm;

    @Autowired
    private BatchContext batchContext;

    public ImaginaryNumberReader(
            @Value("${mandelbrot.minRe}") double minRe,
            @Value("${mandelbrot.maxRe}") double maxRe,
            @Value("#{stepExecutionContext[minIm]}") double minIm,
            @Value("#{stepExecutionContext[maxIm]}") double maxIm,
            @Value("${mandelbrot.step}") double step
    ) {
        this.minRe = minRe;
        this.maxRe = maxRe;
        this.minIm = minIm;
        this.maxIm = maxIm;
        this.currentRe = this.minRe;
        this.currentIm = this.maxIm;
        this.step = step;
    }

    public Tuple2<Double, Double> read() {
        Tuple2<Double, Double> current = Tuple.of(currentRe, currentIm);
        if (currentRe >= maxRe) {
            currentRe = minRe;
            currentIm -= step;
            batchContext.setNewLine(true);
        } else {
            currentRe += step;
        }

        if (current._2 >= minIm) {
            return current;
        } else {
            return null;
        }
    }
}
