package bondarmih.edu.persistence.dao;

import bondarmih.edu.catalog.Artist;
import bondarmih.edu.persistence.dbservice.Executor;

import java.sql.Connection;
import java.util.List;

/**
 * Created by bondarm on 15.06.16.
 */
public class ArtistDbDao implements ArtistDao {

    private Executor executor;

    public ArtistDbDao(Connection connection) {
        this.executor = new Executor(connection);
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
