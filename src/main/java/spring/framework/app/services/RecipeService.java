package spring.framework.app.services;

import spring.framework.app.commands.RecipeCommand;
import spring.framework.app.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findByID(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long valueOf);

    void deleteById(Long id);
}
