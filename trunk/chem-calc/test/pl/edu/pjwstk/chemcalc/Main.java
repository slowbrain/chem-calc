package pl.edu.pjwstk.chemcalc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import pl.edu.pjwstk.chemcalc.code.ast.IExpression;
import pl.edu.pjwstk.chemcalc.code.datastore.SBAStore;
import pl.edu.pjwstk.chemcalc.code.interpreter.envs.ENVS;
import pl.edu.pjwstk.chemcalc.code.interpreter.envs.Interpreter;
import pl.edu.pjwstk.chemcalc.code.parser.ChemcalcParser;
import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

public class Main {
    public static void main(String[] args) {
        System.out.println("chem-calc");
    }
}