package by.valvik.dmdevcaffe.context;

import by.valvik.dmdevcaffe.entity.Cashier;
import by.valvik.dmdevcaffe.entity.Order;
import by.valvik.dmdevcaffe.entity.Visitor;
import by.valvik.dmdevcaffe.io.StatisticsIO;
import by.valvik.dmdevcaffe.io.impl.FileStatisticsIO;
import by.valvik.dmdevcaffe.util.CollectionUtils;
import by.valvik.dmdevcaffe.util.PropertiesUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static by.valvik.dmdevcaffe.constant.AppConstant.*;

public class AppContext {

    public static final String VISITORS_COUNT = "visitors.count";
    public static final String CASHIERS_COUNT = "cashiers.count";
    private static final String RESOURCES = "resources";

    private final BlockingQueue<Cashier> cashiers;

    private final BlockingQueue<Visitor> visitors;

    private final BlockingQueue<Order> orders;

    private final BlockingQueue<Order> completedOrdersForCashiersStatistics;

    private final BlockingQueue<Order> completedOrdersForVisitorsStatistics;

    private final StatisticsIO statisticsIO;

    private final AtomicInteger visitorsStatisticsFileId;

    private final AtomicInteger cashiersStatisticsFileId;

    public AppContext() {
        this.visitors = CollectionUtils.generateQueue(Visitor::new, PropertiesUtils.getAsInt(VISITORS_COUNT));
        this.cashiers = CollectionUtils.generateQueue(Cashier::new, PropertiesUtils.getAsInt(CASHIERS_COUNT));
        this.orders = CollectionUtils.generateQueue(PropertiesUtils.getAsInt(VISITORS_COUNT));
        this.completedOrdersForCashiersStatistics = CollectionUtils.generateQueue((int) (CASHIERS_STATISTICS_PERIOD / ORDERS_PERIOD));
        this.completedOrdersForVisitorsStatistics = CollectionUtils.generateQueue((int) (VISITORS_STATISTICS_PERIOD / ORDERS_PERIOD));
        this.statisticsIO = new FileStatisticsIO(RESOURCES);
        this.visitorsStatisticsFileId = new AtomicInteger(INITIAL_ID);
        this.cashiersStatisticsFileId = new AtomicInteger(INITIAL_ID);
    }

    public BlockingQueue<Cashier> getCashiers() {
        return cashiers;
    }

    public BlockingQueue<Visitor> getVisitors() {
        return visitors;
    }

    public BlockingQueue<Order> getOrders() {
        return orders;
    }

    public BlockingQueue<Order> getCompletedOrdersForCashiersStatistics() {
        return completedOrdersForCashiersStatistics;
    }

    public BlockingQueue<Order> getCompletedOrdersForVisitorsStatistics() {
        return completedOrdersForVisitorsStatistics;
    }

    public StatisticsIO getStatisticsIO() {
        return statisticsIO;
    }

    public AtomicInteger getVisitorsStatisticsFileId() {
        return visitorsStatisticsFileId;
    }

    public AtomicInteger getCashiersStatisticsFileId() {
        return cashiersStatisticsFileId;
    }

}
