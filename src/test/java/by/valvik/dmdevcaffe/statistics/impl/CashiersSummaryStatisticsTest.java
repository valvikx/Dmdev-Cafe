package by.valvik.dmdevcaffe.statistics.impl;

import by.valvik.dmdevcaffe.TestData;
import by.valvik.dmdevcaffe.entity.CashierStatisticsElement;
import by.valvik.dmdevcaffe.statistics.StatisticsPair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashiersSummaryStatisticsTest {

    @Test
    void getBestPersonTest() {
        List<CashierStatisticsElement> statistics = TestData.getCashiersStatistics();
        CashiersSummaryStatistics cashiersSummaryStatistics = new CashiersSummaryStatistics();
        StatisticsPair<Integer, Double> bestCashier = cashiersSummaryStatistics.getBestPerson(statistics);
        assertEquals(bestCashier.id(), 3);
    }

}