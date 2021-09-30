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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> list() {
        return categoryService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("id") Long id) {
        return categoryService.getCategory(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category add(Category category) {
        return categoryService.createCategory(category);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        categoryService.delete(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCategory(Category category) {
        categoryService.updateEntry(category);
    }
}
