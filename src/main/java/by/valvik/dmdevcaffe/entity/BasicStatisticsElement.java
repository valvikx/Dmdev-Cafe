package by.valvik.dmdevcaffe.entity;

public abstract class BasicStatisticsElement {

    public static final String COMMA = ",";

    private Integer personId;

    public BasicStatisticsElement() {
    }

    public BasicStatisticsElement(Integer personId) {
        this.personId = personId;
    }

    public String[] parseCSVString(String csvString) {
        return csvString.split(COMMA);
    }

    public abstract String toCSVString();

    public abstract void setFromCSVString(String csvString);

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

}
