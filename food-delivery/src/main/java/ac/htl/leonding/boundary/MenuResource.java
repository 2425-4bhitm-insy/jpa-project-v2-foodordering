package ac.htl.leonding.boundary;

import ac.htl.leonding.entities.Dish;
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

    @Inject
    DishRepository dishRepository;

    @GET
    public List<Menu> getAllMenus() {
        return menuRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getMenuById(@PathParam("id") Long id) {
        Menu menu = menuRepository.findById(id);
        if (menu != null) {
            return Response.ok(menu).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
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
        Menu entity = menuRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.setName(menu.getName());

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMenu(@PathParam("id") Long id) {
        boolean deleted = menuRepository.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/dishes")
    public List<Dish> getMenuDishes(@PathParam("id") Long id) {
        return dishRepository.findAvailableByMenuId(id);
    }

    @POST
    @Path("/{id}/dishes")
    @Transactional
    public Response addDishToMenu(@PathParam("id") Long id, Dish dish) {
        Menu menu = menuRepository.findById(id);
        if (menu == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        dish.setMenu(menu);
        dishRepository.persist(dish);

        return Response.status(Response.Status.CREATED).entity(dish).build();
    }
}