package io.tastypenguinbacon.pinger.integration;

import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import org.testng.annotations.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import static org.testng.Assert.assertEquals;

public class Echo {
    @Test
    public void restApiIsAlive() {
        Escaper pathEscaper = UrlEscapers.urlFragmentEscaper();
        String restApiPath = pathEscaper.escape("pinger-rest-api-1.0.0-SNAPSHOT");
        System.out.println(restApiPath);
        String echo = "cudo";
        String response = ClientBuilder.newClient()
                .target("localhost:8080/" + restApiPath)
                .request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(echo, MediaType.TEXT_PLAIN),
                        String.class);
        assertEquals(response, echo);
    }
}
