package bondarmih.edu.persistence.dao;

import bondarmih.edu.catalog.Artist;
import bondarmih.edu.persistence.parser.XmlAnalyzer;

import java.util.List;

/**
 * Created by bondarm on 14.06.16.
 */
public class ArtistXmlDao implements ArtistDao {
    private XmlAnalyzer XmlAnalyzer;

    public ArtistXmlDao(XmlAnalyzer XmlAnalyzer) {
        this.XmlAnalyzer = XmlAnalyzer;
    }


    public Artist selectById(int id) {
        return null;
    }

    public List<Artist> getAll() {
        return null;
    }

    public void insert(Artist artist) {

    }

    public void delete(int id) {

    }

    public void update(Artist artist) {

    }
}
