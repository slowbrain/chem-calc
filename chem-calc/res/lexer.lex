package pl.edu.pjwstk.chemcalc.code.lexer;

import java_cup.runtime.Symbol;
import static pl.edu.pjwstk.chemcalc.code.parser.Symbols.*;

%%
%{
    private Symbol createToken(int id) {
        return new Symbol(id, yyline, yycolumn);
    }
    private Symbol createToken(int id, Object o) {
        return new Symbol(id, yyline, yycolumn, o);
    }
%}

%public
%class Lexer
%cup
%line
%column
%char
%eofval{
    return createToken(EOF);
%eofval}

   LINETERMINATOR = \r|\n|\r\n
       WHITESPACE = {LINETERMINATOR}|[ \t\f]

      LEFTBRACKET = "("|"["|"{"
     RIGHTBRACKET = ")"|"]"|"}"
  
         LOWINDEX = "_"
         OPERATOR = "∙"|"•"|"*"|":"|"::"|":::"|"-"|"="|"≡"|"<"|"←"|"<-"|"⇐"|"<="|"<≡"|"≡>"|"=>"|"⇒"|"->"|"→"|">"|"<--"|"<=="|"<≡≡"|"≡≡>"|"==>"|"-->"
 
          INTEGER = [0-9]+

        BIGLETTER = [A-Z]
      SMALLLETTER = [a-z]

          ELEMENT = {BIGLETTER}({SMALLLETTER}{SMALLLETTER}?)?

%%
<YYINITIAL> {
    {WHITESPACE} { }
    {LEFTBRACKET} {
        return createToken(LEFT_BRACKET);
    }
    {RIGHTBRACKET} {
        return createToken(RIGHT_BRACKET);
    }
    {LOWINDEX} {
        return createToken(LOW_INDEX);
    }
    {OPERATOR} {
        String val;
        try {
            val = new String(yytext());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return createToken(OP, new String(val));
        //return createToken(OP);
    }
    {INTEGER} {
        int val;
        try {
            val = Integer.parseInt(yytext());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return createToken(INTEGER_LITERAL, new Integer(val));
    }
    {ELEMENT} {
        String val = "";
        try {
            val = new String(yytext());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return createToken(ELEMENT_EXPRESSION, val);
    }
}