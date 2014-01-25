package pl.edu.pjwstk.chemcalc.code.ast.terminal;

import pl.edu.pjwstk.chemcalc.code.ast.IExpression;

public interface ITerminalExpression<T> extends IExpression {
    public T getValue();
}