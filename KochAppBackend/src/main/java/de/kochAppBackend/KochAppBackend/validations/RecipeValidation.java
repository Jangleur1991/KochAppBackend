package de.kochAppBackend.KochAppBackend.validations;

import de.kochAppBackend.KochAppBackend.model.RecipeRequest;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;


public interface RecipeValidation extends Function<RecipeRequest, Boolean> {

    static RecipeValidation nameIsNotEmpty() {
        return (RecipeRequest r) -> Optional.ofNullable(r) //
                .map(RecipeRequest::getName) //
                .map(String::trim)//
                .map(s -> !s.isEmpty())//
                .orElse(false);
    }

    static RecipeValidation durationInMinutesIsNumber() {
        return (RecipeRequest r) -> Optional.ofNullable(r) //
                .map(RecipeRequest::getDurationInMinutes)//
                .map(Misc::isNumeric)//
                .orElse(false);
    }

    static RecipeValidation ingredientsNotEmpty() {
        return (RecipeRequest r) -> Optional.ofNullable(r)//
                .map(RecipeRequest::getIngredients)
                .map(ingredients -> !ingredients.isEmpty())
                .orElse(false);
    }

    default RecipeValidation and(RecipeValidation other) {
        return (RecipeRequest r) -> this.apply(r) && other.apply(r);
    }

    static boolean isValid(RecipeRequest request) {
         return Stream.of(nameIsNotEmpty(), //
                         durationInMinutesIsNumber(), //
                         ingredientsNotEmpty()) //
                     .allMatch(v -> v.apply(request));
    }
}
