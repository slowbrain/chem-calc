package pl.edu.pjwstk.chemcalc.code.visitor;

import pl.edu.pjwstk.chemcalc.code.ast.binary.BracketExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.ChemicalFormulaExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.CompoundExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.FormulaExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.IBracketExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.IChemicalFormulaExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.ICompoundExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.IFormulaExpression;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.DoubleTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IDoubleTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IIntegerTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IStringTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IntegerTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.StringTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.unary.ElementExpression;
import pl.edu.pjwstk.chemcalc.code.ast.unary.IElementExpression;

/**
 * General Abstract Syntax Tree Visitor interface of Visitor general pattern.
 * 
 * last modified: 9 Dec 2013
 * 
 * @version 1.0
 * @author Adrian Bala
 */
public interface ASTVisitor {
    /**
     * @param expr
     *            visited IntegerTerminal expression
     * @see IntegerTerminal
     */
    public void visitIntegerTerminal(IIntegerTerminal expr);

    /**
     * @param expr
     *            visited DoubleTerminal expression
     * @see DoubleTerminal
     */
    public void visitDoubleTerminal(IDoubleTerminal expr);

    /**
     * @param expr
     *            visited StringTerminal expression
     * @see StringTerminal
     */
    public void visitStringTerminal(IStringTerminal expr);

    /**
     * @param expr
     *            visited ElementExpression expression
     * @see ElementExpression
     */
    public void visitElementExpression(IElementExpression expr);

    /**
     * @param expr
     *            visited CompoundExpression expression
     * @see CompoundExpression
     */
    public void visitCompoundExpression(ICompoundExpression expr);

    /**
     * @param expr
     *            visited ChemicalFormulaExpression expression
     * @see ChemicalFormulaExpression
     */
    public void visitChemicalFormulaExpression(IChemicalFormulaExpression expr);

    /**
     * @param expr
     *            visited FormulaExpression expression
     * @see FormulaExpression
     */
    public void visitFormulaExpression(IFormulaExpression expr);

    /**
     * @param expr
     *            visited BracketExpression expression
     * @see BracketExpression
     */
    public void visitBracketExpression(IBracketExpression expr);
}