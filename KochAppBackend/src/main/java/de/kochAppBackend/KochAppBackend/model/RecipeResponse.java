package de.kochAppBackend.KochAppBackend.model;

import lombok.Getter;

import java.util.List;

/**
 * A model object i.e. a business object without programme logic.
 */
@Getter
public class RecipeResponse {

    private final String id;
    private final String name;
    private final String durationInMinutes;
    private final String description;
    private final List<String> ingredients;
    private final List<String> tags;

    public RecipeResponse(String id,
                          String name,
                          String durationInMinutes,
                          String description,
                          List<String> ingredients,
                          List<String> tags) {
        this.id = id;
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.description = description;
        this.ingredients = ingredients;
        this.tags = tags;
    }
}
