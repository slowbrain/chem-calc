package pl.edu.pjwstk.chemcalc.code.ast.unary;

import pl.edu.pjwstk.chemcalc.code.ast.Expression;
import pl.edu.pjwstk.chemcalc.code.ast.IExpression;
import pl.edu.pjwstk.chemcalc.code.visitor.ASTVisitor;

public class ElementExpression extends UnaryExpression implements
        IElementExpression {
    public ElementExpression(Expression innerExpr) {
        super(innerExpr);
    }

    public IExpression getInnerExpression() {
        return super.getInnerExpression();
    }

    public void accept(ASTVisitor visitor) {
        visitor.visitElementExpression(this);
    }
}