package edu.towson.cis.cosc442.project4.coffeemaker;

public class Inventory {

    private static int coffee;
    private static int milk;
    private static int sugar;
    private static int chocolate;

    /**
     * Constructs an Inventory with default amounts of 15 units for coffee, milk,
     * sugar, and chocolate.
     */
    public Inventory() {
        setCoffee(15);
        setMilk(15);
        setSugar(15);
        setChocolate(15);
    }

    /**
     * Returns the current amount of chocolate in the inventory.
     * 
     * @return the quantity of chocolate available
     */
    public int getChocolate() {
        return chocolate;
    }

    /**
     * Sets the amount of chocolate in the inventory, ensuring it is not negative.
     * 
     * @param chocolate the new quantity of chocolate to set
     */
    public void setChocolate(int chocolate) {
        if (chocolate >= 0) {
            Inventory.chocolate = chocolate;
        } else {
            Inventory.chocolate = 0;
        }

    }

    /**
     * Returns the current amount of coffee in the inventory.
     * 
     * @return the quantity of coffee available
     */
    public int getCoffee() {
        return coffee;
    }

    /**
     * Sets the amount of coffee in the inventory, ensuring it is not negative.
     * 
     * @param coffee the new quantity of coffee to set
     */
    public void setCoffee(int coffee) {
        if (coffee >= 0) {
            Inventory.coffee = coffee;
        } else {
            Inventory.coffee = 0;
        }
    }

    /**
     * Returns the current amount of milk in the inventory.
     * 
     * @return the quantity of milk available
     */
    public int getMilk() {
        return milk;
    }

    /**
     * Sets the amount of milk in the inventory, ensuring it is not negative.
     * 
     * @param milk the new quantity of milk to set
     */
    public void setMilk(int milk) {
        if (milk >= 0) {
            Inventory.milk = milk;
        } else {
            Inventory.milk = 0;
        }
    }

    /**
     * Returns the current amount of sugar in the inventory.
     * 
     * @return the quantity of sugar available
     */
    public int getSugar() {
        return sugar;
    }

    /**
     * Sets the amount of sugar in the inventory, ensuring it is not negative.
     * 
     * @param sugar the new quantity of sugar to set
     */
    public void setSugar(int sugar) {
        if (sugar >= 0) {
            Inventory.sugar = sugar;
        } else {
            Inventory.sugar = 0;
        }
    }

    /**
     * Determines if the inventory has enough ingredients to make the specified
     * recipe.
     * 
     * @param r the Recipe to check ingredient adequacy for
     * @return true if the inventory contains sufficient ingredients; false
     *         otherwise
     */
    public boolean enoughIngredients(Recipe r) {
        boolean isEnough = true;
        if (Inventory.coffee < r.getAmtCoffee()) {
            isEnough = false;
        }
        if (Inventory.milk < r.getAmtMilk()) {
            isEnough = false;
        }
        if (Inventory.sugar < r.getAmtSugar()) {
            isEnough = false;
        }
        if (Inventory.chocolate < r.getAmtChocolate()) {
            isEnough = false;
        }
        return isEnough;
    }

    /**
     * Returns a string representation of the inventory quantities for all
     * ingredients.
     * 
     * @return a string listing the amounts of coffee, milk, sugar, and chocolate
     */
    public String toString() {
        return "Coffee: " + getCoffee() + "\n" +
                "Milk: " + getMilk() + "\n" +
                "Sugar: " + getSugar() + "\n" +
                "Chocolate: " + getChocolate() + "\n";
    }
}
