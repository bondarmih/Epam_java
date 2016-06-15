package bondarmih.edu.persistence.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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



}
