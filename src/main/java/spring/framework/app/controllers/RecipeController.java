package spring.framework.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.framework.app.commands.RecipeCommand;
import spring.framework.app.services.RecipeService;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"recipe/show/{id}"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findByID(Long.parseLong(id)));
        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());

        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeForm";
    }

    @PostMapping(path = "/")
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand=recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/"+savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("recipe/delete/{id}")
    public String saveOrUpdate(@PathVariable String id){

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }


}
