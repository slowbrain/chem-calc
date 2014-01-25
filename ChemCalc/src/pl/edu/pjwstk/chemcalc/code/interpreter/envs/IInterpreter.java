package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import pl.edu.pjwstk.chemcalc.code.ast.IExpression;
import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;
import pl.edu.pjwstk.chemcalc.code.visitor.ASTVisitor;

public interface IInterpreter extends ASTVisitor {
    public IAbstractQueryResult eval(IExpression queryTreeRoot);
}