package io.tastypenguinbacon.xslt.read;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.testng.Assert.assertNotNull;

public class ReadingFilesFromResources {
    @Test
    public void loadFileFromResources() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/txt/tmp.txt")) {
            assertNotNull(is, "Inputstream shouldn't be null");
        }
    }

    @Test
    public void printFileFromResourcesUsingScanner() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/txt/tmp.txt")) {
            Scanner s = new Scanner(is);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }
    }

    @Test
    public void printFileFromResourcesUsingBufferedReader() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/txt/tmp.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    @Test
    public void printFileFromResourcesUsingStreamApi() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/txt/tmp.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            br.lines().forEachOrdered(System.out::println);
        }
    }
}
