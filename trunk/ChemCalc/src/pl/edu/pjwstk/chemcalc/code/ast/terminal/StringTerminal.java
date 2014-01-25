package pl.edu.pjwstk.chemcalc.code.ast.terminal;

import pl.edu.pjwstk.chemcalc.code.ast.Expression;
import pl.edu.pjwstk.chemcalc.code.visitor.ASTVisitor;

public class StringTerminal extends Expression implements IStringTerminal {
    private String value;

    public StringTerminal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visitStringTerminal(this);
    }
}