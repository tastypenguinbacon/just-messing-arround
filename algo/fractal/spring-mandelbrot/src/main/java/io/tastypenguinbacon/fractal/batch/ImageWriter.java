package io.tastypenguinbacon.fractal.batch;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

@Service
@StepScope
public class ImageWriter implements ItemWriter<Integer> {
    @Autowired
    private BatchContext batchContext;


    @Value("#{stepExecutionContext[filename]}")
    private String filename;

    private StringBuilder sb;

    @BeforeStep
    public void init() throws IOException {
        File file = new File(filename);
        if (!file.createNewFile()) {
            file.delete();
            file.createNewFile();
        }
        sb = new StringBuilder();
    }

    @Override
    public void write(List<? extends Integer> list) throws Exception {
        list.forEach(i -> sb.append(i).append(" "));
        if (batchContext.isNewLine()) {
            printElement();
            sb = new StringBuilder();
            batchContext.setNewLine(false);
        }
    }

    private void printElement() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(filename), true);
             PrintStream ps = new PrintStream(fos)) {
            ps.println(sb.toString());
        }
    }
}
