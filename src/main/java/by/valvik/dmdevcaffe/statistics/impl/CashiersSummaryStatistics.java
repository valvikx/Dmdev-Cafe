package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.entity.CashierStatisticsElement;
import by.valvik.dmdevcaffe.statistics.StatisticsPair;
import by.valvik.dmdevcaffe.statistics.SummaryStatisticBasic;

import java.util.List;

public class CashiersSummaryStatistics extends SummaryStatisticBasic<CashierStatisticsElement> {

    @Override
    public StatisticsPair<Integer, Double> getBestPerson(List<CashierStatisticsElement> statistics) {
        return getBestPerson(statistics, this::getAvgOrderCost);
    }

    private Double getAvgOrderCost(List<CashierStatisticsElement> statistics) {
        int totalOrders = 0;
        int totalCost = 0;
        for (CashierStatisticsElement element: statistics) {
            totalOrders += element.getOrdersCount();
            totalCost += element.getTotalOrdersCost();
        }
        return totalOrders > 0 ? (double) totalCost / totalOrders : 0.0;
    }

}
