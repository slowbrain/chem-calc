package pl.edu.pjwstk.chemcalc.code.interpreter.qres;

import java.util.Stack;

import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

public class QResStack implements IQResStack {
    private Stack<IAbstractQueryResult> stack = new Stack<IAbstractQueryResult>();

    public void push(IAbstractQueryResult value) {
        stack.push(value);
    }

    public IAbstractQueryResult pop() {
        return stack.empty() ? null : stack.pop();
    }

    public IAbstractQueryResult peek() {
        return stack.peek();
    }
}