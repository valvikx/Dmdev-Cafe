package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.TestData;
import by.valvik.dmdevcaffe.entity.Order;
import by.valvik.dmdevcaffe.entity.VisitorStatisticsElement;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.averagingInt;
import static org.junit.jupiter.api.Assertions.*;

class VisitorsStatisticsCalculatorTest {

    @Test
    void getStatisticsTest() {
        List<Order> testData = TestData.getCompletedOrders();
        ArrayBlockingQueue<Order> completedOrders = new ArrayBlockingQueue<>(testData.size(), true, testData);
        VisitorsStatisticsCalculator visitorsStatisticsCalculator = new VisitorsStatisticsCalculator(completedOrders);
        List<VisitorStatisticsElement> statistics = visitorsStatisticsCalculator.getStatistics();
        assertEquals(statistics.size(), statistics.size());
        assertTrue(statistics.stream().map(VisitorStatisticsElement::getOrdersCount).allMatch(count -> count == 1));
        assertEquals(testData.stream().collect(averagingInt(Order::getTotalCalories)),
                     statistics.stream().collect(averagingDouble(VisitorStatisticsElement::getAvgCalories)));
        assertEquals(testData.stream().collect(averagingInt(Order::getCost)),
                     statistics.stream().collect(averagingDouble(VisitorStatisticsElement::getAvgCost)));
    }

}