package io.tastypenguinbacon.fractal.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        List<Path> filesToConcat = Files.list(Paths.get("."))
                .filter(p -> p.toString().endsWith("cudo.txt"))
                .sorted(Comparator.comparing(Path::toString).reversed())
                .collect(Collectors.toList());

        try (FileOutputStream fos = new FileOutputStream("concated.txt")) {
            for (Path file : filesToConcat) {
                Files.copy(file, fos);
                Files.delete(file);
            }
        }

        return RepeatStatus.FINISHED;
    }
}
