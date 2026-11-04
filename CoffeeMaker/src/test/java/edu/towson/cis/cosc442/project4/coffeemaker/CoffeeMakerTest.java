package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoffeeMakerTest {
    private CoffeeMaker maker;

    /** Creates a fresh CoffeeMaker before every test for independent results. */
    @BeforeEach
    void setUp() {
        maker = new CoffeeMaker();
    }

    /** Builds a recipe with a name, price, and ingredient amounts for the tests. */
    private Recipe recipe(String name, int price, int coffee, int milk, int sugar, int chocolate) {
        Recipe r = new Recipe();
        r.setName(name);
        r.setPrice(price);
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setAmtChocolate(chocolate);
        return r;
    }

    /** Verifies that a new CoffeeMaker has three empty recipe slots and default inventory. */
    @Test
    void newMachineHasThreeEmptyRecipeSlotsAndInitialInventory() {
        // Arrange: @BeforeEach created a new CoffeeMaker.

        // Act: get its recipe slots and inventory.
        Recipe[] recipes = maker.getRecipes();
        Inventory inventory = maker.checkInventory();

        // Assert: three slots are unnamed, and all ingredient amounts start at 15.
        assertEquals(3, recipes.length);
        for (Recipe stored : recipes) {
            assertNull(stored.getName());
        }
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

     /** Verifies that adding a recipe places it in the first open slot. */
    @Test
    void addRecipeStoresItInFirstAvailableSlot() {
        // Arrange: create a valid recipe for an empty CoffeeMaker.
        Recipe latte = recipe("Latte", 10, 2, 1, 1, 0);

        // Act: add the recipe and look it up by name.
        boolean added = maker.addRecipe(latte);
        Recipe found = maker.getRecipeForName("Latte");

        // Assert: adding succeeds and both lookups reference the same recipe.
        assertTrue(added);
        assertSame(latte, maker.getRecipes()[0]);
        assertSame(latte, found);
    }

    /** Verifies that recipes cannot share a name even when their other fields differ. */
    @Test
    void duplicateRecipeNameCannotBeAdded() {
        // Arrange: ISP uses equal names but different prices and ingredient amounts.
        Recipe first = recipe("Latte", 10, 2, 1, 1, 0);
        Recipe duplicate = recipe("Latte", 25, 4, 2, 3, 1);
        boolean firstAdded = maker.addRecipe(first);

        // Act: attempt to add another recipe with the same name.
        // Doesn't matter if the duplicate recipe has different details, it should still fail to be added.
        boolean duplicateAdded = maker.addRecipe(duplicate);
        Recipe stored = maker.getRecipeForName("Latte");

        // Assert: the first addition succeeds, the duplicate fails, and original data remains.
        assertTrue(firstAdded);
        assertFalse(duplicateAdded);
        assertEquals(10, stored.getPrice());
    }
}