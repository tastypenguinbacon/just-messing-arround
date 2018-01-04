package tastypenguinbacon.dontforgetboutit.remember;

import tastypenguinbacon.dontforgetboutit.entities.url.Url;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@SuppressWarnings("unused")
public class RememberUrlDTO {
    @NotNull(message = "User has to be set")
    private String user;

    @NotNull(message = "Uri has to be set")
    @Url
    private String url;

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String urlName) {
        this.name = urlName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RememberUrlDTO)) {
            return false;
        }
        RememberUrlDTO another = (RememberUrlDTO) o;
        return Objects.equals(user, another.user) &&
                Objects.equals(url, another.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, url);
    }
}
