package ac.htl.leonding.boundary;

import ac.htl.leonding.control.DishRepository;
import ac.htl.leonding.entities.Dish;
import ac.htl.leonding.entities.dto.DishDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DishResource {

    @Inject
    DishRepository dishRepository;

    @GET
    public Response getAllDishes() {
        List<Dish> dishes = dishRepository.listAll();
        List<DishDTO> dishDTOS = dishRepository.entityToDTO(dishes);
        return Response.ok(dishDTOS).build();
    }

    @GET
    @Path("/{id}")
    public Response getDishById(@PathParam("id") Long id) {
        Dish dish = dishRepository.findById(id);
        if (dish == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dish).build();
    }

    @GET
    @Path("/category/{category}")
    public Response getDishesByCategory(@PathParam("category") String category) {
        List<Dish> dishes = dishRepository.findByCategory(category);
        List<DishDTO> dishDTOS = dishRepository.entityToDTO(dishes);
        return Response.ok(dishDTOS).build();
    }

    @GET
    @Path("/menu/{menuId}/available")
    public Response getAvailableDishesByMenuId(@PathParam("menuId") Long menuId) {
        List<Dish> dishes = dishRepository.findAvailableByMenuId(menuId);
        List<DishDTO> dishDTOS = dishRepository.entityToDTO(dishes);
        return Response.ok(dishDTOS).build();
    }

    @POST
    @Transactional
    public Response createDish(Dish dish) {
        dishRepository.persist(dish);
        return Response.status(Response.Status.CREATED).entity(dish).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDish(@PathParam("id") Long id, Dish dish) {
        Dish existingDish = dishRepository.findById(id);
        if (existingDish == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        dish.setId(id);
        Dish updated = dishRepository.update(dish);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDish(@PathParam("id") Long id) {
        Dish dish = dishRepository.findById(id);
        if (dish == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        dishRepository.deleteById(id);
        return Response.noContent().build();
    }
}
