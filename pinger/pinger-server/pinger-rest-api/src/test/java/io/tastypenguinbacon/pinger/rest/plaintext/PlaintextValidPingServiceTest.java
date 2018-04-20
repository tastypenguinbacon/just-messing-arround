package io.tastypenguinbacon.pinger.rest.plaintext;

import io.tastypenguinbacon.pinger.rest.exception.PingIsInvalid;
import io.tastypenguinbacon.pinger.rest.service.PingProcessingService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlaintextValidPingServiceTest {
    @Test(dataProvider = "positivePing")
    public void whenPositivePingStringOccurredSavesPositive(String positivePing) {
        PingProcessingService processingService = mock(PingProcessingService.class);
        PingService pingService = new PlaintextPingService(processingService);

        pingService.consumePing(positivePing);

        verify(processingService).savePositivePing(any());
    }

    @DataProvider
    public static Object[][] positivePing() {
        return new Object[][]{
                {"64 bytes from 8.8.8.8: icmp_seq=1177 ttl=61 time=47.6 ms"},
                {"128 bytes from 8.8.8.8: icmp seq=1777 ttl=61 time=21.2 ms"},
        };
    }

    @Test
    public void whenPingIsUnreachableThenSavesItAsUnreachable() {
        String unreachable = "ping: sendmsg: Sieć jest niedostępna";
        PingProcessingService processingService = mock(PingProcessingService.class);
        PingService pingService = new PlaintextPingService(processingService);

        pingService.consumePing(unreachable);
        verify(processingService).saveUnreachable(any());
    }
}