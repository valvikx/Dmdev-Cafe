package by.valvik.dmdevcaffe.statistics;

import by.valvik.dmdevcaffe.entity.BasicStatisticsElement;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

public abstract class SummaryStatisticBasic<S extends BasicStatisticsElement> implements SummaryStatistics<S> {

    public StatisticsPair<Integer, Double> getBestPerson(List<S> statistics, Function<List<S>, Double> finisher) {
        Map<Integer, Double> collect = statistics.stream()
                                                 .collect(groupingBy(BasicStatisticsElement::getPersonId,
                                                                     collectingAndThen(toList(), finisher)));
        Map.Entry<Integer, Double> max = Collections.max(collect.entrySet(), comparingDouble(Map.Entry::getValue));
        return new StatisticsPair<>(max.getKey(), max.getValue());
    }

}
