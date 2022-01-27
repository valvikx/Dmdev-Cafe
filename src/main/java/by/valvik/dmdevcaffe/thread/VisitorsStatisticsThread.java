package by.valvik.dmdevcaffe.thread;

import by.valvik.dmdevcaffe.context.AppContext;
import by.valvik.dmdevcaffe.entity.VisitorStatisticsElement;
import by.valvik.dmdevcaffe.io.StatisticsIO;
import by.valvik.dmdevcaffe.statistics.impl.VisitorsStatisticsCalculator;
import by.valvik.dmdevcaffe.util.PropertiesUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static by.valvik.dmdevcaffe.constant.AppConstant.VISITORS_STATISTICS_FILE_NAME_PREFIX;

public record VisitorsStatisticsThread(VisitorsStatisticsCalculator visitorsStatisticsCalculator,
                                       StatisticsIO statisticsIO,
                                       AtomicInteger fileId) implements Runnable{

    public VisitorsStatisticsThread(AppContext appContext) {
        this(new VisitorsStatisticsCalculator(appContext.getCompletedOrdersForVisitorsStatistics()),
                appContext.getStatisticsIO(),
                appContext.getCashiersStatisticsFileId());
    }

    @Override
    public void run() {
        try {
            List<VisitorStatisticsElement> statistics = visitorsStatisticsCalculator.getStatistics();
            String sourceName = PropertiesUtils.getValue(VISITORS_STATISTICS_FILE_NAME_PREFIX) + fileId.get();
            statisticsIO.write(statistics, sourceName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
