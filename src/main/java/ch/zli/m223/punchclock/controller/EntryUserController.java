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
public class EntryUserController {

    @Inject
    EntryUserService entryUserService;

    /**
     * @return a list of EntryUser
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntryUser> list() {
        return entryUserService.findAll();
    }

    /**
     * This function gets a specific EntryUser
     * @param id gets passed in the URL
     * @return a specific EntryUser
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntryUser getEntry(@PathParam("id") Long id) {
        return entryUserService.getEntryUser(id);
    }

    /**
     * This function gets a specific id of a EntryUser
     * @param username gets passed in the URL
     * @return a specific id
     */
    @GET
    @Path("/findByName/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public long getUserIdByName(@PathParam("username") String username) {
        return entryUserService.getEntryUserIdByName(username);
    }

    /**
     * This function gets a specific username of a EntryUser
     * @param username gets passed in the URL
     * @return a specific username
     */
    @GET
    @Path("/findByName/isAdmin/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean getUsernameByName(@PathParam("username") String username) {
        return entryUserService.getEntryUserByName(username);
    }

    /**
     * This function creates a new EntryUser
     * @param entryUser creates a EntryUser with the POST request
     * @return the newly created EntryUser
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EntryUser add(EntryUser entryUser) {
        return entryUserService.createUser(entryUser);
    }

    /**
     * This function deletes a specific EntryUser
     * @param id gets passed in the URL
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        entryUserService.delete(id);
    }

    /**
     * This function updates a already existing EntryUser
     * @param entryUser creates a EntryUser with the PUT request
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEntry(EntryUser entryUser) {
        entryUserService.updateEntry(entryUser);
    }
}
