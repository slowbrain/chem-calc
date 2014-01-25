package pl.edu.pjwstk.chemcalc.code.ast.unary;

import pl.edu.pjwstk.chemcalc.code.ast.Expression;
import pl.edu.pjwstk.chemcalc.code.ast.IExpression;

public class UnaryExpression extends Expression implements IUnaryExpression {
    private Expression innerExpr;

    public UnaryExpression(Expression innerExpr) {
        this.innerExpr = innerExpr;
    }

    public IExpression getInnerExpression() {
        return innerExpr;
    }
}