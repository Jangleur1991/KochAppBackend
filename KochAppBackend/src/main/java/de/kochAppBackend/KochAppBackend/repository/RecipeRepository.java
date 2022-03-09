package de.kochAppBackend.KochAppBackend.repository;

import de.kochAppBackend.KochAppBackend.model.RecipeRequest;
import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Getter
public class RecipeRepository {

    //TODO: Only for test purposes
    private List<RecipeResponse> recipes = new ArrayList<>(Arrays.asList(
            new RecipeResponse.Builder().with(b -> {
                b.id = "1";
                b.name = "Spagetti";
                b.durationInMinutes = "20";
                b.description = "Mum's Spagetti";
                b.ingredients = Arrays.asList("Spagetti", "Tomatoes", "Salt", "Water");
                b.tags = Arrays.asList("italian", "easy");
            }).build(),
            new RecipeResponse.Builder().with(b -> {
                b.id = "2";
                b.name = "Hamburger";
                b.durationInMinutes = "15";
                b.description = "Big Mac";
                b.ingredients = Arrays.asList("Burger Patty", "Roll", "Salat", "Ketchup", "Cheese");
                b.tags = Arrays.asList("american", "fastfood", "easy");
            }).build()
    ));

    public List<RecipeResponse> findAll(String tag) {
        if (null == tag) {
            return recipes;
        } else {
            String lowercaseTag = tag.toLowerCase();
            return recipes.stream() //
                    .filter(r -> lowercaseTags(r).contains(lowercaseTag))
                    .collect(Collectors.toList());
        }
    }

    private List<String> lowercaseTags(RecipeResponse recipeResponse) {
        return recipeResponse.getTags().stream()//
                .map(String::toLowerCase)//
                .collect(Collectors.toList());
    }

    public Optional<RecipeResponse> findById(String id) {
        return recipes.stream() //
                .filter(r -> r.getId().equals(id)) //
                .findFirst();
    }

    //TODO: Remove sideeffect later...
    public List<RecipeResponse> deleteById(String id) {
        this.recipes = recipes.stream() //
                .filter(r -> !r.getId().equals(id)) //
                .collect(Collectors.toList());
        return this.recipes;
    }

    public RecipeResponse save(RecipeRequest request) {
        RecipeResponse response = new RecipeResponse.Builder()
                .with(b -> {
                      b.id = UUID.randomUUID().toString();
                      b.name = request.getName();
                      b.durationInMinutes = request.getDurationInMinutes();
                      b.description = request.getDescription();
                      b.ingredients =  request.getIngredients();
                      b.tags = request.getTags();
                }).build();

        this.recipes.add(response);
        return response;
    }
}
