package by.valvik.dmdevcaffe.statistics;

import by.valvik.dmdevcaffe.entity.BasicStatisticsElement;
import by.valvik.dmdevcaffe.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public abstract class StatisticsCalculatorBasic<S extends BasicStatisticsElement> implements StatisticsCalculator<S> {

    private final List<Order> statisticsData;

    public StatisticsCalculatorBasic() {
        statisticsData = new ArrayList<>();
    }

    public void initStatisticsData(BlockingQueue<Order> completedOrders) {
        statisticsData.clear();
        completedOrders.drainTo(statisticsData);
    }

    public List<Order> getStatisticsData() {
        return statisticsData;
    }

}
