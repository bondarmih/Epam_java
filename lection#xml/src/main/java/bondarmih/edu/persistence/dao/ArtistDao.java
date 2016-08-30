package bondarmih.edu.persistence.dao;

import bondarmih.edu.catalog.Artist;

import java.util.List;

/**
 * Created by bondarm on 14.06.16.
 */
public interface ArtistDao {
    Artist selectById(int id);
    List<Artist> getAll();
    void insert(Artist artist);
    void delete(int id);
    void update(Artist artist);
}
