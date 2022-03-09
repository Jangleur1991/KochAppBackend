package de.kochAppBackend.KochAppBackend.validations;

import de.kochAppBackend.KochAppBackend.model.RecipeRequest;

import java.util.function.Function;
import java.util.stream.Stream;

import static de.kochAppBackend.KochAppBackend.validations.Misc.isNumeric;


public interface RecipeValidation extends Function<RecipeRequest, Boolean> {

    static RecipeValidation nameIsNotEmpty() {
        return (RecipeRequest r) -> !r.getName().trim().isEmpty();
    }

    static RecipeValidation durationInMinutesIsNumber() {
        return (RecipeRequest r) -> isNumeric(r.getDurationInMinutes());
    }

    static RecipeValidation ingredientsNotEmpty() {
        return (RecipeRequest r) -> !r.getIngredients().isEmpty();
    }

    static boolean isValid(RecipeRequest request) {
        return Stream.of(nameIsNotEmpty(), //
                         durationInMinutesIsNumber(), //
                         ingredientsNotEmpty()) //
                     .allMatch(v -> v.apply(request));
    }
}
