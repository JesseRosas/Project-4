
package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

public class InventoryTest {

    private Inventory inventory;
    private Item sword;
    private Item shield;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(2);
        sword = new Item('/', Color.GRAY, "Sword", "Sword");
        shield = new Item('[', Color.BLUE, "Shield", "Shield");
    }

    @Test
    void newInventoryStartsEmpty() {
        assertEquals(2, inventory.getItems().length);
        assertNull(inventory.get(0));
        assertNull(inventory.get(1));
        assertFalse(inventory.isFull());
    }

    @Test
    void addItemToEmptyInventory() {
        inventory.add(sword);

        assertSame(sword, inventory.get(0));
        assertTrue(inventory.contains(sword));
        assertFalse(inventory.isFull());
    }

    @Test
    void addingTwoItemsFillsInventory() {
        inventory.add(sword);
        inventory.add(shield);

        assertSame(sword, inventory.get(0));
        assertSame(shield, inventory.get(1));
        assertTrue(inventory.isFull());
    }

    @Test
    void addingItemToFullInventoryDoesNothing() {
        Item potion = new Item('!', Color.RED, "Potion", "Potion");

        inventory.add(sword);
        inventory.add(shield);
        inventory.add(potion);

        assertSame(sword, inventory.get(0));
        assertSame(shield, inventory.get(1));
        assertFalse(inventory.contains(potion));
        assertTrue(inventory.isFull());
    }

    @Test
    void removeExistingItem() {
        inventory.add(sword);
        inventory.add(shield);

        inventory.remove(sword);

        assertNull(inventory.get(0));
        assertSame(shield, inventory.get(1));
        assertFalse(inventory.contains(sword));
        assertFalse(inventory.isFull());
    }

    @Test
    void removingNonexistentItemChangesNothing() {
        inventory.add(sword);

        inventory.remove(shield);

        assertSame(sword, inventory.get(0));
        assertTrue(inventory.contains(sword));
    }

    @Test
    void addedItemUsesFirstAvailableSlot() {
        inventory.add(sword);
        inventory.add(shield);
        inventory.remove(sword);

        Item potion = new Item('!', Color.RED, "Potion", "Potion");
        inventory.add(potion);

        assertSame(potion, inventory.get(0));
        assertSame(shield, inventory.get(1));
    }

    @Test
    void containsReturnsFalseForMissingItem() {
        inventory.add(sword);

        assertFalse(inventory.contains(shield));
        assertTrue(inventory.contains(sword));
    }

    @Test
    void zeroCapacityInventoryIsFull() {
        Inventory emptyInventory = new Inventory(0);

        assertEquals(0, emptyInventory.getItems().length);
        assertTrue(emptyInventory.isFull());
        assertFalse(emptyInventory.contains(sword));
    }

    @Test
    void removingItemFromEmptyInventoryDoesNothing() {
        inventory.remove(sword);

        assertNull(inventory.get(0));
        assertNull(inventory.get(1));
        assertFalse(inventory.isFull());
    }
}
