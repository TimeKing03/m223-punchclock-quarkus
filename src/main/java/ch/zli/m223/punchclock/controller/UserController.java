package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Path;

import ch.zli.m223.punchclock.domain.EntryUser;
import ch.zli.m223.punchclock.service.EntryUserService;
import io.quarkus.security.Authenticated;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;

@Path("/user")
public class UserController {

    @Inject
    EntryUserService entryUserService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntryUser> list() {
        return entryUserService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntryUser getEntry(@PathParam("id") Long id) {
        return entryUserService.getEntryUser(id);
    }

    @GET
    @Path("/findByName/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public long getUserByName(@PathParam("username") String username) {
        return entryUserService.getEntryUserByName(username);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EntryUser add(EntryUser entryUser) {
        return entryUserService.createUser(entryUser);
    }


    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        entryUserService.delete(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEntry(EntryUser entryUser) {
        entryUserService.updateEntry(entryUser);
    }
}
