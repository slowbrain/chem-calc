package pl.edu.pjwstk.chemcalc.code.ast.binary;

import pl.edu.pjwstk.chemcalc.code.ast.Expression;
import pl.edu.pjwstk.chemcalc.code.ast.IExpression;
import pl.edu.pjwstk.chemcalc.code.visitor.ASTVisitor;

public class FormulaExpression extends BinaryExpression implements
        IFormulaExpression {
    public FormulaExpression(Expression leftExpr, Expression rightExpr) {
        super(leftExpr, rightExpr);
    }

    public IExpression getLeftExpression() {
        return super.getLeftExpr();
    }

    public IExpression getRightExpression() {
        return super.getRightExpr();
    }

    public void accept(ASTVisitor visitor) {
        visitor.visitFormulaExpression(this);
    }
}