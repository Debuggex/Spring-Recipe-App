package spring.framework.app.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String RecipeNotes;

    public Notes() {
    }

    public void setRecipeNotes(String recipeNotes) {
        RecipeNotes = recipeNotes;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Notes;
    }

}
