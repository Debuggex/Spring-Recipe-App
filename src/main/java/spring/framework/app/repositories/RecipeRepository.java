package spring.framework.app.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.framework.app.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
