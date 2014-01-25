package pl.edu.pjwstk.chemcalc.code.result;

import pl.edu.pjwstk.chemcalc.code.ast.binary.CompoundExpression;

/**
 * Represents terminal of DoubleExpression.
 * 
 * last modified: 9 Dec 2013
 * 
 * @see CompoundExpression
 * @author Adrian Bala
 */
public class DoubleResult implements IDoubleResult {
    private Double value;
    private Double mass;

    /**
     * @param value
     *            DoubleResult expression
     * @see DoubleResult
     */
    public DoubleResult(Double value) {
        this.value = value;
        this.mass = 0.0;
    }

    /**
     * Returns the mass of DoubleExpression which does not have any physical
     * sense.
     * 
     * @return mass
     */
    public Double getMass() {
        return mass;
    }

    /**
     * Returns an instance of DoubleResult.
     * 
     * @return DoubleResult
     * @see DoubleResult
     */
    public Double getValue() {
        return value;
    }

    public String toString() {
        return value.toString();
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