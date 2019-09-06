package io.tastypenguinbacon.fractal.batch;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MandelbrotPartitioner implements Partitioner {
    @Value("${mandelbrot.minIm}")
    private double minIm;

    @Value("${mandelbrot.maxIm}")
    private double maxIm;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        return IntStream.iterate(0, i -> i + 1)
                .mapToObj(getExecutionContextIntFunction(gridSize))
                .limit(gridSize)
                .collect(Collectors.toMap(k -> k.get("filename").toString(), Function.identity()));
    }

    private IntFunction<ExecutionContext> getExecutionContextIntFunction(int gridSize) {
        return i -> {
            ExecutionContext ec = new ExecutionContext();
            ec.put("minIm", minIm + i * (maxIm - minIm) / gridSize);
            ec.put("maxIm", minIm + (i + 1) * (maxIm - minIm) / gridSize);
            ec.put("filename", String.format("%04d_cudo.txt", i));
            return ec;
        };
    }
}
