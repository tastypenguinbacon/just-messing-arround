package io.tastypenguinbacon.foobar.batch.chunk;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@StepScope
public class FooBarItemReader implements ItemReader<Integer> {
    @Value("${foo.bar.startValue}")
    private int startValue;

    @Value("${foo.bar.endValue}")
    private int endValue;

    private int curr;

    @PostConstruct
    public void init() {
        curr = startValue;
    }

    @Override
    public Integer read() {
        if (curr == endValue) {
            return null;
        } else {
            return curr++;
        }
    }
}
