package de.kochAppBackend.KochAppBackend.service;

import de.kochAppBackend.KochAppBackend.model.RecipeRequest;
import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import de.kochAppBackend.KochAppBackend.repository.RecipeRepository;
import de.kochAppBackend.KochAppBackend.validations.RecipeValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/*
  RecipeRepository: Serves as a layer between controller and repository
 */
public class RecipeService {

    RecipeRepository recipeRepository = new RecipeRepository();


    public List<RecipeResponse> getAllRecipes(String tag) {
        return recipeRepository.findAll(tag);
    }

    public ResponseEntity<RecipeResponse> getRecipeById(String id) {
        return recipeRepository
                .findById(id) //
                .map(ResponseEntity::ok) //StatusCode 200
                .orElseGet(() -> ResponseEntity.notFound().build()); //StatusCode 404 in case id doesn't exist
    }

    public ResponseEntity<RecipeResponse> createProduct(RecipeRequest request) {
        return Optional.ofNullable(request) //
                       .filter(RecipeValidation::isValid) //
                       .map(recipeRepository::save) //
                       .map(r -> new ResponseEntity<>(r, HttpStatus.OK)) //StatusCode 200
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    public ResponseEntity<RecipeResponse> deleteRecipe(String id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // If there is no content, you return noContent 204
    }

}
