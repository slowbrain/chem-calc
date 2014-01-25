package pl.edu.pjwstk.chemcalc.code.api.v1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class ErrorHandlerServlet extends HttpServlet {
    // Method to handle GET method request.
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) req
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        resp.setContentType("application/json; charset=utf8");
        PrintWriter printout = resp.getWriter();

        JSONObject JObject = null;
        try {
            JObject = new JSONObject();
            JObject.put("exception", throwable);
            JObject.put("code", statusCode);
            JObject.put("name", servletName);
            JObject.put("uri", requestUri);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        printout.print(JObject);
        printout.flush();
    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}