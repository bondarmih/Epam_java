package bondarmih.edu.persistence.xml;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by bondarm on 14.06.16.
 */
public class XmlDocumentHolderFactory {
    public XmlDocumentHolder getXmlDocument(String filename) {
        DOMParser parser = new DOMParser();
        try {
            parser.parse(filename);
            Document document = parser.getDocument();
            if (documentIsValid()) {
                return new XmlDocumentHolder(document);
            }
            else {
                throw new IllegalStateException("Document is not valid");
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can not initiate DOM document");
        }
    }

    private boolean documentIsValid() {
        return true;
    }

    public XmlDocumentHolder getXmlDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element artists = document.createElement("artists");
            document.appendChild(artists);
            return new XmlDocumentHolder(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can not initiate DOM document");
        }

    }
}
