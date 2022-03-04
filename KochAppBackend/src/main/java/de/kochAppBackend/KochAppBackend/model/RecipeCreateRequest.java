package de.kochAppBackend.KochAppBackend.model;

import lombok.Getter;

import java.util.List;

@Getter
public class RecipeCreateRequest {
    private final String name;
    private final String durationInMinutes;
    private final String description;
    private final List<String> ingredients;
    private final List<String> tags;

    public RecipeCreateRequest(String name,
                               String durationInMinutes,
                               String description,
                               List<String> ingredients,
                               List<String> tags) {
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.description = description;
        this.ingredients = ingredients;
        this.tags = tags;
    }
}
