// https://developers.google.com/appengine/kb/java?hl=pl&csw=1#fileforms
package pl.edu.pjwstk.chemcalc.code;

// http://commons.apache.org/proper/commons-fileupload/
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import pl.edu.pjwstk.chemcalc.code.dao.Dao;
import pl.edu.pjwstk.chemcalc.code.model.ChemicalElement;

/**
 * Servlet which uploads (stored in XML file) periodic table to GAE datastore.
 * 
 * last modified: 9 Dec 2013
 * 
 * @version 1.0
 * @author Adrian Bala
 */
@SuppressWarnings("serial")
public class UploadPeriodicTableServlet extends HttpServlet {
    @SuppressWarnings("unused")
    private static final Logger log = Logger
            .getLogger(UploadPeriodicTableServlet.class.getName());

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            ServletFileUpload upload = new ServletFileUpload();
            res.setContentType("text/plain");

            FileItemIterator iterator = upload.getItemIterator(req);
            while (iterator.hasNext()) {
                FileItemStream item = iterator.next();
                InputStream stream = item.openStream();
                SAXBuilder saxBuilder = new SAXBuilder();
                Document document = saxBuilder.build(stream);

                /*
                 * SBAStore store = new SBAStore(); store.loadXML(document);
                 * store.PrintObjects(store.retrieve(store.getEntryOID()));
                 * System.out.println(store.getString());
                 */

                Integer atnum = 0;
                Double atmass = 0.0;
                String sym = "";
                String nam = "";
                Element element = document.getRootElement();
                for (Element el : element.getChildren()) {
                    List<Element> values = el.getChildren();
                    atnum = Integer.parseInt(values.get(0).getValue());
                    atmass = Double.parseDouble(values.get(1).getValue());
                    sym = values.get(2).getValue();
                    nam = values.get(3).getValue();
                    ChemicalElement chel = new ChemicalElement(atnum, atmass,
                            sym, nam);
                    Dao.INSTANCE.addChemicalElement(chel);
                }
                Dao.INSTANCE.uploadPeriodicTable();
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}