package spring.framework.app.domain;

import javax.persistence.*;
import java.util.Set;

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
    private String direction;

    @Lob
    private Byte[] image;

    @Enumerated(EnumType.STRING)
    private Difficulties difficulties;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredient;

    @ManyToMany
    @JoinTable(name="recipe_category",
    joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(Integer prep_time) {
        this.prep_time = prep_time;
    }

    public Integer getCoocking_time() {
        return coocking_time;
    }

    public void setCoocking_time(Integer coocking_time) {
        this.coocking_time = coocking_time;
    }

    public Integer getServing() {
        return serving;
    }

    public void setServing(Integer serving) {
        this.serving = serving;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Difficulties getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(Difficulties difficulties) {
        this.difficulties = difficulties;
    }

    public Set<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public Set<Category> getCategoryNames() {
        return categories;
    }

    public void setCategoryNames(Set<Category> categories) {
        this.categories = categories;
    }
}
