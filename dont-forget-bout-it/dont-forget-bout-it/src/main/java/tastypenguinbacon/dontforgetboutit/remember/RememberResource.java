package tastypenguinbacon.dontforgetboutit.remember;

import tastypenguinbacon.dontforgetboutit.entities.exceptions.url.FailedToSave;
import tastypenguinbacon.dontforgetboutit.entities.exceptions.url.UrlNotFound;
import tastypenguinbacon.dontforgetboutit.monitoring.fed.SpyedByTheFeds;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("remembered")
@Produces(MediaType.APPLICATION_JSON)
public class RememberResource {
    private RememberRepository remember;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @SpyedByTheFeds(agency = "fbi")
    public RememberedIdentifier rememberLink(
            @NotNull @Valid RememberUrlDTO urlToRemember) {
        try {
            return remember.saveUrlToRemember(urlToRemember);
        } catch (FailedToSave e) {
            throw new InternalServerErrorException("Failed to save", e);
        }
    }

    @GET
    @SpyedByTheFeds(agency = "fbi")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{user}/{rememberedId}")
    public List<String> getRememberedUrl(
            @PathParam("user") String user,
            @PathParam("rememberedId") String id) {
        try {
            return remember.getRememberedUrls(user, id);
        } catch (UrlNotFound e) {
            throw new NotFoundException("Couldn't find url of id" + id, e);
        }
    }

    @Inject
    public void setRememberRepository(RememberRepository remember) {
        this.remember = remember;
    }
}
