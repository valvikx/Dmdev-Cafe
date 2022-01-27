package by.valvik.dmdevcaffe.entity;

public record Visitor(Integer id) {

    private static Integer sequence = 1;

    public Visitor() {
        this(sequence++);
    }

}
