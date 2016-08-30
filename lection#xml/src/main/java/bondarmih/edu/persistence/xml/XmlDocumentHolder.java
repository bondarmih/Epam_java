package bondarmih.edu.persistence.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by bondarm on 14.06.16.
 */
public class XmlDocumentHolder {
    Document document;
    public XmlDocumentHolder(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void saveToXml(String fileName) {
        TransformerFactory factory = TransformerFactory.newInstance();
        DOMSource source = new DOMSource(this.document);
        try {
            StreamResult result = new StreamResult(new FileWriter(fileName));
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
        } catch (IOException|TransformerException e) {
            e.printStackTrace();
            throw new IllegalStateException("Saving to file failed");
        }

    }

}
