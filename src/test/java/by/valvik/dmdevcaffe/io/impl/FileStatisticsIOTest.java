package by.valvik.dmdevcaffe.io.impl;

import by.valvik.dmdevcaffe.TestData;
import by.valvik.dmdevcaffe.entity.CashierStatisticsElement;
import by.valvik.dmdevcaffe.entity.VisitorStatisticsElement;
import by.valvik.dmdevcaffe.io.StatisticsIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class FileStatisticsIOTest {

    public static final String RESOURCES = "resources";
    public static final String CASHIERS_STATISTICS_TEST = "cashiers-statistics-test";
    public static final String VISITORS_STATISTICS_TEST = "visitors-statistics-test";
    public static final String CSV_EXT = ".csv";

    @Test
    void writeAndReadCSVCashiersStatisticFileTest() throws IOException {
        Files.deleteIfExists(Paths.get(RESOURCES, CASHIERS_STATISTICS_TEST + CSV_EXT));
        StatisticsIO statisticsIO = new FileStatisticsIO(RESOURCES);
        List<CashierStatisticsElement> cashiersStatistics = TestData.getCashiersStatistics();
        List<CashierStatisticsElement> statisticsElements1Block = cashiersStatistics.subList(0, 3);
        List<CashierStatisticsElement> statisticsElements2Block = cashiersStatistics.subList(3, 5);
        statisticsIO.write(statisticsElements1Block, CASHIERS_STATISTICS_TEST);
        statisticsIO.write(statisticsElements2Block, CASHIERS_STATISTICS_TEST);
        List<CashierStatisticsElement> cashierStatisticElements = statisticsIO.read(CASHIERS_STATISTICS_TEST, CashierStatisticsElement::new);
        Assertions.assertEquals(cashierStatisticElements.size(), 5);
        Assertions.assertEquals(cashierStatisticElements.get(0).getPersonId(), 1);
        Assertions.assertEquals(cashierStatisticElements.get(4).getPersonId(), 3);
    }

    @Test
    void writeAndReadCSVVisitorsStatisticFileTest() throws IOException {
        Files.deleteIfExists(Paths.get(RESOURCES, VISITORS_STATISTICS_TEST + CSV_EXT));
        StatisticsIO statisticsIO = new FileStatisticsIO(RESOURCES);
        List<VisitorStatisticsElement> visitorsStatistics = TestData.getVisitorsStatistics();
        List<VisitorStatisticsElement> statisticsElements1Block = visitorsStatistics.subList(0, 3);
        List<VisitorStatisticsElement> statisticsElements2Block = visitorsStatistics.subList(3, 5);
        statisticsIO.write(statisticsElements1Block, VISITORS_STATISTICS_TEST);
        statisticsIO.write(statisticsElements2Block, VISITORS_STATISTICS_TEST);
        List<VisitorStatisticsElement> cashierStatisticElements = statisticsIO.read(VISITORS_STATISTICS_TEST, VisitorStatisticsElement::new);
        Assertions.assertEquals(cashierStatisticElements.size(), 5);
        Assertions.assertEquals(cashierStatisticElements.get(0).getPersonId(), 1);
        Assertions.assertEquals(cashierStatisticElements.get(4).getPersonId(), 3);
    }

}