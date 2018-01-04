package tastypenguinbacon.dontforgetboutit.file;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pingwin on 31.12.17.
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class FileAccess {
    public void createDirectoryIfAbsent(Path baseDirectory) throws IOException {
        if (Files.notExists(baseDirectory)) {
            Files.createDirectory(baseDirectory);
        }
    }

    public void createFileIfAbsent(Path urlFile) throws IOException {
        if (Files.notExists(urlFile)) {
            Files.createFile(urlFile);
        }
    }

    public void append(Path urlFile, String s) throws IOException {
        Files.write(urlFile, Collections.singleton(s), StandardOpenOption.APPEND);
    }

    public List<String> getLines(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.toList());
        }
    }
}
