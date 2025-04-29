package ac.htl.leonding.control;

import ac.htl.leonding.entities.Customer;
import ac.htl.leonding.entities.Restaurant;
import ac.htl.leonding.entities.dto.CustomerDTO;
import ac.htl.leonding.entities.dto.RestaurantDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class RestaurantRepository {

    @Inject
    EntityManager em;

    @Inject
    CustomerRepository customerRepository;

    public List<Restaurant> findAllRestaurants() {
        return em.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
    }

    public Restaurant findById(Long id) {
        return em.find(Restaurant.class, id);
    }

    public List<Restaurant> findByName(String name) {
        return em.createQuery("SELECT r FROM Restaurant r WHERE r.name LIKE :name", Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public List<Restaurant> findByRating(String minRating) {
        return em.createQuery("SELECT r FROM Restaurant r WHERE r.rating >= :minRating", Restaurant.class)
                .setParameter("minRating", minRating)
                .getResultList();
    }

    public List<Restaurant> findByRatingAbove(Double minRating) {
        return em.createQuery("SELECT r FROM Restaurant r WHERE CAST(r.rating AS double) > :minRating", Restaurant.class)
                .setParameter("minRating", minRating)
                .getResultList();
    }

    public List<Object[]> findRestaurantsWithHighAverageRating() {
        return em.createQuery("SELECT r, AVG(CAST(rv.rating AS double)) " +
                        "FROM Restaurant r JOIN r.reviews rv " +
                        "GROUP BY r.id " +
                        "HAVING AVG(CAST(rv.rating AS double)) > 4")
                .getResultList();
    }


    public RestaurantDTO entityToDTO(Restaurant restaurant) {
     return new RestaurantDTO(restaurant.getName(),
             restaurant.getAddress(),
             restaurant.getDescription(),
             restaurant.getRating(),
             restaurant.getRestaurantOwner().getId());
    }

    public List<RestaurantDTO> entityToDTO(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::entityToDTO)
                .toList();
    }

//    public Restaurant dtoToEntity(RestaurantDTO dto) {
//        Restaurant restaurant = new Restaurant();
//        restaurant.setName(dto.name());
//        restaurant.setAddress(dto.address());
//        restaurant.setDescription(dto.description());
//        restaurant.setRating(dto.rating());
//        restaurant.setRestaurantOwner();
//    }

    @Transactional
    public void save(Restaurant restaurant) {
        em.persist(restaurant);
    }

    @Transactional
    public Restaurant update(Restaurant restaurant) {
        return em.merge(restaurant);
    }

    @Transactional
    public void delete(Restaurant restaurant) {
        em.remove(em.contains(restaurant) ? restaurant : em.merge(restaurant));
    }

    @Transactional
    public void deleteById(Long id) {
        Restaurant entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
    }
}