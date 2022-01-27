package by.valvik.dmdevcaffe;

import by.valvik.dmdevcaffe.entity.Dish;

import java.util.List;

public class AppRunner {

    public static void main(String[] args) {
        List<Dish> menu = List.of(new Dish("IT Steak", 500, 10),
                                  new Dish("Legacy Salad", 50, 5),
                                  new Dish("Switch Potato", 300, 3),
                                  new Dish("Debug Cola", 25, 2),
                                  new Dish("Script Ice Cream", 150, 4));
        DmdevCafe dmdevCafe = new DmdevCafe(menu);
        dmdevCafe.open();
    }

}
