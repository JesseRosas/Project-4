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

    /** Verifies that only three recipes can be stored and a fourth is rejected. */
    @Test
    void recipeCapacityIsThreeAndFourthIsRejected() {
        // Arrange: BVA targets the capacity boundary: the 3rd versus 4th recipe.
        Recipe a = recipe("A", 1, 1, 1, 1, 1);
        Recipe b = recipe("B", 2, 1, 1, 1, 1);
        Recipe c = recipe("C", 3, 1, 1, 1, 1);
        Recipe d = recipe("D", 4, 1, 1, 1, 1);

        // Act: attempt to add four different recipes.
        boolean addedA = maker.addRecipe(a);
        boolean addedB = maker.addRecipe(b);
        boolean addedC = maker.addRecipe(c);
        boolean addedD = maker.addRecipe(d);

        // Assert: the first three fit; the fourth is not saved.
        assertTrue(addedA);
        assertTrue(addedB);
        assertTrue(addedC);
        assertFalse(addedD);
        assertNull(maker.getRecipeForName("D").getName());
    }

    /** Verifies that deleting an existing recipe removes it and makes its slot reusable. */
    @Test
    void deleteExistingRecipeRemovesItAndFreesASlot() {
        // Arrange: add a recipe to the first slot.
        Recipe latte = recipe("Latte", 10, 2, 1, 1, 0);
        maker.addRecipe(latte);

        // Act: delete the recipe, then add a different recipe.
        boolean deleted = maker.deleteRecipe(latte);
        Recipe afterDeletion = maker.getRecipeForName("Latte");
        boolean addedReplacement = maker.addRecipe(recipe("Mocha", 12, 1, 1, 1, 2));

        // Assert: deletion works, the old name is gone, and the first slot can be reused.
        assertTrue(deleted);
        assertNull(afterDeletion.getName());
        assertTrue(addedReplacement);
        assertEquals("Mocha", maker.getRecipes()[0].getName());
    }

    /** Verifies that trying to delete a nonexistent recipe leaves existing data/recipes intact. */
    @Test
    void deletingUnknownRecipe() {
        // Arrange: store a known recipe and create one with a different name.
        Recipe latte = recipe("Latte", 10, 2, 1, 1, 0);
        maker.addRecipe(latte);
        Recipe unknown = recipe("Unknown", 1, 1, 1, 1, 1);

        // Act: attempt to delete the unknown recipe.
        boolean deleted = maker.deleteRecipe(unknown);

        // Assert: deletion fails and the original recipe remains unchanged.
        assertFalse(deleted);
        assertSame(latte, maker.getRecipeForName("Latte"));
    }

     /** Verifies that a null or unnamed recipe cannot be deleted. */
    @Test
    void deletingNullOrEmptyRecipeReturnsFalse() {
        // Arrange: ISP covers a null object and a non-null object with a null name.
        Recipe unnamed = new Recipe();

        // Act: attempt both invalid deletions.
        boolean deletedNull = maker.deleteRecipe(null);
        boolean deletedUnnamed = maker.deleteRecipe(unnamed);

        // Assert: neither deletion succeeds.
        assertFalse(deletedNull);
        assertFalse(deletedUnnamed);
    }
}