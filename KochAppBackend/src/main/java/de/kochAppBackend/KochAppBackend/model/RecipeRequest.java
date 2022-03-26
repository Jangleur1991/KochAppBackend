package de.kochAppBackend.KochAppBackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecipeRequest {
    private String name;
    private String durationInMinutes;
    private String description;
    private List<String> ingredients;
    private List<String> tags;

    public RecipeRequest(String name,
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
