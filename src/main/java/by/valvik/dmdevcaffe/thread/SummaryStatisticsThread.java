package by.valvik.dmdevcaffe.thread;

import by.valvik.dmdevcaffe.context.AppContext;
import by.valvik.dmdevcaffe.entity.CashierStatisticsElement;
import by.valvik.dmdevcaffe.entity.VisitorStatisticsElement;
import by.valvik.dmdevcaffe.io.StatisticsIO;
import by.valvik.dmdevcaffe.statistics.StatisticsPair;
import by.valvik.dmdevcaffe.statistics.impl.CashiersSummaryStatistics;
import by.valvik.dmdevcaffe.statistics.impl.VisitorsSummaryStatistics;
import by.valvik.dmdevcaffe.util.PropertiesUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static by.valvik.dmdevcaffe.constant.AppConstant.CASHIERS_STATISTICS_FILE_NAME_PREFIX;
import static by.valvik.dmdevcaffe.constant.AppConstant.VISITORS_STATISTICS_FILE_NAME_PREFIX;

public record SummaryStatisticsThread(StatisticsIO statisticsIO,
                                      AtomicInteger visitorsStatisticsFileId,
                                      AtomicInteger cashiersStatisticsFileId,
                                      CashiersSummaryStatistics cashiersSummaryStatistics,
                                      VisitorsSummaryStatistics visitorsSummaryStatistics) implements Runnable {

    public static final String CASHIERS_STATISTICS_PRESENTATION = "cashiers.statistics.presentation";
    public static final String VISITORS_STATISTICS_PRESENTATION = "visitors.statistics.presentation";

    public SummaryStatisticsThread(AppContext appContext) {
        this(appContext.getStatisticsIO(),
             appContext.getVisitorsStatisticsFileId(),
             appContext.getCashiersStatisticsFileId(),
             new CashiersSummaryStatistics(),
             new VisitorsSummaryStatistics());
    }

    @Override
    public void run() {
        try {
            printSummaryCashiersStatistics();
            printSummaryVisitorsStatistics();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printSummaryCashiersStatistics() throws IOException {
        int activeCashiersStatisticsFileId = cashiersStatisticsFileId.incrementAndGet() - 1;
        String cashiersSourceName = PropertiesUtils.getValue(CASHIERS_STATISTICS_FILE_NAME_PREFIX) + activeCashiersStatisticsFileId;
        List<CashierStatisticsElement> cashiersStatistics = statisticsIO.read(cashiersSourceName, CashierStatisticsElement::new);
        StatisticsPair<Integer, Double> bestCashier = cashiersSummaryStatistics.getBestPerson(cashiersStatistics);
        String statisticsPresentation =
                PropertiesUtils.getValue(CASHIERS_STATISTICS_PRESENTATION).formatted(bestCashier.id(), bestCashier.value());
        System.out.println(statisticsPresentation);
    }

    private void printSummaryVisitorsStatistics() throws IOException {
        int activeVisitorsStatisticsFileId = visitorsStatisticsFileId.incrementAndGet() - 1;
        String visitorsSourceName = PropertiesUtils.getValue(VISITORS_STATISTICS_FILE_NAME_PREFIX) + activeVisitorsStatisticsFileId;
        List<VisitorStatisticsElement> visitorsStatistics = statisticsIO.read(visitorsSourceName, VisitorStatisticsElement::new);
        StatisticsPair<Integer, Double> bestVisitor = visitorsSummaryStatistics.getBestPerson(visitorsStatistics);
        String statisticsPresentation =
                PropertiesUtils.getValue(VISITORS_STATISTICS_PRESENTATION).formatted(bestVisitor.id(), bestVisitor.value());
        System.out.println(statisticsPresentation);
    }

}
