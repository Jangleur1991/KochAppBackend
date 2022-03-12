package repository;

import de.kochAppBackend.KochAppBackend.model.RecipeRequest;
import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import de.kochAppBackend.KochAppBackend.repository.RecipeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RecipeRepositoryTest {

    private RecipeRepository testCandidate;

    @BeforeEach
    public void setUp() {
        testCandidate = new RecipeRepository();
    }

    @Test
    public void testThatFindAllWithNullTagReturnsAllRecipes() {
        //given

        //when
        List<RecipeResponse> recipes = testCandidate.findAll(null);

        //then
        assertThat(recipes).isEqualTo(testCandidate.getRecipes());

    }

    @Test
    public void testThatFindAllWithUnknownTagReturnsNoRecipe() {
        //given
        String tag = RandomStringUtils.random(10, true, false);

        //when
        List<RecipeResponse> recipes = testCandidate.findAll(tag);

        //then
        assertThat(recipes.size()).isZero();
    }

    @Test
    public void testThatFindAllWithKnowTagReturnsAtLeastOneCorrespondingRecipe() {
        //given
        RecipeResponse firstRecipe = testCandidate.getRecipes().get(0);
        //TODO: How to ensure that it has a tag???
        String tag = firstRecipe.getTags().get(0);

        //when
        List<RecipeResponse> recipes = testCandidate.findAll(tag);

        //then
        assertTrue(recipes.contains(firstRecipe));

    }

    @Test
    public void testThatFindByIdWithUnknownIdReturnOptionalEmpty() {
        //given
        String id = "0"; //TODO: How to ensure that the id doesn't exist?

        //when
        Optional<RecipeResponse> recipeResponse = testCandidate.findById(id);

        //then
        assertThat(recipeResponse).isEmpty();
    }

    @Test
    public void testThatFindByIdWithKnownIdReturnRecipe() {
        //given
        String id = testCandidate.getRecipes().get(0).getId();

        //when
        Optional<RecipeResponse> recipeResponse = testCandidate.findById(id);

        //then
        assertThat(recipeResponse.get().getId()).isEqualTo(id);
    }

    @Test
    public void testThatDeleteByIdWithUnknownIdReturnAllRecipes() {
        //given
        String id = UUID.randomUUID().toString();

        //when
        List<RecipeResponse> recipeResponses = testCandidate.deleteById(id);

        //then
        assertThat(recipeResponses).isEqualTo(testCandidate.getRecipes());
    }

    @Test
    public void testThatDeleteByIdWithKnownIdReturnAllButOneRecipes() {
        //given
        String id = testCandidate.getRecipes().get(0).getId();
        List<RecipeResponse> expectedResult = testCandidate.getRecipes().stream() //
                .skip(1) //
                .collect(Collectors.toList()); //

        //when
        List<RecipeResponse> recipeResponses = testCandidate.deleteById(id);

        //then
        assertThat(recipeResponses).isEqualTo(expectedResult);

    }

    @Test
    public void testThatSaveWithNullThrowsNPE() {
        //given
        RecipeRequest request = null;

        //then
        assertThrows(NullPointerException.class, () -> {
        //when
            testCandidate.save(request);
        });
    }

    @Test
    public void testThatSaveWithRequestNameReturnsResponseName() {
        //given
        String name = "testname";
        RecipeRequest request = new RecipeRequest(
                name,
                null,
                null,
                null,
                null
        );

        //when
        RecipeResponse result = testCandidate.save(request);

        //then
        assertThat(result.getName()).isEqualTo(name);
    }

}
