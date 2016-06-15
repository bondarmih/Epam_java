package bondarmih.edu.persistence.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by bondarm on 14.06.16.
 */
public class XmlAnalyzer {
    Document document;
    public XmlAnalyzer(Document document) {
        this.document = document;
    }

    private NodeList getArtistNodes() {
        Element root = document.getDocumentElement();
        NodeList artistNodes = root.getElementsByTagName("artist");
        return artistNodes;
    }
}
