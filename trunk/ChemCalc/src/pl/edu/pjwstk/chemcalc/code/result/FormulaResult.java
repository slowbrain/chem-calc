package pl.edu.pjwstk.chemcalc.code.result;

public class FormulaResult implements IFormulaResult {
    private ISingleResult leftFormula;
    private ISingleResult rightFormula;
    private Double mass;

    public FormulaResult(ISingleResult leftFormula) {
        this.leftFormula = leftFormula;
        this.rightFormula = null;
        this.mass = leftFormula.getMass();
    }

    public FormulaResult(ISingleResult leftFormula, ISingleResult rightFormula) {
        this.leftFormula = leftFormula;
        this.rightFormula = rightFormula;
        if (leftFormula instanceof IntegerResult) {
            this.mass = ((IntegerResult) leftFormula).getValue()
                    * rightFormula.getMass();
        } else {
            this.mass = leftFormula.getMass() + rightFormula.getMass();
        }
    }

    public ISingleResult getLeftResult() {
        return leftFormula;
    }

    public ISingleResult getRightResult() {
        return rightFormula;
    }

    public Double getMass() {
        return mass;
    }

    public FormulaResult getValue() {
        return this;
    }

    public String toString() {
        String chemform = leftFormula.toString();
        if (this.rightFormula != null) {
            if (leftFormula instanceof IntegerResult) {
                chemform += "(" + rightFormula.toString() + ")";
            } else {
                chemform += " " + rightFormula.toString();
            }
        }
        return chemform;
    }
}