package ac.htl.leonding.boundary;

import ac.htl.leonding.control.MenuRepository;
import ac.htl.leonding.entities.Menu;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/menus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource {

    @Inject
    MenuRepository menuRepository;

    @GET
    public Response getAllMenus() {
        List<Menu> menus = menuRepository.listAll();
        return Response.ok(menus).build();
    }

    @GET
    @Path("/{id}")
    public Response getMenuById(@PathParam("id") Long id) {
        Menu menu = menuRepository.findById(id);
        if (menu == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(menu).build();
    }

    @GET
    @Path("/restaurant/{restaurantId}")
    public Response getMenuByRestaurantId(@PathParam("restaurantId") Long restaurantId) {
        Menu menu = menuRepository.findMenuByRestaurantId(restaurantId);
        if (menu == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(menu).build();
    }

    @POST
    @Transactional
    public Response createMenu(Menu menu) {
        menuRepository.persist(menu);
        return Response.status(Response.Status.CREATED).entity(menu).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateMenu(@PathParam("id") Long id, Menu menu) {
        Menu existingMenu = menuRepository.findById(id);
        if (existingMenu == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        menu.setId(id);
        Menu updated = menuRepository.update(menu);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMenu(@PathParam("id") Long id) {
        Menu menu = menuRepository.findById(id);
        if (menu == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        menuRepository.deleteById(id);
        return Response.noContent().build();
    }
}