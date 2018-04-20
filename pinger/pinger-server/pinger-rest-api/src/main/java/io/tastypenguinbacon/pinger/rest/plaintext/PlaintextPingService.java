package io.tastypenguinbacon.pinger.rest.plaintext;


import io.tastypenguinbacon.pinger.rest.service.PingProcessingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PlaintextPingService implements PingService {
    private final PingProcessingService processingService;

    @Inject
    public PlaintextPingService(PingProcessingService processingService) {
        this.processingService = processingService;
    }

    public void consumePing(String ping) {
        if (isReachable(ping))
            processingService.savePositivePing(null);
        processingService.saveUnreachable(null);
    }

    private boolean isReachable(String ping) {
        String[] words = ping.split(" ");
        try {
            String bytesCount = words[0];
            Integer.valueOf(bytesCount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
