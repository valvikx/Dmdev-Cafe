package by.valvik.dmdevcaffe.entity;

import static java.lang.Integer.parseInt;

public class CashierStatisticsElement extends BasicStatisticsElement {

    private static final String CSV_STRING_TEMPLATE = "%d,%d,%d";

    private Integer ordersCount;

    private Integer totalOrdersCost;

    public CashierStatisticsElement() {
    }

    public CashierStatisticsElement(Integer cashierId, Integer ordersCount, Integer totalOrdersCost) {
        super(cashierId);
        this.ordersCount = ordersCount;
        this.totalOrdersCost = totalOrdersCost;
    }

    @Override
    public String toCSVString() {
        return CSV_STRING_TEMPLATE.formatted(getPersonId(), ordersCount, totalOrdersCost);
    }

    @Override
    public void setFromCSVString(String csvString) {
        String[] tokens = parseCSVString(csvString);
        setPersonId(parseInt(tokens[0]));
        this.ordersCount = parseInt(tokens[1]);
        this.totalOrdersCost = parseInt(tokens[2]);
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public Integer getTotalOrdersCost() {
        return totalOrdersCost;
    }

}
