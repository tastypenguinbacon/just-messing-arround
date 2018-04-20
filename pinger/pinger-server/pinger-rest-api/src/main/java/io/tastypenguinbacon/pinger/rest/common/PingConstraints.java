package io.tastypenguinbacon.pinger.rest.common;

import javax.enterprise.context.RequestScoped;
import java.util.regex.Pattern;

@RequestScoped
public class PingConstraints {
    private final static Pattern positivePing
            = Pattern.compile("");

    public boolean isReachable(String ping) {
        return true;
    }

    public boolean isUnreachable(String ping) {
        return false;
    }
}
