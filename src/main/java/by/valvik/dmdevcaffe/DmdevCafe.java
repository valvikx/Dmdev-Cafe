package by.valvik.dmdevcaffe;

import by.valvik.dmdevcaffe.context.AppContext;
import by.valvik.dmdevcaffe.entity.Dish;
import by.valvik.dmdevcaffe.thread.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static by.valvik.dmdevcaffe.constant.AppConstant.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DmdevCafe {

    private final List<Dish> menu;

    private final AppContext appContext;

    private final ScheduledExecutorService scheduledExecutorService;

    private final ExecutorService executorService;

    public DmdevCafe(List<Dish> menu) {
        this.menu = menu;
        this.appContext = new AppContext();
        this.scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void open() {
        Runnable visitorThread = new VisitorThread(appContext, menu);
        scheduledExecutorService.scheduleAtFixedRate(visitorThread, 0, ORDERS_PERIOD, SECONDS);
        Runnable cashierThread = new CashierThread(appContext);
        executorService.submit(cashierThread);
        Runnable cashierStatisticsThread = new CashiersStatisticsThread(appContext);
        scheduledExecutorService.scheduleAtFixedRate(cashierStatisticsThread, CASHIERS_STATISTICS_PERIOD,
                                                     CASHIERS_STATISTICS_PERIOD, SECONDS);
        Runnable visitorsStatisticsThread = new VisitorsStatisticsThread(appContext);
        scheduledExecutorService.scheduleAtFixedRate(visitorsStatisticsThread, VISITORS_STATISTICS_PERIOD,
                                                     VISITORS_STATISTICS_PERIOD, SECONDS);
        Runnable summaryStatisticsThread = new SummaryStatisticsThread(appContext);
        scheduledExecutorService.scheduleAtFixedRate(summaryStatisticsThread, SUMMARY_STATISTICS_PERIOD,
                                                     SUMMARY_STATISTICS_PERIOD, SECONDS);
    }

}
