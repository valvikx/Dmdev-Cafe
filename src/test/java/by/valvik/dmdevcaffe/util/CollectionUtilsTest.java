package by.valvik.dmdevcaffe.util;

import by.valvik.dmdevcaffe.entity.Cashier;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

    @Test
    public void generateQueueTest() throws InterruptedException {
        Supplier<Cashier> cashierSupplier = Cashier::new;
        int capacity = 3;
        BlockingQueue<Cashier> cashiers = CollectionUtils.generateQueue(cashierSupplier, capacity);
        assertEquals(cashiers.size(), capacity);
        Cashier cashier1 = cashiers.take();
        Cashier cashier2 = cashiers.take();
        Cashier cashier3 = cashiers.take();
        assertEquals(cashier1.id(), 1);
        assertEquals(cashier2.id(), 2);
        assertEquals(cashier3.id(), 3);
    }

}