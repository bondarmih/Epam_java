package bondarmih.edu.cache;

import com.sun.istack.internal.Nullable;

import java.util.LinkedHashMap;
import java.util.StringTokenizer;

/**
 * Created by bondarm on 21.05.16.
 */

@bondarmih.edu.util.Cache(name = "HourlyCache")
public class HourlyCache implements Cache {
    LinkedHashMap<Integer, String> storage;
    public HourlyCache() {
        this.storage = new LinkedHashMap<Integer, String>();
    }

    public void cache(int id, String value) {
        storage.put(id, value);
    }

    @Nullable
    public String getById(int id) {
        if (storage.containsKey(id))
            return storage.get(id);
        else return null;
    }

    public void deleteById(int id) throws IllegalArgumentException {
        if (storage.containsKey(id))
            storage.remove(id);
        else throw new IllegalArgumentException("No such key");
    }

    public void clear() {
        if (!storage.isEmpty()) storage.clear();
    }

    public int size() {
        return storage.size();
    }
}
