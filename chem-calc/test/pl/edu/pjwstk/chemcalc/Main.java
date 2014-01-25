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

        ENVS envs1 = new ENVS(); // envs1||
        // QResStack qres1 = new QResStack(); // qres1||
        SBAStore store1 = new SBAStore(); // store1||

        store1.loadXML(".\\res\\testdata.xml");
        envs1.init(store1.getEntryOID(), store1);
        store1.PrintObjects(store1.retrieve(store1.getEntryOID()));
        System.out.println(store1.getString());

        Interpreter interpreter = new Interpreter(store1);

        try {
            // FileInputStream fstream = new
            // FileInputStream(".\\res\\examples.txt");
            FileInputStream fstream = new FileInputStream(".\\res\\test.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            int i = 1;
            while ((strLine = br.readLine()) != null) {
                ChemcalcParser parser = new ChemcalcParser(strLine);
                parser.user_init();
                parser.parse();
                IExpression expression = parser.RESULT;

                IAbstractQueryResult queryResult = interpreter.eval(expression);
                System.out.println("ex" + i + ": " + strLine + " = "
                        + queryResult.toString());
                i++;
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
         * 
         * // 2*3 IExpression expression1 =new IntegerTerminal(3);
         * 
         * IAbstractQueryResult queryResult1 = interpreter.eval(expression1);
         * System.out.println("ex1: "+queryResult1.toString());
         * 
         * IExpression expression2 = new ElementTerminal("Cu"); /* new
         * MultiplyExpression( new IntegerTerminal(2), new IntegerTerminal(3) );
         * 
         * IAbstractQueryResult queryResult2 = interpreter.eval(expression2);
         * System.out.println("ex2: "+queryResult2.toString());
         */
    }
}