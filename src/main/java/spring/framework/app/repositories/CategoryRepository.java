package spring.framework.app.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.framework.app.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
