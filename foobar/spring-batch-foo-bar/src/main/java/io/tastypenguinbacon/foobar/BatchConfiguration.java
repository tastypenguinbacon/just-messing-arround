package io.tastypenguinbacon.foobar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BatchConfiguration(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemReader<Integer> fooBarReader(
            @Value("${foobar.start}") int start,
            @Value("${foobar.cnt}") int cnt) {
        return new ItemReader<Integer>() {
            private int i = start;

            @Override
            public Integer read() {
                return i != start + cnt ? i++ : null;
            }
        };
    }

    @Bean
    public ItemProcessor<Integer, String> fooBarProcessor() {
        return i -> i % 15 == 0 ? "foobar" : i % 3 == 0 ? "foo" : i % 5 == 0 ? "bar" : i.toString();
    }

    @Bean
    public ItemWriter<String> fooBarWriter() {
        return System.out::println;
    }

    @Bean
    public Job fooBarJob(Step fooBarStep) {
        return jobBuilderFactory.get("fooBarJob")
                .incrementer(new RunIdIncrementer())
                .flow(fooBarStep)
                .end()
                .build();
    }

    @Bean
    public Step fooBarStep(
            ItemReader<Integer> fooBarReader,
            ItemProcessor<Integer, String> fooBarProcessor,
            ItemWriter<String> fooBarWriter) {
        return stepBuilderFactory.get("fooBarStep")
                .<Integer, String>chunk(10)
                .reader(fooBarReader)
                .processor(fooBarProcessor)
                .writer(fooBarWriter)
                .build();
    }
}
