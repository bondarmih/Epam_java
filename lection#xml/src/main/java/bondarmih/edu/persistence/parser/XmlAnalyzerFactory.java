package bondarmih.edu.persistence.parser;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by bondarm on 14.06.16.
 */
public class XmlAnalyzerFactory {
    public XmlAnalyzer getXmlAnalyzer(String filename) {
        DOMParser parser = new DOMParser();
        try {
            parser.parse(filename);
            Document document = parser.getDocument();
            if (documentIsValid()) {
                return new XmlAnalyzer(document);
            }
            else {
                throw new IllegalStateException();
            }
        } catch (SAXException e) {
            e.printStackTrace();
            throw(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private boolean documentIsValid() {
        return true;
    }
}
