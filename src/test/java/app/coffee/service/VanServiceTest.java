package app.coffee.service;

import app.coffee.model.Coffee;
import app.coffee.model.CoffeeBeans;
import app.coffee.model.GroundCoffee;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VanServiceTest {

    private final VanService vanService = new VanService();

    @Test
    public void testLoadVan_LimitsBudget() {
        List<Coffee> stock = Arrays.asList(
            new CoffeeBeans("Expensive Beans", 5000, 1.0, 2.0, 90, "Medium"),
            new GroundCoffee("Cheap Ground", 500, 1.0, 2.0, 70, "Fine")
        );

        List<Coffee> loaded = vanService.loadVan(stock, 10.0, 1000.0);

        assertEquals(1, loaded.size());
        assertEquals("Cheap Ground", loaded.get(0).getName());
    }

    @Test
    public void testFindCoffeeByQuality() {
        List<Coffee> stock = Arrays.asList(
            new CoffeeBeans("High Quality", 500, 1.0, 2.0, 95, "Medium"),
            new GroundCoffee("Low Quality", 500, 1.0, 2.0, 60, "Fine")
        );

        List<Coffee> result = vanService.findCoffeeByQuality(stock, 80, 100);

        assertEquals(1, result.size());
        assertEquals("High Quality", result.get(0).getName());
    }
}
