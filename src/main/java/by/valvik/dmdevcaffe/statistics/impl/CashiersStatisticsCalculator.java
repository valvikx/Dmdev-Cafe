package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.entity.Cashier;
import by.valvik.dmdevcaffe.entity.CashierStatisticsElement;
import by.valvik.dmdevcaffe.entity.Order;
import by.valvik.dmdevcaffe.statistics.StatisticsCalculatorBasic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import static java.util.stream.Collectors.*;

public class CashiersStatisticsCalculator extends StatisticsCalculatorBasic<CashierStatisticsElement> {

    private final BlockingQueue<Order> completedOrders;

    public CashiersStatisticsCalculator(BlockingQueue<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    @Override
    public List<CashierStatisticsElement> getStatistics() {
        initStatisticsData(completedOrders);
        Map<Cashier, CashierAggregateData> collect =
                getStatisticsData().stream()
                                   .collect(groupingBy(Order::getCashier, collectingAndThen(toList(), this::getCashierAggregateData)));
        return collect.entrySet()
                      .stream()
                      .map(e -> new CashierStatisticsElement(e.getKey().id(),
                                                             e.getValue().ordersCount,
                                                             e.getValue().totalOrdersCost))
                      .toList();
    }

    private CashierAggregateData getCashierAggregateData(List<Order> orders) {
        return new CashierAggregateData(orders.size(), orders.stream().mapToInt(Order::getCost).sum());
    }

    private record CashierAggregateData(Integer ordersCount, Integer totalOrdersCost) {
    } 

}
