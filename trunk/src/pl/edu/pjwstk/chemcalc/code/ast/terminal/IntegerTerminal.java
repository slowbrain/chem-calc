package pl.edu.pjwstk.chemcalc.code.ast.terminal;

import pl.edu.pjwstk.chemcalc.code.ast.Expression;
import pl.edu.pjwstk.chemcalc.code.visitor.ASTVisitor;

public class IntegerTerminal extends Expression implements IIntegerTerminal {
    private Integer value;

    public IntegerTerminal(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visitIntegerTerminal(this);
    }
}