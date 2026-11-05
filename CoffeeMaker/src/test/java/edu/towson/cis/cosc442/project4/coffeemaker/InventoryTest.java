package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {
    private Inventory inventory;

    /** Creates fresh inventory before each test. */
    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    /** Verifies that a new inventory correctly starts with 15 units of each ingredient. */
    @Test
    void initialInventory() {
        // Arrange: @BeforeEach created a new inventory.

        // Act: retrieve the initial ingredient quantities.
        int coffee = inventory.getCoffee();
        int milk = inventory.getMilk();
        int sugar = inventory.getSugar();
        int chocolate = inventory.getChocolate();

        // Assert: each ingredient starts at 15 units.
        assertEquals(15, coffee);
        assertEquals(15, milk);
        assertEquals(15, sugar);
        assertEquals(15, chocolate);
    }

    /** Verifies that all ingredient setters handle negative and boundary values. */
    @Test
    void ingredientBoundaryValues() {
        // BVA checks -1, 0, and 1 around the minimum allowed value (0).
        int[] testValues = {-5, -1, 0, 1, 20};

        for (int value : testValues) {
            // Act: set all ingredient quantities to the test value.
            inventory.setCoffee(value);
            inventory.setMilk(value);
            inventory.setSugar(value);
            inventory.setChocolate(value);

            // Assert: negative values become 0; otherwise values are preserved.
            int expected = Math.max(0, value);

            assertAll("Ingredient setters with input " + value,
                () -> assertEquals(expected, inventory.getCoffee(), "coffee"),
                () -> assertEquals(expected, inventory.getMilk(), "milk"),
                () -> assertEquals(expected, inventory.getSugar(), "sugar"),
                () -> assertEquals(expected, inventory.getChocolate(), "chocolate")
            );
        }
    }

    /** Creates a recipe with the specified ingredient requirements. */
    private Recipe recipe(int coffee, int milk, int sugar, int chocolate) {
        Recipe r = new Recipe();
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setAmtChocolate(chocolate);
        return r;
    }

    /** Verifies that exactly the required ingredient quantities are sufficient. */
    @Test
    void exactQuantity() {
        // Arrange: BVA checks 15 required against 15 available.
        Recipe r = recipe(15, 15, 15, 15);

        // Act: check if inventory can make the recipe.
        boolean enough = inventory.enoughIngredients(r);

        // Assert: exact quantities satisfy the recipe.
        assertTrue(enough);
    }

    /** Verifies that a shortage of any single ingredient prevents making a recipe. */
    @Test
    void tooLittleIngredients() {
        // Arrange: BVA requires 16 units, one more than the 15 available.
        // ISP checks a shortage of each ingredient individually.
        Recipe tooLittleCoffee = recipe(16, 15, 15, 15);
        Recipe tooLittleMilk = recipe(15, 16, 15, 15);
        Recipe tooLittleSugar = recipe(15, 15, 16, 15);
        Recipe tooLittleChocolate = recipe(15, 15, 15, 16);

        // Act: check availability for each recipe.
        boolean coffeeAvailable = inventory.enoughIngredients(tooLittleCoffee);
        boolean milkAvailable = inventory.enoughIngredients(tooLittleMilk);
        boolean sugarAvailable = inventory.enoughIngredients(tooLittleSugar);
        boolean chocolateAvailable = inventory.enoughIngredients(tooLittleChocolate);

        // Assert: any single ingredient shortage makes the recipe unavailable.
        assertAll("Shortage of each ingredient",
            () -> assertFalse(coffeeAvailable, "coffee shortage"),
            () -> assertFalse(milkAvailable, "milk shortage"),
            () -> assertFalse(sugarAvailable, "sugar shortage"),
            () -> assertFalse(chocolateAvailable, "chocolate shortage")
        );
    }

    /** Verifies that toString displays each ingredient and its current quantity. */
    @Test
    void inventoryString() {
        // Arrange: assign different quantities to each ingredient.
        inventory.setCoffee(8);
        inventory.setMilk(9);
        inventory.setSugar(10);
        inventory.setChocolate(11);

        // Act: get the inventory's string representation.
        String description = inventory.toString();

        // Assert: verify ingredient names, values, and order.
        assertEquals(
            "Coffee: 8\nMilk: 9\nSugar: 10\nChocolate: 11\n",
            description
        );
    }
}
