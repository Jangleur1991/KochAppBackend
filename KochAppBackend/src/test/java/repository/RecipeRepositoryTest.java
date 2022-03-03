package repository;

import de.kochAppBackend.KochAppBackend.model.RecipeResponse;
import de.kochAppBackend.KochAppBackend.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String tag = ""; //TODO: How to ensure that it doesn't appear as a tag in any recipe?

        //when
        List<RecipeResponse> recipes = testCandidate.findAll(tag);

        //then
        assertThat(recipes.size()).isEqualTo(0);
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
}
