package ac.htl.leonding.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ReviewRepository {

    @Inject
    EntityManager em;
}
