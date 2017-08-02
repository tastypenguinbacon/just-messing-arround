package io.github.tastypenguinbacon.dontforgetboutit;

import java.util.Arrays;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Main extends Application {
    public static void main(String... args) {
        String arguments = Arrays.toString(args);
        System.out.println(arguments);
    }
}
