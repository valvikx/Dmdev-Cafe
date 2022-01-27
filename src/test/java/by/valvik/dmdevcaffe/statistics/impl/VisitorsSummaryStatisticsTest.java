package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.TestData;
import by.valvik.dmdevcaffe.entity.VisitorStatisticsElement;
import by.valvik.dmdevcaffe.statistics.StatisticsPair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VisitorsSummaryStatisticsTest {

    @Test
    void getBestPersonTest() {
        List<VisitorStatisticsElement> statistics = TestData.getVisitorsStatistics();
        VisitorsSummaryStatistics visitorsSummaryStatistics = new VisitorsSummaryStatistics();
        StatisticsPair<Integer, Double> bestVisitor = visitorsSummaryStatistics.getBestPerson(statistics);
        assertEquals(bestVisitor.id(), 1);
    }

}