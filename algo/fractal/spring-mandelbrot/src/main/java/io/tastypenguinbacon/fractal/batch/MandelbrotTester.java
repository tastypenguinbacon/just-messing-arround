package io.tastypenguinbacon.fractal.batch;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MandelbrotTester implements ItemProcessor<Tuple2<Double, Double>, Integer> {
    @Value("${mandelbrot.iterationCount}")
    private int iterationCount;

    @Override
    public Integer process(Tuple2<Double, Double> c) {
        Tuple2<Double, Double> x = Tuple.of(0d, 0d);
        for (int i = 0; i < iterationCount; i++) {
            if (isOutOfReach(x)) {
                return i;
            }
            x = nextIteration(x, c);
        }
        return iterationCount - 1;
    }

    private boolean isOutOfReach(Tuple2<Double, Double> x) {
        return x._1 * x._1 + x._2 * x._2 > 4;
    }

    private Tuple2<Double, Double> nextIteration(Tuple2<Double, Double> x, Tuple2<Double, Double> c) {
        double xRe = x._1, xIm = x._2, cRe = c._1, cIm = c._2;
        return Tuple.of(xRe * xRe - xIm * xIm + cRe, 2 * xRe * xIm + cIm);
    }
}
