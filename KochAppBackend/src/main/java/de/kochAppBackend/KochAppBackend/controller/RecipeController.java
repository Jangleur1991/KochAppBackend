package de.kochAppBackend.KochAppBackend.controller;

import de.kochAppBackend.KochAppBackend.model.RecipeRequest;
import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import de.kochAppBackend.KochAppBackend.repository.RecipeRepository;
import de.kochAppBackend.KochAppBackend.validations.RecipeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.kochAppBackend.KochAppBackend.validations.RecipeValidation.isValid;

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

    @PostMapping("/recipes")
    public ResponseEntity<RecipeResponse> createProduct(@RequestBody RecipeRequest request) {
        if (isValid(request)) {
            recipeRepository.save(request);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<RecipeResponse> deleteRecipe(@PathVariable String id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // If there is no content, you return noContent 204
    }
}
