package pl.edu.pjwstk.chemcalc.code.result;

import pl.edu.pjwstk.chemcalc.code.ast.binary.ChemicalFormulaExpression;

/**
 * Represents terminal of ChemicalFormulaExpression.
 * 
 * last modified: 9 Dec 2013
 * 
 * @see ChemicalFormulaExpression
 * @author Adrian Bala
 */
public class ChemicalFormulaResult implements IChemicalFormulaResult {
    private ISingleResult leftFormula;
    private ISingleResult rightFormula;
    private Double mass;

    /**
     * @param leftFormula
     *            ISingleResult expression
     * @see ISingleResult
     */
    public ChemicalFormulaResult(ISingleResult leftFormula) {
        this.leftFormula = leftFormula;
        this.rightFormula = null;
        this.mass = leftFormula.getMass();
    }

    /**
     * @param leftFormula
     *            ISingleResult expression
     * @param rightFormula
     *            ISingleResult expression
     * @see ISingleResult
     */
    public ChemicalFormulaResult(ISingleResult leftFormula,
            ISingleResult rightFormula) {
        this.leftFormula = leftFormula;
        this.rightFormula = rightFormula;
        this.mass = leftFormula.getMass() + rightFormula.getMass();
    }

    /**
     * Returns the ISingleResult leftFormula.
     * 
     * @return ISingleResult leftFormula
     * @see ISingleResult
     */
    public ISingleResult getLeftResult() {
        return leftFormula;
    }

    /**
     * Returns the ISingleResult rightFormula.
     * 
     * @return ISingleResult rightFormula
     * @see ISingleResult
     */
    public ISingleResult getRightResult() {
        return rightFormula;
    }

    /**
     * Returns the mass of whole ChemicalFormulaExpression.
     * 
     * @return mass
     */
    public Double getMass() {
        return mass;
    }

    /**
     * Returns an instance of ChemicalFormulaResult.
     * 
     * @return ChemicalFormulaResult
     * @see ChemicalFormulaResult
     */
    public ChemicalFormulaResult getValue() {
        return this;
    }

    public String toString() {
        String chemform = leftFormula.toString();
        if ((rightFormula != null)
                && !((rightFormula instanceof IntegerResult)
                    && (1 == ((IntegerResult) rightFormula).getValue()))) {
            chemform += rightFormula.toString();
        }
        return chemform;
    }
}