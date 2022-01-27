package by.valvik.dmdevcaffe.thread;

import by.valvik.dmdevcaffe.context.AppContext;
import by.valvik.dmdevcaffe.entity.CashierStatisticsElement;
import by.valvik.dmdevcaffe.io.StatisticsIO;
import by.valvik.dmdevcaffe.statistics.impl.CashiersStatisticsCalculator;
import by.valvik.dmdevcaffe.util.PropertiesUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static by.valvik.dmdevcaffe.constant.AppConstant.CASHIERS_STATISTICS_FILE_NAME_PREFIX;

public record CashiersStatisticsThread(CashiersStatisticsCalculator cashiersStatisticsCalculator,
                                       StatisticsIO statisticsIO,
                                       AtomicInteger fileId) implements Runnable {

    public CashiersStatisticsThread(AppContext appContext) {
        this(new CashiersStatisticsCalculator(appContext.getCompletedOrdersForCashiersStatistics()),
             appContext.getStatisticsIO(),
             appContext.getCashiersStatisticsFileId());
    }

    @Override
    public void run() {
        try {
            List<CashierStatisticsElement> statistics = cashiersStatisticsCalculator.getStatistics();
            String sourceName = PropertiesUtils.getValue(CASHIERS_STATISTICS_FILE_NAME_PREFIX) + fileId.get();
            statisticsIO.write(statistics, sourceName);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

}
