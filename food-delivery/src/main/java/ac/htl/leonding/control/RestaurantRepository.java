package ac.htl.leonding.control;

import ac.htl.leonding.entities.Restaurant;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class RestaurantRepository {

    @Inject
    EntityManager em;

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

    public RestaurantDTO entityToDTO(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getCuisine(),
                restaurant.getRating(),
                restaurant.getIsOpen()
        );
    }

    public List<RestaurantDTO> entityToDTO(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::entityToDTO)
                .toList();
    }

    public Restaurant dtoToEntity(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.id());
        restaurant.setName(dto.name());
        restaurant.setAddress(dto.address());
        restaurant.setPhone(dto.phone());
        restaurant.setCuisine(dto.cuisine());
        restaurant.setRating(dto.rating());
        restaurant.setIsOpen(dto.isOpen());
        return restaurant;
    }

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