package spring.framework.app.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String decription;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipe;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Set<Recipe> getRecipes() {
        return recipe;
    }

    public void setRecipes(Set<Recipe> recipe) {
        this.recipe = recipe;
    }
}
