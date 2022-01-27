package by.valvik.dmdevcaffe.io;

import by.valvik.dmdevcaffe.entity.BasicStatisticsElement;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public interface StatisticsIO {

    <S extends BasicStatisticsElement> void write(List<S> statistics, String sourceName) throws IOException;

    <S extends BasicStatisticsElement> List<S> read(String sourceName, Supplier<S> factory) throws IOException;

}
