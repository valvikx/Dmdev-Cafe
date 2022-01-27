package by.valvik.dmdevcaffe.statistics;

import by.valvik.dmdevcaffe.entity.BasicStatisticsElement;

import java.util.List;

public interface SummaryStatistics<S extends BasicStatisticsElement> {

     StatisticsPair<Integer, Double> getBestPerson(List<S> statistics);

}
