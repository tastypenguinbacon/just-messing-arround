package tastypenguinbacon.dontforgetboutit.remember;

import tastypenguinbacon.dontforgetboutit.entities.url.Url;

import javax.validation.constraints.NotNull;
import java.util.Objects;


public class RememberUrlDTO {
    @NotNull(message = "User has to be set")
    private String user;

    @NotNull(message = "Uri has to be set")
    @Url
    private String uri;

    public void setUser(String user) {
        this.user = user;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUser() {
        return user;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RememberUrlDTO)) {
            return false;
        }
        RememberUrlDTO another = (RememberUrlDTO) o;
        return Objects.equals(user, another.user) &&
                Objects.equals(uri, another.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, uri);
    }
}
