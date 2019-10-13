package io.tastypenguinbacon.foobar.batch.chunk;

import io.tastypenguinbacon.foobar.model.FooBarDTO;
import org.springframework.batch.item.ItemProcessor;

public class FooBarItemProcessor implements ItemProcessor<Integer, FooBarDTO> {
    @Override
    public FooBarDTO process(Integer i) {
        if (i % 15 == 0) {
            return new FooBarDTO(i, "FooBar");
        } else if (i % 5 == 0) {
            return new FooBarDTO(i, "Bar");
        } else if (i % 3 == 0) {
            return new FooBarDTO(i, "Foo");
        } else {
            return new FooBarDTO(i, String.valueOf(i));
        }
    }
}
