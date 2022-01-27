package by.valvik.dmdevcaffe.thread;

import by.valvik.dmdevcaffe.TestData;
import by.valvik.dmdevcaffe.context.AppContext;
import by.valvik.dmdevcaffe.entity.Dish;
import by.valvik.dmdevcaffe.entity.Order;
import by.valvik.dmdevcaffe.entity.Visitor;
import by.valvik.dmdevcaffe.util.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VisitorThreadTest {

    public static final long WAITING_PERIOD = 2L;

    @Test
    void runTest() throws InterruptedException {
        AppContext appContext = new AppContext();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        List<Dish> menu = TestData.getMenu();
        int capacity = 5;
        BlockingQueue<Visitor> visitors = CollectionUtils.generateQueue(Visitor::new, capacity);
        BlockingQueue<Order> orders = CollectionUtils.generateQueue(capacity);
        VisitorThread visitorThread = new VisitorThread(appContext, menu);
        scheduledExecutorService.scheduleAtFixedRate(visitorThread, 0, WAITING_PERIOD, TimeUnit.SECONDS);
        Thread.sleep(WAITING_PERIOD * capacity * 1000);
        assertEquals(orders.size(), capacity);
        assertTrue(visitors.isEmpty());
    }

}