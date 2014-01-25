package pl.edu.pjwstk.chemcalc.code.result;

import pl.edu.pjwstk.chemcalc.code.ast.binary.BracketExpression;

/**
 * Represents terminal of BracketExpression.
 * 
 * last modified: 9 Dec 2013
 * 
 * @see BracketExpression
 * @author Adrian Bala
 */
public class BracketResult implements IBracketResult {
    private ISingleResult expression;
    private IIntegerResult number;
    private Double mass;

    /**
     * @param expression
     *            ISingleResult expression
     * @see ISingleResult
     */
    public BracketResult(ISingleResult expression) {
        this.expression = expression;
        this.number = new IntegerResult(1);
        this.mass = expression.getMass();
    }

    /**
     * @param expression
     *            ISingleResult expression
     * @param number
     *            integer number after the bracket
     * @see ISingleResult
     */
    public BracketResult(ISingleResult expression, IntegerResult number) {
        this.expression = expression;
        this.number = number;
        this.mass = expression.getMass() * number.getValue();
    }

    /**
     * Returns the ISingleResult expression.
     * 
     * @return ISingleResult expression
     * @see ISingleResult
     */
    public ISingleResult getElement() {
        return expression;
    }

    /**
     * Returns the IIntegerResult expression.
     * 
     * @return IIntegerResult expression
     * @see IIntegerResult
     */
    public IIntegerResult getNumber() {
        return number;
    }

    /**
     * Returns the mass of whole BracketExpression.
     * 
     * @return mass
     */
    public Double getMass() {
        return mass;
    }

    /**
     * Returns an instance of BracketResult.
     * 
     * @return BracketResult
     * @see BracketResult
     */
    public BracketResult getValue() {
        return this;
    }

    public String toString() {
        return "(" + expression.toString() + ")_"
                + number.getValue().toString();
    }
}