package de.kochAppBackend.KochAppBackend.repository;

import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Getter
public class RecipeRepository {

    //TODO: Only for test purposes
    private final List<RecipeResponse> recipes = Arrays.asList(
            new RecipeResponse(
                    "1",
                    "Spagetti",
                    "20",
                    "Mum's Spagetti",
                    Arrays.asList("Spagetti", "Tomatoes", "Salt", "Water"),
                    Arrays.asList("italian", "easy")
            ),
            new RecipeResponse(
                    "2",
                    "Hamburger",
                    "15",
                    "Big Mac",
                    Arrays.asList("Burger Patty", "Roll", "Salat", "Ketchup", "Cheese"),
                    Arrays.asList("american", "fastfood", "easy")
            )
    );

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
}
