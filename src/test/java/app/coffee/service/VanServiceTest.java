package app.coffee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import app.coffee.model.Coffee;
import app.coffee.model.CoffeeBeans;
import app.coffee.model.GroundCoffee;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VanServiceTest {

    @Mock
    private DataLoader dataLoader;

    private VanService vanService;

    @BeforeEach
    public void setUp() {
        vanService = new VanService();
    }

    @Test
    public void testLoadVan_LimitsBudget() {
        List mockStock = Arrays.asList(
            new CoffeeBeans("Expensive Beans", 5000, 1.0, 2.0, 90, "Medium"),
            new GroundCoffee("Cheap Ground", 500, 1.0, 2.0, 70, "Fine")
        );
        when(dataLoader.loadInitializationData()).thenReturn(mockStock);
        
        List inventory = dataLoader.loadInitializationData();
        List loaded = vanService.loadVan(inventory, 10.0, 1000.0);
        
        assertEquals(1, loaded.size());
        assertEquals("Cheap Ground", loaded.get(0).getName());
    }

    @Test
    public void testFindCoffeeByQuality() {
        List cargo = Arrays.asList(
            new CoffeeBeans("Good", 100, 1.0, 1.0, 85, "Dark"),
            new GroundCoffee("Perfect", 200, 1.0, 1.0, 95, "Medium"),
            new CoffeeBeans("Bad", 50, 1.0, 1.0, 40, "Light")
        );

        List result = vanService.findCoffeeByQuality(cargo, 80, 100);

        assertEquals(2, result.size());
    }
}