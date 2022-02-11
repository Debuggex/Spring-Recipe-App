package spring.framework.app.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;
    private Integer prep_time;
    private Integer coocking_time;
    private Integer serving;
    private String source;
    private String url;

    @Lob
    private String direction;

    @Lob
    private Byte[] image;

    @Enumerated(EnumType.STRING)
    private Difficulties difficulties;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredient=new HashSet<>();

    @ManyToMany
    @JoinTable(name="recipe_category",
    joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories=new HashSet<>();


    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrep_time(Integer prep_time) {
        this.prep_time = prep_time;
    }


    public void setCoocking_time(Integer coocking_time) {
        this.coocking_time = coocking_time;
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }


    public void setDifficulties(Difficulties difficulties) {
        this.difficulties = difficulties;
    }


    public Set<Ingredient> getIngredient() {
        return ingredient;
    }


    public Set<Category> getCategoryNames() {
        return categories;
    }

}
