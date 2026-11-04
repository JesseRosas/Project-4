package edu.towson.cis.cosc442.project4.coffeemaker;

public class Recipe {
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;

    /**
     * Returns the amount of chocolate used in the recipe.
     * 
     * @return The amount of chocolate as an integer.
     */
    public int getAmtChocolate() {
        return amtChocolate;
    }

    /**
     * Sets the amount of chocolate used in the recipe, defaulting to 0 if a
     * negative value is given.
     * 
     * @param amtChocolate The amount of chocolate to set.
     */
    public void setAmtChocolate(int amtChocolate) {
        if (amtChocolate >= 0) {
            this.amtChocolate = amtChocolate;
        } else {
            this.amtChocolate = 0;
        }
    }

    /**
     * Returns the amount of coffee used in the recipe.
     * 
     * @return The amount of coffee as an integer.
     */
    public int getAmtCoffee() {
        return amtCoffee;
    }

    /**
     * Sets the amount of coffee used in the recipe, defaulting to 0 if a negative
     * value is given.
     * 
     * @param amtCoffee The amount of coffee to set.
     */
    public void setAmtCoffee(int amtCoffee) {
        if (amtCoffee >= 0) {
            this.amtCoffee = amtCoffee;
        } else {
            this.amtCoffee = 0;
        }
    }

    /**
     * Returns the amount of milk used in the recipe.
     * 
     * @return The amount of milk as an integer.
     */
    public int getAmtMilk() {
        return amtMilk;
    }

    /**
     * Sets the amount of milk used in the recipe, defaulting to 0 if a negative
     * value is given.
     * 
     * @param amtMilk The amount of milk to set.
     */
    public void setAmtMilk(int amtMilk) {
        if (amtMilk >= 0) {
            this.amtMilk = amtMilk;
        } else {
            this.amtMilk = 0;
        }
    }

    /**
     * Returns the amount of sugar used in the recipe.
     * 
     * @return The amount of sugar as an integer.
     */
    public int getAmtSugar() {
        return amtSugar;
    }

    /**
     * Sets the amount of sugar used in the recipe, defaulting to 0 if a negative
     * value is given.
     * 
     * @param amtSugar The amount of sugar to set.
     */
    public void setAmtSugar(int amtSugar) {
        if (amtSugar >= 0) {
            this.amtSugar = amtSugar;
        } else {
            this.amtSugar = 0;
        }
    }

    /**
     * Returns the name of the recipe.
     * 
     * @return The name of the recipe as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the recipe.
     * 
     * @param name The name to assign to the recipe.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the recipe.
     * 
     * @return The price as an integer.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the recipe, defaulting to 0 if a negative value is given.
     * 
     * @param price The price to set.
     */
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }

    /**
     * Determines if this recipe has the same name as another recipe, returning
     * false if either name is null.
     * 
     * @param r The recipe to compare against.
     * @return True if both recipes have non-null equal names; false otherwise.
     */
    public boolean equals(Recipe r) {
        if (r.getName() == null) {
            return false;
        }
        if (this.name == null) {
            return false;
        }
        if ((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }

    /**
     * Returns the name of the recipe as a string representation.
     * 
     * @return The name of the recipe as a String.
     */
    public String toString() {
        return name;
    }
}
