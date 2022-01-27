package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.entity.VisitorStatisticsElement;
import by.valvik.dmdevcaffe.statistics.StatisticsPair;
import by.valvik.dmdevcaffe.statistics.SummaryStatisticBasic;

import java.util.List;

public class VisitorsSummaryStatistics extends SummaryStatisticBasic<VisitorStatisticsElement> {

    @Override
    public StatisticsPair<Integer, Double> getBestPerson(List<VisitorStatisticsElement> statistics) {
        return getBestPerson(statistics, this::getTotalCalories);
    }

    private Double getTotalCalories(List<VisitorStatisticsElement> statistics) {
        return statistics.stream().mapToDouble(VisitorStatisticsElement::getAvgCalories).sum();
    }

}
