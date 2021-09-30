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

import io.quarkus.security.Authenticated;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;

@Authenticated
@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    /**
     * This function gets all Entries
     * @return a list of entries
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries", description = "")
    public List<Entry> list() {
        return entryService.findAll();
    }

    /**
     * This function gets all entries of a specific user
     * @param id gets passed in the URL
     * @return a list of entries
     */
    @GET
    @Path("/entriesFromUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> listEntriesFromUser(@PathParam("id") Long id) {
        return entryService.findAllFromUser(id);
    }

    /**
     * This function gets a specific Entry
     * @param id gets passed in the URL
     * @return a Entry
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entry getEntry(@PathParam("id") Long id) {
        return entryService.getEntry(id);
    }

    /**
     * This function creates a new Entry
     * @param entry creates a Entry with the POST request
     * @return the newly created Entry
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry add(Entry entry) {
       return entryService.createEntry(entry);
    }

    /**
     * This function deletes an existing Entry
     * @param id gets passed in the URL
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        entryService.delete(id);
    }

    /**
     * This function updates a already existing Entry
     * @param entry creates a Entry with the POST request
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEntry(Entry entry) {
        entryService.updateEntry(entry);
    }
}
