package by.valvik.dmdevcaffe.util;

import by.valvik.dmdevcaffe.entity.Dish;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public final class OrderUtils {

    private static final RandomGenerator RANDOM_GENERATOR = new Random();

    private OrderUtils() {
    }

    public static Map<Dish, Integer> generateDishesInOrder(List<Dish> menu, int maxNumberOfEachDish) {
        int numberOfDishes = RANDOM_GENERATOR.nextInt(menu.size()) + 1;
        List<Dish> dishes = RANDOM_GENERATOR.ints(numberOfDishes, 0, menu.size())
                                            .mapToObj(menu::get)
                                            .toList();
        return dishes.stream()
                     .collect(toMap(identity(),
                                    value -> RANDOM_GENERATOR.nextInt(maxNumberOfEachDish) + 1,
                                    (existingNode, newNode) -> existingNode));
    }

}
