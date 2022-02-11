package spring.framework.app.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.framework.app.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByDescription(String description);
}
