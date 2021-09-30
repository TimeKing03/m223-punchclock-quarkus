package ch.zli.m223.punchclock.controller;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.EntryUser;
import ch.zli.m223.punchclock.service.AuthenticationService;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.EntryUserService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.h2.engine.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

@Path("/auth")
public class AuthController {

    @Inject
    AuthenticationService authService;

    @Inject
    EntryUserController entryUserController;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(EntryUser entryUser) {
        if (authService.userExists(entryUser)) {
            return authService.GenerateValidJwtToken(entryUser.getUsername());
        } else {
            return "";
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(EntryUser entryUser) {
        boolean check = true;
        for (EntryUser entryUser1 : entryUserController.list()) {
            if (Objects.equals(entryUser1.getUsername(), entryUser.getUsername())) {
                check = false;
                break;
            }
        }

        if (check) {
            entryUserController.add(entryUser);
            return authService.GenerateValidJwtToken(entryUser.getUsername());
        } else {
            return "";
        }
    }

}
