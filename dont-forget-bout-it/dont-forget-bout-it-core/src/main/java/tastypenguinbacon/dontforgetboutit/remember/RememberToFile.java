package tastypenguinbacon.dontforgetboutit.remember;

import tastypenguinbacon.dontforgetboutit.entities.exceptions.url.FailedToSave;
import tastypenguinbacon.dontforgetboutit.entities.exceptions.url.UrlNotFound;
import tastypenguinbacon.dontforgetboutit.file.FileAccess;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by pingwin on 31.12.17.
 */
@RequestScoped
public class RememberToFile implements RememberRepository {
    private final static String BASE_REMEMBER_PATH = "./remembered/";
    private final FileAccess fileAccess;

    private RememberUrlDTO toRemember;

    @Inject
    public RememberToFile(FileAccess fileAccess) {
        this.fileAccess = fileAccess;
    }

    @PostConstruct
    public void createRememberedDirectoryIfDoesNotExist() {
        Path baseDirectory = Paths.get(BASE_REMEMBER_PATH);
        try {
            fileAccess.createDirectoryIfAbsent(baseDirectory);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create remember directory", e);
        }
    }

    @Override
    public RememberedIdentifier saveUrlToRemember(RememberUrlDTO urlToRemember)
            throws FailedToSave {
        this.toRemember = urlToRemember;
        try {
            createUserDirectory();
            createUrlDirectory();
            createUrlCategory();
            appendUrlToFile();
            return new RememberedIdentifier("file", urlToRemember.getName());
        } catch (IOException e) {
            throw new FailedToSave("Failed to save url", e);
        }
    }

    private void createUserDirectory() throws IOException {
        Path userDirectory = Paths.get(userDirectory());
        fileAccess.createDirectoryIfAbsent(userDirectory);
    }

    private void createUrlDirectory() throws IOException {
        Path urlFile = Paths.get(urlDirectory());
        fileAccess.createDirectoryIfAbsent(urlFile);
    }

    private void createUrlCategory() throws IOException {
        Path categoryFile = Paths.get(urlDirectory() + toRemember.getName());
        fileAccess.createFileIfAbsent(categoryFile);
    }

    private void appendUrlToFile() throws IOException {
        Path fileName = Paths.get(urlDirectory() + toRemember.getName());
        fileAccess.append(fileName, toRemember.getUrl());
    }


    private String userDirectory() {
        return BASE_REMEMBER_PATH + toRemember.getUser();
    }

    private String urlDirectory() {
        return userDirectory() + "/url/";
    }

    @Override
    public List<String> getRememberedUrls(String user, String id) throws UrlNotFound {
        Path urlPath = Paths.get(BASE_REMEMBER_PATH + user + "/" + "url/" + id);
        try {
            return fileAccess.getLines(urlPath);
        } catch (IOException e) {
            throw new UrlNotFound("Couldn't find URL for user " + user + "and name" + id, e);
        }
    }
}
