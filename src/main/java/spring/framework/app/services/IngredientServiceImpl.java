package spring.framework.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.framework.app.commands.IngredientCommand;
import spring.framework.app.converters.IngredientCommandToIngredient;
import spring.framework.app.converters.IngredientToIngredientCommand;
import spring.framework.app.domain.Ingredient;
import spring.framework.app.domain.Recipe;
import spring.framework.app.repositories.RecipeRepository;
import spring.framework.app.repositories.UnitOfMeasureRespository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    public final RecipeRepository recipeRepository;
    public final IngredientToIngredientCommand ingredientToIngredientCommand;
    public final UnitOfMeasureRespository unitOfMeasureRepository;

    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, UnitOfMeasureRespository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }


    @Override
    public IngredientCommand findByRecipeIdAndId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            return null;
        }

        Optional<IngredientCommand>ingredientCommand=recipeOptional.get().getIngredient().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();


        if (!ingredientCommand.isPresent()){
            return null ;
        }
        return ingredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredient()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient ingredient=ingredientCommandToIngredient.convert(command);
                assert ingredient != null;
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> ingredient=savedRecipe.getIngredient().stream().filter(ingredient1->ingredient1.getId().equals(command.getId())).findFirst();

            if (!ingredient.isPresent()){
                ingredient=savedRecipe.getIngredient().stream()
                        .filter(ingredient1 -> ingredient1.getDescription().equals(command.getDescription()))
                        .filter(ingredient1 -> ingredient1.getAmount().equals(command.getAmount()))
                        .filter(ingredient1 -> ingredient1.getUnitOfMeasure().getId().equals(command.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(ingredient.get());
        }
    }

    @Override
    public void deleteByID(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipe=recipeRepository.findById(recipeId);

        if (recipe.isPresent()){
            Recipe recipe1=recipe.get();

            Optional<Ingredient>ingredientOptional=recipe1.getIngredient().stream()
            .filter(ingredient->ingredient.getId().equals(ingredientId)).findFirst();

            if (ingredientOptional.isPresent()){
                Ingredient ingredient=ingredientOptional.get();
                ingredient.setRecipe(null);
                recipe1.getIngredient().remove(ingredientOptional.get());
                recipeRepository.save(recipe1);
            }


        }

    }
}
