package pl.edu.pjwstk.chemcalc.code.ast;

import pl.edu.pjwstk.chemcalc.code.visitor.ASTVisitor;

/**
 * General interface for Expression class.
 * 
 * last modified: 9 Dec 2013
 * 
 * @version 1.0
 * @author Adrian Bala
 */
public interface IExpression {
    /**
     * Method corresponding to Visitor design pattern.
     * 
     * @param visitor
     *            instance of Visitor design pattern
     */
    public void accept(ASTVisitor visitor);
}