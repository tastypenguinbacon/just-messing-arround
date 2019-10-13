package io.tastypenguinbacon.foobar.batch.tasklet;

import io.tastypenguinbacon.foobar.model.FooBarDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@StepScope
public class FooBarTasklet implements Tasklet {
    private static final String SELECT_FOO_BAR = "select number_value, string_value from FooBarValue";
    private static final Logger LOG = LogManager.getLogger(FooBarTasklet.class);

    @Value("${foo.bar.startValue}")
    private int startValue;

    @Value("${foo.bar.endValue}")
    private int endValue;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws IOException {
        LOG.info("Writing FooBar to file");
        try (FileOutputStream fos = new FileOutputStream("cudo.txt");
             PrintWriter pw = new PrintWriter(new BufferedOutputStream(fos))) {
            jdbcTemplate.query(SELECT_FOO_BAR, new FooBarPrinter(pw));
        }
        return RepeatStatus.FINISHED;
    }

    private static class FooBarPrinter implements RowCallbackHandler {
        private final PrintWriter pw;

        FooBarPrinter(PrintWriter pw) {
            this.pw = pw;
        }

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            FooBarDTO fooBar = new FooBarDTO(resultSet.getInt(1), resultSet.getString(2));
            pw.println(fooBar);
        }
    }
}
