package by.valvik.dmdevcaffe.io.impl;

import by.valvik.dmdevcaffe.entity.BasicStatisticsElement;
import by.valvik.dmdevcaffe.io.StatisticsIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileStatisticsIO implements StatisticsIO {

    private static final String CSV_EXT = ".csv";

    private final String resource;

    private final ReadWriteLock lock;

    public FileStatisticsIO(String resource) {
        this.resource = resource;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public <S extends BasicStatisticsElement> void write(List<S> statistics, String fileName) throws IOException {
        Path path = Paths.get(resource, fileName + CSV_EXT);
        lock.writeLock().lock();
        try {
            Files.write(path, toLines(statistics), CREATE, APPEND);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public <S extends BasicStatisticsElement> List<S> read(String fileName, Supplier<S> factory) throws IOException {
        Path path = Paths.get(resource, fileName + CSV_EXT);
        List<S> statistics;
        lock.readLock().lock();
        try {
            List<String> lines = Files.readAllLines(path);
            statistics = toStatistics(lines, factory);
        } finally {
            lock.readLock().unlock();
        }
        return statistics;
    }

    private <S extends BasicStatisticsElement> List<String> toLines(List<S> statistic) {
        return statistic.stream().map(BasicStatisticsElement::toCSVString).toList();
    }

    private <S extends BasicStatisticsElement> List<S> toStatistics(List<String> lines, Supplier<S> factory) {
        return lines.stream()
                    .map(line -> {
                        S statisticsElement = factory.get();
                        statisticsElement.setFromCSVString(line);
                        return statisticsElement;
                    })
                    .toList();
    }

}
