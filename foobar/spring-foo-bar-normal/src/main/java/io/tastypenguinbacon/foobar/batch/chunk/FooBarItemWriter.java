package io.tastypenguinbacon.foobar.batch.chunk;

import io.tastypenguinbacon.foobar.model.FooBarDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

@StepScope
public class FooBarItemWriter implements ItemWriter<FooBarDTO> {
    private static final Logger LOG = LogManager.getLogger(FooBarItemWriter.class);
    private static final String INSERT_FOO_BAR = "insert into FooBarValue (number_value, string_value) values (?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public synchronized void write(List<? extends FooBarDTO> list) {
        LOG.info("Writing batch of size {} to database", list.size());
        jdbcTemplate.batchUpdate(INSERT_FOO_BAR, toSqlParams(list));
    }

    private List<Object[]> toSqlParams(List<? extends FooBarDTO> list) {
        return list.stream()
                .map(fooBar -> new Object[]{fooBar.getNumberValue(), fooBar.getStringValue()})
                .collect(Collectors.toList());
    }
}
