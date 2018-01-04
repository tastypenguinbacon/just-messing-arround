package tastypenguinbacon.dontforgetboutit.remember;

import tastypenguinbacon.dontforgetboutit.entities.exceptions.url.FailedToSave;
import tastypenguinbacon.dontforgetboutit.entities.exceptions.url.UrlNotFound;

import java.util.List;

/**
 * Created by pingwin on 31.12.17.
 */
public interface RememberRepository {
    RememberedIdentifier saveUrlToRemember(RememberUrlDTO urlToRemember)
            throws FailedToSave;

    List<String> getRememberedUrls(String user, String id) throws UrlNotFound;
}
