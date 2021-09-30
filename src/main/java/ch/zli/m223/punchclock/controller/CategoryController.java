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

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.EntryUser;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.service.EntryUserService;
import io.quarkus.security.Authenticated;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;

@Path("/categories")
public class CategoryController {

    @Inject
    CategoryService categoryService;

    /**
     * @return this function returns all categories
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> list() {
        return categoryService.findAll();
    }

    /**
     * this function gets the id in the URL and gets a specific category
     *
     * @param id is getting passed in the URL
     * @return a specific category
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("id") Long id) {
        return categoryService.getCategory(id);
    }

    /**
     * This function creates a new Category
     * @param category creates a Category with the POST request
     * @return the newly created Category
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category add(Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * This function deletes a specific category
     * @param id gets passed in the URL
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        categoryService.delete(id);
    }

    /**
     * This function updates a already existing category
     * @param category creates a Category with the PUT request
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCategory(Category category) {
        categoryService.updateCategory(category);
    }
}
