package de.kochAppBackend.KochAppBackend.model;

import lombok.Data;

import java.util.List;
import java.util.function.Consumer;

/**
 * A model object i.e. a business object without programme logic.
 */
@Data
public class RecipeResponse {

    private final String id;
    private final String name;
    private final String durationInMinutes;
    private final String description;
    private final List<String> ingredients;
    private final List<String> tags;

    public static class Builder {
        public String id;
        public String name;
        public String durationInMinutes;
        public String description;
        public List<String> ingredients;
        public List<String> tags;

        public Builder with(Consumer<Builder> builderConsumer) {
            builderConsumer.accept(this);
            return this;
        }

        public RecipeResponse build() {
            return new RecipeResponse(this);
        }
    }

    private RecipeResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.durationInMinutes = builder.durationInMinutes;
        this.description = builder.description;
        this.ingredients = builder.ingredients;
        this.tags = builder.tags;
    }

}
