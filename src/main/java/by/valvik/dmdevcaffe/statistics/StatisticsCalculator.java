package by.valvik.dmdevcaffe.statistics;

import by.valvik.dmdevcaffe.entity.BasicStatisticsElement;

import java.util.List;

public interface StatisticsCalculator<S extends BasicStatisticsElement> {

     List<S> getStatistics();

}
