package pl.edu.pjwstk.chemcalc.code.ast.binary;

import pl.edu.pjwstk.chemcalc.code.ast.IExpression;

public interface IBinaryExpression extends IExpression {
    public IExpression getLeftExpression();

    public IExpression getRightExpression();
}