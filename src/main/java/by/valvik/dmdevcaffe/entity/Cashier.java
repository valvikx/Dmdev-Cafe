package by.valvik.dmdevcaffe.entity;

public record Cashier(Integer id) {

    private static Integer sequence = 1;

    public Cashier() {
        this(sequence++);
    }

}
