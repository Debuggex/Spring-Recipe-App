package spring.framework.app.services;

import spring.framework.app.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteByID(Long recipeId, Long ingredientId);
}
