package de.kochAppBackend.KochAppBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.kochAppBackend.KochAppBackend.model.RecipeRequest;
import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import de.kochAppBackend.KochAppBackend.repository.RecipeRepository;
import de.kochAppBackend.KochAppBackend.service.RecipeService;

/*
    RestController: Process requests and generate responses. Part of the WebLayer!
 */
@RestController
public class RecipeController {

    private final RecipeRepository recipeRepository;

    RecipeService recipeService = new RecipeService();

    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes")
    public List<RecipeResponse> getAllRecipes(@RequestParam(required = false) String tag) {
        return recipeService.getAllRecipes(tag);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable String id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping("/recipes")
    public ResponseEntity<RecipeResponse> createProduct(@RequestBody RecipeRequest request) {
        return recipeService.createProduct(request);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<RecipeResponse> deleteRecipe(@PathVariable String id) {
        return recipeService.deleteRecipe(id); // If there is no content, you return noContent 204
    }
}
