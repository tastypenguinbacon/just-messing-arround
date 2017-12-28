package tastypenguinbacon.dontforgetboutit.remember;

import tastypenguinbacon.dontforgetboutit.monitoring.fed.SpyedByTheFeds;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("remembered")
@Produces(MediaType.APPLICATION_JSON)
public class RememberResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @SpyedByTheFeds(agency = "fbi")
    public RememberUrlDTO rememberLink(
            @NotNull @Valid RememberUrlDTO urlToRemember) {
        return urlToRemember;
    }
}
