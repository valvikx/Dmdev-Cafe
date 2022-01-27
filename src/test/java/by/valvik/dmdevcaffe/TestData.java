package by.valvik.dmdevcaffe;

import by.valvik.dmdevcaffe.entity.*;
import by.valvik.dmdevcaffe.util.OrderUtils;

import java.util.List;

public class TestData {

    public static List<Dish> getMenu() {
        return List.of(new Dish("A", 10, 1),
                       new Dish("B", 20, 2),
                       new Dish("C", 30, 3),
                       new Dish("D", 40, 4),
                       new Dish("E", 50, 5));
    }

    public static List<Order> getCompletedOrders() {
        Visitor visitor1 = new Visitor();
        Visitor visitor2 = new Visitor();
        Visitor visitor3 = new Visitor();
        Visitor visitor4 = new Visitor();
        Visitor visitor5 = new Visitor();
        Cashier cashier1 = new Cashier();
        Cashier cashier2 = new Cashier();
        return List.of(new Order(1, visitor1, cashier1, OrderUtils.generateDishesInOrder(getMenu(), 5), true),
                       new Order(2, visitor2, cashier2, OrderUtils.generateDishesInOrder(getMenu(), 5), true),
                       new Order(3, visitor3, cashier1, OrderUtils.generateDishesInOrder(getMenu(), 5), true),
                       new Order(4, visitor4, cashier2, OrderUtils.generateDishesInOrder(getMenu(), 5), true),
                       new Order(5, visitor5, cashier1, OrderUtils.generateDishesInOrder(getMenu(), 5), true));
    }

    public static List<CashierStatisticsElement> getCashiersStatistics() {
        return List.of(new CashierStatisticsElement(1, 1, 10),
                       new CashierStatisticsElement(1, 2, 20),
                       new CashierStatisticsElement(2, 1, 15),
                       new CashierStatisticsElement(2, 2, 30),
                       new CashierStatisticsElement(3, 2, 50));
    }

    public static List<VisitorStatisticsElement> getVisitorsStatistics() {
        return List.of(new VisitorStatisticsElement(1, 1, 10.1, 10.1),
                       new VisitorStatisticsElement(1, 1, 20.2, 20.2),
                       new VisitorStatisticsElement(2, 1, 15.1, 15.1),
                       new VisitorStatisticsElement(2, 2, 2.2, 2.2),
                       new VisitorStatisticsElement(3, 2, 5.5, 5.5));
    }

}