package pl.edu.pjwstk.chemcalc.code.result;

import pl.edu.pjwstk.chemcalc.code.ast.unary.ElementExpression;
import pl.edu.pjwstk.chemcalc.code.model.ChemicalElement;

/**
 * Represents terminal of ElementExpression.
 * 
 * last modified: 9 Dec 2013
 * 
 * @see ElementExpression
 * @author Adrian Bala
 */
public class ElementResult implements IElementResult {
    private ChemicalElement chel;

    /**
     * @param chel
     *            ChemicalElement expression
     * @see ChemicalElement
     */
    public ElementResult(ChemicalElement chel) {
        this.chel = chel;
    }

    /**
     * Returns the ChemicalElement symbol.
     * 
     * @return String which represents the ChemicalElement symbol
     * @see ChemicalElement
     */
    public String getSymbol() {
        return chel.getSymbol();
    }

    /**
     * Returns the ChemicalElement mass.
     * 
     * @return Double which represents the ChemicalElement mass
     * @see ChemicalElement
     */
    public Double getMass() {
        return chel.getAtomicmass();
    }

    /**
     * Returns the ChemicalElement atomic number.
     * 
     * @return Integer which represents the ChemicalElement atomic number
     * @see ChemicalElement
     */
    public Integer getAtomicNumber() {
        return chel.getAtomicnumber();
    }

    /**
     * Returns the ChemicalElement name.
     * 
     * @return String which represents the ChemicalElement name
     * @see ChemicalElement
     */
    public String getName() {
        return chel.getName();
    }

    /**
     * Returns an instance of ElementResult.
     * 
     * @return ElementResult
     * @see ElementResult
     */
    public ElementResult getValue() {
        return this;
    }

    public String toString() {
        return chel.getSymbol();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ISimpleResult<?> other = (ISimpleResult<?>) obj;
        if (chel.getSymbol() == null) {
            if (other.getValue() != null)
                return false;
        } else if (!chel.getSymbol().equals(other.getValue()))
            return false;
        return true;
    }
}