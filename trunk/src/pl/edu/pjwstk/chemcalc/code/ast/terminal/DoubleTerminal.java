package pl.edu.pjwstk.chemcalc.code.ast.terminal;

import pl.edu.pjwstk.chemcalc.code.ast.Expression;
import pl.edu.pjwstk.chemcalc.code.visitor.ASTVisitor;

public class DoubleTerminal extends Expression implements IDoubleTerminal {
    private Double value;

    public DoubleTerminal(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visitDoubleTerminal(this);
    }
}