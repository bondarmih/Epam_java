package bondarmih.edu.cache;

import com.sun.istack.internal.Nullable;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by bondarm on 22.05.16.
 */

@bondarmih.edu.util.Cache(name = "WeeklyCache")
public class WeeklyCache implements Cache {
    Map<Integer, String> storage = new WeakHashMap<Integer, String>();
    public void cache(int id, String value) {
        storage.put(id, value);
    }

    @Nullable
    public String getById(int id) {
        if (storage.containsKey(id))
            return storage.get(id);
        else return null;
    }

    public void deleteById(int id) {
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
