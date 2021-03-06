package spring.framework.app.commands;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.framework.app.domain.Difficulties;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookingTime;
    private Integer serving;
    private String source;
    private String url;
    private String direction;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulties difficulties;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Byte[] image;
}
