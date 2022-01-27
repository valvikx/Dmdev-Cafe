package by.valvik.dmdevcaffe.thread;

import by.valvik.dmdevcaffe.context.AppContext;
import by.valvik.dmdevcaffe.entity.Dish;
import by.valvik.dmdevcaffe.entity.Order;
import by.valvik.dmdevcaffe.entity.Visitor;
import by.valvik.dmdevcaffe.util.OrderUtils;
import by.valvik.dmdevcaffe.util.PropertiesUtils;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static by.valvik.dmdevcaffe.constant.AppConstant.INITIAL_ID;

public record VisitorThread(BlockingQueue<Order> orders,
                            BlockingQueue<Visitor> visitors,
                            List<Dish> menu,
                            AtomicInteger orderId) implements Runnable {

    public static final String MAX_NUMBER_EACH_DISH_IN_ORDER_KEY = "max.number.each.dish.in.order";

    public VisitorThread(AppContext appContext, List<Dish> menu) {
        this(appContext.getOrders(), appContext.getVisitors(), menu, new AtomicInteger(INITIAL_ID));
    }

    @Override
    public void run() {
        try {
            Order order = new Order();
            order.setId(orderId.getAndIncrement());
            order.setVisitor(visitors.take());
            order.setDishes(OrderUtils.generateDishesInOrder(menu, PropertiesUtils.getAsInt(MAX_NUMBER_EACH_DISH_IN_ORDER_KEY)));
            System.out.println("NEW ORDER IS TAKEN>> " + order);
            orders.put(order);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
