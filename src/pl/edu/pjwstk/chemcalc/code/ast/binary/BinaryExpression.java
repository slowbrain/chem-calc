package pl.edu.pjwstk.chemcalc.code.ast.binary;

import pl.edu.pjwstk.chemcalc.code.ast.Expression;
import pl.edu.pjwstk.chemcalc.code.ast.IExpression;

public abstract class BinaryExpression extends Expression implements
        IBinaryExpression {
    private Expression leftExpr;
    private Expression rightExpr;

    public BinaryExpression(Expression leftExpr, Expression rightExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    public IExpression getLeftExpr() {
        return leftExpr;
    }

    public IExpression getRightExpr() {
        return rightExpr;
    }
}