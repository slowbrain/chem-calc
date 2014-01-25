package pl.edu.pjwstk.chemcalc.code.api.v1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.edu.pjwstk.chemcalc.code.dao.Dao;
import pl.edu.pjwstk.chemcalc.code.model.Formula;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Servlet which calculates the chemical formula via RESTful API and JSON.
 * 
 * last modified: 9 Dec 2013
 * 
 * @version 1.0
 * @author Adrian Bala
 */
@SuppressWarnings("serial")
public class CalcFormulaServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String chemform = checkNull(req.getParameter("chemform"));

        resp.setContentType("application/json; charset=utf8");
        PrintWriter printout = resp.getWriter();

        JSONObject JObject = null;
        try {
            // if already calculated ...
            Formula formula = Dao.INSTANCE.getFormula(chemform);
            if (formula == null) {
                formula = Dao.INSTANCE.calcFormula(chemform);
            } else {
                JObject = new JSONObject();
                JObject.put("chemform", formula.getChemform());
                JObject.put("result", formula.getResult());
                JObject.put("mass", formula.getMass());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        printout.print(JObject);
        printout.flush();
    }

    private String checkNull(String s) {
        return (s == null) ? "" : s;
    }
}