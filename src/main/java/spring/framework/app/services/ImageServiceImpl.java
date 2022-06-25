package spring.framework.app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.framework.app.domain.Recipe;
import spring.framework.app.repositories.RecipeRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {


    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeService recipeService, RecipeRepository recipeRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        try{
            Recipe recipe=recipeService.findByID(id);
            Byte bytes[]= new Byte[file.getBytes().length];

            int i=0;
            for (byte b: file.getBytes()) {
                bytes[i]=b;
                i++;
            }
            recipe.setImage(bytes);
            recipeRepository.save(recipe);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
