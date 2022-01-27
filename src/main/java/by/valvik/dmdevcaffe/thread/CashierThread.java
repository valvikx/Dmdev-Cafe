package by.valvik.dmdevcaffe.thread;

import by.valvik.dmdevcaffe.context.AppContext;
import by.valvik.dmdevcaffe.entity.Cashier;
import by.valvik.dmdevcaffe.entity.Order;
import by.valvik.dmdevcaffe.entity.Visitor;

import java.util.concurrent.BlockingQueue;

public record CashierThread(BlockingQueue<Order> orders,
                            BlockingQueue<Visitor> visitors,
                            BlockingQueue<Cashier> cashiers,
                            BlockingQueue<Order> completedOrdersForCashiersStatistics,
                            BlockingQueue<Order> completedOrdersForVisitorsStatistics) implements Runnable {

    private static final long TIME_PER_ELEMENT = 1000L;

    public CashierThread(AppContext appContext) {
        this(appContext.getOrders(),
             appContext.getVisitors(),
             appContext.getCashiers(),
             appContext.getCompletedOrdersForCashiersStatistics(),
             appContext.getCompletedOrdersForVisitorsStatistics());
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = orders.take();
                Cashier cashier = cashiers.take();
                order.setCashier(cashier);
                System.out.println("ORDER IN PROGRESS>> " + order);
                Integer totalElements = order.getTotalElements();
                long preparationTimeInMillis = TIME_PER_ELEMENT * totalElements;
                Thread.sleep(preparationTimeInMillis);
                order.setCompleted(true);
                System.out.println("#ORDER IS COMPLETED>> " + order);
                System.out.println("#TOTAL ORDER COST: " + order.getCost());
                System.out.println("#ORDER PREPARATION TIME: " + preparationTimeInMillis / 1000);
                cashiers.put(cashier);
                visitors.put(order.getVisitor());
                completedOrdersForVisitorsStatistics.put(order);
                completedOrdersForCashiersStatistics.put(order);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
