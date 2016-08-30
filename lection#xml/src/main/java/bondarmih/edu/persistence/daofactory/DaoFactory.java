package bondarmih.edu.persistence.daofactory;

import bondarmih.edu.persistence.dao.ArtistDao;

/**
 * Created by bondarm on 14.06.16.
 */
public interface DaoFactory {

    ArtistDao createArtistDao();
}
