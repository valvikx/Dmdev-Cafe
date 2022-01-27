package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.entity.Order;
import by.valvik.dmdevcaffe.entity.Visitor;
import by.valvik.dmdevcaffe.entity.VisitorStatisticsElement;
import by.valvik.dmdevcaffe.statistics.StatisticsCalculatorBasic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import static java.util.stream.Collectors.*;

public class VisitorsStatisticsCalculator extends StatisticsCalculatorBasic<VisitorStatisticsElement> {

    private final BlockingQueue<Order> completedOrders;

    public VisitorsStatisticsCalculator(BlockingQueue<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    @Override
    public List<VisitorStatisticsElement> getStatistics() {
        initStatisticsData(completedOrders);
        Map<Visitor, VisitorAggregateData> collect =
                getStatisticsData().stream()
                                   .collect(groupingBy(Order::getVisitor, collectingAndThen(toList(), this::getVisitorAggregateData)));
        return collect.entrySet()
                      .stream()
                      .map(e -> new VisitorStatisticsElement(e.getKey().id(),
                                                             e.getValue().ordersCount,
                                                             e.getValue().avgCalories,
                                                             e.getValue().avgCost))
                      .toList();
    }

    private VisitorAggregateData getVisitorAggregateData(List<Order> orders) {
        return new VisitorAggregateData(orders.size(),
                                        orders.stream().collect(averagingInt(Order::getTotalCalories)),
                                        orders.stream().collect(averagingInt(Order::getCost)));
    }

    private record VisitorAggregateData(Integer ordersCount,
                                        Double avgCalories,
                                        Double avgCost) {
    }

}
