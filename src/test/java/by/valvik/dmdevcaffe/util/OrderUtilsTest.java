package by.valvik.dmdevcaffe.util;

import by.valvik.dmdevcaffe.TestData;
import by.valvik.dmdevcaffe.entity.Dish;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderUtilsTest {

    @Test
    void generateDishesInOrderTest() {
        List<Dish> menu = TestData.getMenu();
        int maxNumberOfEachDish = 5;
        Map<Dish, Integer> dishes = OrderUtils.generateDishesInOrder(menu, maxNumberOfEachDish);
        assertTrue(dishes.size() <= menu.size());
        assertTrue(dishes.values().stream().allMatch(value -> value <= maxNumberOfEachDish));
    }

}