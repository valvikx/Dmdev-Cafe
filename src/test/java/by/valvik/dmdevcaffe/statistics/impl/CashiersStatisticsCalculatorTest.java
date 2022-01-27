package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.TestData;
import by.valvik.dmdevcaffe.entity.CashierStatisticsElement;
import by.valvik.dmdevcaffe.entity.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class CashiersStatisticsCalculatorTest {

    @Test
    void getStatisticsTest() {
        List<Order> testData = TestData.getCompletedOrders();
        ArrayBlockingQueue<Order> completedOrders = new ArrayBlockingQueue<>(testData.size(), true, testData);
        CashiersStatisticsCalculator cashiersStatisticsCalculator = new CashiersStatisticsCalculator(completedOrders);
        List<CashierStatisticsElement> statistics = cashiersStatisticsCalculator.getStatistics();
        assertEquals(statistics.size(),
                     testData.stream().mapToInt(order -> order.getCashier().id()).max().getAsInt());
        assertEquals(statistics.stream().mapToInt(CashierStatisticsElement::getOrdersCount).sum(),
                     testData.size());
        assertEquals(testData.stream().mapToInt(Order::getCost).sum(),
                     statistics.stream().mapToInt(CashierStatisticsElement::getTotalOrdersCost).sum());
    }

}