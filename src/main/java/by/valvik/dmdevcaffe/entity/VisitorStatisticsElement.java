package by.valvik.dmdevcaffe.entity;

import java.util.Locale;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class VisitorStatisticsElement extends BasicStatisticsElement {

    private static final String CSV_STRING_TEMPLATE = "%d,%d,%f,%f";

    private Integer ordersCount;

    private Double avgCalories;

    private Double avgCost;

    public VisitorStatisticsElement() {
    }

    public VisitorStatisticsElement(Integer visitorId, Integer ordersCount, Double avgCalories, Double avgCost) {
        super(visitorId);
        this.ordersCount = ordersCount;
        this.avgCalories = avgCalories;
        this.avgCost = avgCost;
    }

    @Override
    public String toCSVString() {
        return String.format(Locale.ROOT, CSV_STRING_TEMPLATE, getPersonId(), ordersCount, avgCalories, avgCost);
    }

    @Override
    public void setFromCSVString(String csvString) {
        String[] tokens = parseCSVString(csvString);
        setPersonId(parseInt(tokens[0]));
        this.ordersCount = parseInt(tokens[1]);
        this.avgCalories = parseDouble(tokens[2]);
        this.avgCost = parseDouble(tokens[3]);
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public Double getAvgCalories() {
        return avgCalories;
    }

    public Double getAvgCost() {
        return avgCost;
    }

}
