package pl.edu.pjwstk.chemcalc.code.interpreter.envs;

import pl.edu.pjwstk.chemcalc.code.ast.IExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.IBracketExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.IChemicalFormulaExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.ICompoundExpression;
import pl.edu.pjwstk.chemcalc.code.ast.binary.IFormulaExpression;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IDoubleTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IIntegerTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IStringTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.terminal.IntegerTerminal;
import pl.edu.pjwstk.chemcalc.code.ast.unary.IElementExpression;
import pl.edu.pjwstk.chemcalc.code.datastore.ISBAStore;
import pl.edu.pjwstk.chemcalc.code.interpreter.qres.IQResStack;
import pl.edu.pjwstk.chemcalc.code.interpreter.qres.QResStack;
import pl.edu.pjwstk.chemcalc.code.model.ChemicalElement;
import pl.edu.pjwstk.chemcalc.code.model.PeriodicTable;
import pl.edu.pjwstk.chemcalc.code.result.BracketResult;
import pl.edu.pjwstk.chemcalc.code.result.ChemicalFormulaResult;
import pl.edu.pjwstk.chemcalc.code.result.CompoundResult;
import pl.edu.pjwstk.chemcalc.code.result.DoubleResult;
import pl.edu.pjwstk.chemcalc.code.result.ElementResult;
import pl.edu.pjwstk.chemcalc.code.result.FormulaResult;
import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;
import pl.edu.pjwstk.chemcalc.code.result.IBracketResult;
import pl.edu.pjwstk.chemcalc.code.result.ICompoundResult;
import pl.edu.pjwstk.chemcalc.code.result.IElementResult;
import pl.edu.pjwstk.chemcalc.code.result.IIntegerResult;
import pl.edu.pjwstk.chemcalc.code.result.ISimpleResult;
import pl.edu.pjwstk.chemcalc.code.result.ISingleResult;
import pl.edu.pjwstk.chemcalc.code.result.IntegerResult;
import pl.edu.pjwstk.chemcalc.code.result.StringResult;

public class Interpreter implements IInterpreter {
    private ISBAStore store;
    private IQResStack qres;
    private IENVS envs;
    private PeriodicTable ptable = new PeriodicTable();

    public Interpreter(ISBAStore aStore) {
        store = aStore;
    }

    public void visitIntegerTerminal(IIntegerTerminal expr) {
        this.qres.push(createSimpleResult(expr.getValue()));
    }

    public void visitDoubleTerminal(IDoubleTerminal expr) {
        this.qres.push(createSimpleResult(expr.getValue()));
    }

    public void visitStringTerminal(IStringTerminal expr) {
        this.qres.push(createSimpleResult(expr.getValue()));
    }

    private IIntegerResult toIntegerResult(IAbstractQueryResult res) {
        IIntegerResult irs;
        if (res instanceof IntegerResult) {
            irs = new IntegerResult(((IntegerResult) res).getValue());
        } else if (res instanceof IntegerTerminal) {
            irs = new IntegerResult(((IntegerTerminal) res).getValue());
        } else {
            throw new RuntimeException();
        }
        return irs;
    }

    private IElementResult toElementResult(IAbstractQueryResult res) {
        IElementResult ele;
        if (res instanceof StringResult) {
            String symbol = ((StringResult) res).getValue();
            ChemicalElement chel = ptable.getChemicalElement(symbol);
            ele = new ElementResult(chel);
        } else if (res instanceof ElementResult) {
            ele = ((ElementResult) res).getValue();
        } else {
            throw new RuntimeException();
        }
        return ele;
    }

    public void visitElementExpression(IElementExpression expr) {
        expr.getInnerExpression().accept(this);
        IElementResult result = toElementResult(qres.pop());
        qres.push(result);
    }

    public void visitCompoundExpression(ICompoundExpression expr) {
        expr.getLeftExpression().accept(this);
        expr.getRightExpression().accept(this);

        IIntegerResult rightResult = toIntegerResult(qres.pop());
        IElementResult leftResult = toElementResult(qres.pop());

        ICompoundResult result = new CompoundResult((ElementResult) leftResult,
                (IntegerResult) rightResult);
        qres.push(result);
    }

    public void visitChemicalFormulaExpression(IChemicalFormulaExpression expr) {
        expr.getLeftExpression().accept(this);
        expr.getRightExpression().accept(this);

        ISingleResult rightResult = toSingleResult(qres.pop());
        ISingleResult leftResult = toSingleResult(qres.pop());

        ChemicalFormulaResult result = new ChemicalFormulaResult(leftResult,
                rightResult);
        qres.push(result);
    }

    public void visitFormulaExpression(IFormulaExpression expr) {
        expr.getLeftExpression().accept(this);
        expr.getRightExpression().accept(this);

        ISingleResult rightResult = toSingleResult(qres.pop());
        ISingleResult leftResult = toSingleResult(qres.pop());

        FormulaResult result = new FormulaResult(leftResult, rightResult);
        qres.push(result);
    }

    public void visitBracketExpression(IBracketExpression expr) {
        expr.getLeftExpression().accept(this);
        expr.getRightExpression().accept(this);

        IIntegerResult rightResult = toIntegerResult(qres.pop());
        ISingleResult leftResult = toSingleResult(qres.pop());

        IBracketResult result = new BracketResult(leftResult,
                (IntegerResult) rightResult);
        qres.push(result);
    }

    public ISimpleResult<?> createSimpleResult(Object object) {
        if (object == null)
            return null;

        if (object instanceof String) {
            return new StringResult((String) object);
        } else if (object instanceof Double) {
            return new DoubleResult((Double) object);
        } else if (object instanceof Integer) {
            return new IntegerResult((Integer) object);
        } else {
            System.out.println("error: Could not create a simple result");
            throw new RuntimeException();
        }
    }

    public ISingleResult toSingleResult(IAbstractQueryResult res) {
        if (res == null)
            return null;

        if (res instanceof ISingleResult) {
            return (ISingleResult) res;
        } else {
            System.out.println("error: This is not a single result");
            return null;
        }
    }

    public void init() {
        qres = new QResStack();
        envs = new ENVS();
        envs.init(store.getEntryOID(), store);
    }

    public IAbstractQueryResult eval(IExpression treeRoot) {
        try {
            init();
            treeRoot.accept(this);
            return qres.pop();
        } catch (RuntimeException e) {
            throw e;
        }

    }
}