package de.kochAppBackend.KochAppBackend.controller;

import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import de.kochAppBackend.KochAppBackend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public List<RecipeResponse> getAllRecipes(@RequestParam(required = false) String tag) {
        return recipeRepository.findAll(tag);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable String id) {
        return recipeRepository //
                .findById(id) //
                .map(ResponseEntity::ok) //StatusCode 200
                .orElseGet(() -> ResponseEntity.notFound().build()); //StatusCode 404 in case id doesn't exist
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<RecipeResponse> deleteRecipe(@PathVariable String id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // If there is no content, you return noContent 204
    }
}
