package pl.edu.pjwstk.chemcalc.code.ast.unary;

import pl.edu.pjwstk.chemcalc.code.ast.IExpression;

public interface IUnaryExpression extends IExpression {
    public IExpression getInnerExpression();
}