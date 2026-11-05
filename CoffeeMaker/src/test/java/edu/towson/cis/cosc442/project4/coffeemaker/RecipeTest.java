
package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RecipeTest {

    /** Verifies that a new Recipe starts with no name and zero-valued fields. */
    @Test
    void newRecipeHasEmptyDefaults() {
        // Arrange: create a recipe without assigning any values.
        Recipe recipe = new Recipe();

        // Act: read the initial values through the getters.
        String name = recipe.getName();
        int price = recipe.getPrice();
        int coffee = recipe.getAmtCoffee();
        int milk = recipe.getAmtMilk();
        int sugar = recipe.getAmtSugar();
        int chocolate = recipe.getAmtChocolate();

        // Assert: every field has its expected default value.
        assertNull(name);
        assertEquals(0, price);
        assertEquals(0, coffee);
        assertEquals(0, milk);
        assertEquals(0, sugar);
        assertEquals(0, chocolate);
    }

    /** Verifies that setName updates the name returned by getName and toString. */
    @Test
    void setNameAndToString() {
        // Arrange: create an unnamed recipe.
        Recipe recipe = new Recipe();

        // Act: assign a valid name, then read it in both supported ways.
        recipe.setName("Latte");
        String actualName = recipe.getName();
        String displayName = recipe.toString();

        // Assert: both methods return the assigned name.
        assertEquals("Latte", actualName);
        assertEquals("Latte", displayName);
    }

    /** Checks that negative prices become zero and nonnegative prices are preserved. */
    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 5})
    void setPriceNegative(int value) {
        // Arrange: ISP uses negative, zero, and positive inputs;
        // BVA uses -1, 0, and 1 around the minimum valid price (0).
        Recipe recipe = new Recipe();

        // Act: set the price and read back the stored value.
        recipe.setPrice(value);
        int actualPrice = recipe.getPrice();

        // Assert: negatives become 0; zero and positive values remain unchanged.
        assertEquals(Math.max(0, value), actualPrice);
    }

    /** Checks that the coffee amount handles negative and boundary inputs correctly. */
    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 5})
    void setCoffeeNegative(int value) {
        // Arrange: ISP covers negative, zero, and positive amounts;
        // BVA checks -1, 0, and 1 around the allowed minimum (0).
        Recipe recipe = new Recipe();

        // Act: set the coffee amount and retrieve the result.
        recipe.setAmtCoffee(value);
        int actualCoffee = recipe.getAmtCoffee();

        // Assert: negatives become 0; nonnegative amounts are retained.
        assertEquals(Math.max(0, value), actualCoffee);
    }

    /** Checks that the milk amount handles negative and boundary inputs correctly. */
    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 5})
    void setMilkNegative(int value) {
        // Arrange: ISP covers negative, zero, and positive amounts;
        // BVA checks -1, 0, and 1 around the allowed minimum (0).
        Recipe recipe = new Recipe();

        // Act: set the milk amount and retrieve the result.
        recipe.setAmtMilk(value);
        int actualMilk = recipe.getAmtMilk();

        // Assert: negatives become 0; nonnegative amounts are retained.
        assertEquals(Math.max(0, value), actualMilk);
    }

    /** Checks that the sugar amount handles negative and boundary inputs correctly. */
    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 5})
    void setSugarNegative(int value) {
        // Arrange: ISP covers negative, zero, and positive amounts;
        // BVA checks -1, 0, and 1 around the allowed minimum (0).
        Recipe recipe = new Recipe();

        // Act: set the sugar amount and retrieve the result.
        recipe.setAmtSugar(value);
        int actualSugar = recipe.getAmtSugar();

        // Assert: negatives become 0; nonnegative amounts are retained.
        assertEquals(Math.max(0, value), actualSugar);
    }

    /** Checks that the chocolate amount handles negative and boundary inputs correctly. */
    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 5})
    void setChocolateNegative(int value) {
        // Arrange: ISP covers negative, zero, and positive amounts;
        // BVA checks -1, 0, and 1 around the allowed minimum (0).
        Recipe recipe = new Recipe();

        // Act: set the chocolate amount and retrieve the result.
        recipe.setAmtChocolate(value);
        int actualChocolate = recipe.getAmtChocolate();

        // Assert: negatives become 0; nonnegative amounts are retained.
        assertEquals(Math.max(0, value), actualChocolate);
    }

    /** Verifies that recipes with matching names are equal even if other fields differ. */
    @Test
    void sameNameRecipes() {
        // Arrange: ISP uses matching names but different prices and ingredient amounts.
        Recipe first = new Recipe();
        first.setName("Latte");
        first.setPrice(10);
        first.setAmtCoffee(2);

        Recipe second = new Recipe();
        second.setName("Latte");
        second.setPrice(25);
        second.setAmtCoffee(5);

        // Act: compare the two recipes by the Recipe.equals(Recipe) method.
        boolean areEqual = first.equals(second);

        // Assert: equality depends on the name, not the price or ingredients.
        assertTrue(areEqual);
    }

    /** Verifies that recipes with different names are not equal. */
    @Test
    void differentNameRecipes() {
        // Arrange: ISP uses two non-null, different recipe names.
        Recipe first = new Recipe();
        first.setName("Latte");
        Recipe second = new Recipe();
        second.setName("Mocha");

        // Act: compare the recipes.
        boolean areEqual = first.equals(second);

        // Assert: different names must not compare as equal.
        assertFalse(areEqual);
    }

    /** Verifies that recipe-name comparisons are case-sensitive. */
    @Test
    void recipeNameCaseSensitive() {
        // Arrange: ISP uses names differing only in letter case.
        Recipe first = new Recipe();
        first.setName("Latte");
        Recipe second = new Recipe();
        second.setName("latte");

        // Act: compare the recipes.
        boolean areEqual = first.equals(second);

        // Assert: uppercase/lowercase differences make names unequal.
        assertFalse(areEqual);
    }

    /** Verifies that equality is false if this recipe has no name. */
    @Test
    void noNameRecipe() {
        // Arrange: ISP uses an unnamed recipe compared with a named recipe.
        Recipe first = new Recipe();
        Recipe other = new Recipe();
        other.setName("Latte");

        // Act: compare the unnamed recipe against the named one.
        boolean areEqual = first.equals(other);

        // Assert: a null name in the first recipe means not equal.
        assertFalse(areEqual);
    }

}
