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

import ch.zli.m223.punchclock.domain.Place;
import ch.zli.m223.punchclock.service.EntryUserService;
import ch.zli.m223.punchclock.service.PlaceService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/places")
public class PlaceController {

    @Inject
    PlaceService placeService;

    /**
     * @return a list of all places
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Place> list() {
        return placeService.findAll();
    }

    /**
     * This function gets a specific Place
     * @param id get passed in the URL
     * @return a specific Place
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Place getPlace(@PathParam("id") Long id) {
        return placeService.getPlace(id);
    }

    /**
     * This function creates a new Place
     * @param place creates a Place with the POST request
     * @return the newly created Place
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Place add(Place place) {
        return placeService.createPlace(place);
    }

    /**
     * This function deletes a specific Place
     * @param id gets passed in the URL
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        placeService.delete(id);
    }

    /**
     * This function updates an already existing Place
     * @param place creates a Place with the PUT request
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePlace(Place place) {
        placeService.updatePlace(place);
    }
}
