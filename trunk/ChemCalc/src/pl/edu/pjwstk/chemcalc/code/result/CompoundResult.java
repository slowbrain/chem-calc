package pl.edu.pjwstk.chemcalc.code.result;

import pl.edu.pjwstk.chemcalc.code.ast.binary.CompoundExpression;

/**
 * Represents terminal of CompoundExpression.
 * 
 * last modified: 9 Dec 2013
 * 
 * @see CompoundExpression
 * @author Adrian Bala
 */
public class CompoundResult implements ICompoundResult {
    private ElementResult element;
    private IntegerResult number;

    /**
     * @param element
     *            ElementResult expression
     * @see ElementResult
     */
    public CompoundResult(ElementResult element) {
        this.element = element;
        this.number = new IntegerResult(1);
    }

    /**
     * @param element
     *            ElementResult expression
     * @param number
     *            integer number after the element
     * @see ElementResult
     */
    public CompoundResult(ElementResult element, IntegerResult number) {
        this.element = element;
        this.number = number;
    }

    /**
     * Returns the ElementResult expression.
     * 
     * @return ElementResult expression
     * @see ElementResult
     */
    public ElementResult getElement() {
        return element;
    }

    /**
     * Returns the IIntegerResult expression.
     * 
     * @return IIntegerResult expression
     * @see IIntegerResult
     */
    public IntegerResult getNumber() {
        return number;
    }

    /**
     * Returns the mass of whole CompoundExpression.
     * 
     * @return mass
     */
    public Double getMass() {
        return element.getMass() * number.getValue();
    }

    /**
     * Returns an instance of CompoundResult.
     * 
     * @return CompoundResult
     * @see CompoundResult
     */
    public CompoundResult getValue() {
        return this;
    }

    public String toString() {
        String s = element.getSymbol();
        if (1 < number.getValue()) {
            s += "_" + number.getValue().toString();
        }
        return s;
    }
}