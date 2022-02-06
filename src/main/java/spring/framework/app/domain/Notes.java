package spring.framework.app.domain;

import javax.persistence.*;

@Entity
public class Notes {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String RecipeNotes;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return RecipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        RecipeNotes = recipeNotes;
    }
}
