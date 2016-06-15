package bondarmih.edu.util;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Track;
import bondarmih.edu.persistence.dao.ArtistDao;
import bondarmih.edu.persistence.dao.ArtistXmlDao;
import bondarmih.edu.persistence.daofactory.XmlDaoFactory;
import bondarmih.edu.persistence.xml.XmlDocumentHolder;
import bondarmih.edu.persistence.xml.XmlDocumentHolderFactory;
import com.sun.org.apache.xml.internal.resolver.Catalog;

/**
 * Created by bondarm on 16.06.16.
 */
public class CatalogToXMLCreator {
    public static void create() {
        Artist lenny = new Artist(0, "Lenny Kravitz");
        Album baptizm = new Album(0, "Baptizm", "Funk Rock");
        lenny.addAlbum(baptizm);
        baptizm.addTrack(new Track(0, "Baptizm", 315));

        XmlDaoFactory factory = new XmlDaoFactory();
        XmlDocumentHolderFactory documentHolderFactory = new XmlDocumentHolderFactory();
        XmlDocumentHolder documentHolder = documentHolderFactory.getXmlDocument();
        ArtistDao dao = factory.createArtistDao();
        dao.insert(lenny);
    }
}
