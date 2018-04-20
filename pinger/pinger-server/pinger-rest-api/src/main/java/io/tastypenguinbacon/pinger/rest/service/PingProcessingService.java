package io.tastypenguinbacon.pinger.rest.service;

import io.tastypenguinbacon.pinger.rest.bo.PositivePing;
import io.tastypenguinbacon.pinger.rest.bo.UnreachablePing;

public interface PingProcessingService {
    void savePositivePing(PositivePing positivePing);

    void saveUnreachable(UnreachablePing unreachablePing);
}
