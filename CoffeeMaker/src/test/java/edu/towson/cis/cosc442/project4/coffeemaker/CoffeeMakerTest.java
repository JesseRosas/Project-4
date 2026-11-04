package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.BeforeEach;

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
}