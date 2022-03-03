package de.kochAppBackend.KochAppBackend.controller;

import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import de.kochAppBackend.KochAppBackend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public List<RecipeResponse> getAllRecipes(@RequestParam(required = false) String tag) {
        return recipeRepository.findAll(tag);
    }
}
