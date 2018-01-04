package tastypenguinbacon.dontforgetboutit.entities.exceptions.url;

/**
 * Created by pingwin on 31.12.17.
 */
public class UrlNotFound extends Exception {
    public UrlNotFound() {
    }

    public UrlNotFound(String message) {
        super(message);
    }

    public UrlNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlNotFound(Throwable cause) {
        super(cause);
    }

    public UrlNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
