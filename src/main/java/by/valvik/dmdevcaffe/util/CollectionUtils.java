package by.valvik.dmdevcaffe.util;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static <E> BlockingQueue<E> generateQueue(Supplier<E> entitySupplier, int capacity){
        List<E> list = Stream.generate(entitySupplier).limit(capacity).toList();
        return new ArrayBlockingQueue<>(capacity, true, list);
    }

    public static <E> BlockingQueue<E> generateQueue(int capacity){
        return new ArrayBlockingQueue<>(capacity, true);
    }

}
