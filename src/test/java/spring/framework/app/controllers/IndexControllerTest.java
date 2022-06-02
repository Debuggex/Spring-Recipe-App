package spring.framework.app.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import spring.framework.app.services.RecipeService;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        controller=new IndexController(recipeService);
    }

    @Test
    void indexPage() {

        String viewname=controller.IndexPage(model);
        assertEquals("index", viewname);
        Mockito.verify(recipeService,Mockito.times(1)).getRecipes();
        Mockito.verify(model, Mockito.times(1)).addAttribute(ArgumentMatchers.eq("recipes"), ArgumentMatchers.anySet());
    }
}