package pl.edu.pjwstk.chemcalc.code.result;

public class StringResult implements IStringResult {
    private String value;
    private Double mass;

    public StringResult(String value) {
        this.value = value;
        this.mass = 0.0;
    }

    public Double getMass() {
        return mass;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "\"" + value + "\"";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ISimpleResult<?> other = (ISimpleResult<?>) obj;
        if (value == null) {
            if (other.getValue() != null)
                return false;
        } else if (!value.equals(other.getValue()))
            return false;
        return true;
    }
}