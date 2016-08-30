package bondarmih.edu.cache;

/**
 * Created by bondarm on 21.05.16.
 */
public interface Cache {
    void cache(int id, String value);
    String getById(int id);
    void deleteById(int id);
    void clear();
    int size();
}
