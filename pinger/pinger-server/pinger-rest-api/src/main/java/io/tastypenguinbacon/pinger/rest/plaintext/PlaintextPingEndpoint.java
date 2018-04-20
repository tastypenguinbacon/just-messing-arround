package io.tastypenguinbacon.pinger.rest.plaintext;

import io.tastypenguinbacon.pinger.rest.validation.ValidPing;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("plain")
@Consumes(MediaType.TEXT_PLAIN)
public class PlaintextPingEndpoint {
    private PingService pingService;

    @POST
    public void savePing(@ValidPing String pingMessage) {
        pingService.consumePing(pingMessage);
    }

    @Inject
    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }
}
