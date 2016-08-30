package bondarmih.edu.persistence.daofactory;

import bondarmih.edu.persistence.dao.ArtistDao;
import bondarmih.edu.persistence.dao.ArtistXmlDao;

/**
 * Created by bondarm on 14.06.16.
 */
public class XmlDaoFactory implements DaoFactory {

    public ArtistDao createArtistDao() {
        return new ArtistXmlDao();
    }
}
