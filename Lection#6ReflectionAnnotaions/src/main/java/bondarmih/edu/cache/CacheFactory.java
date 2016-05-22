package bondarmih.edu.cache;

import javax.annotation.Nullable;

/**
 * Created by bondarm on 22.05.16.
 */
public class CacheFactory {

    private static final String cacheClassPath = "bondarmih.edu.cache.";
    @Nullable
    public static Cache getCache(String cacheName) {
        try {
            Cache cache = (Cache)Class.forName(cacheClassPath + cacheName).newInstance();
            populateCache(cache);
            return cache;
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + cacheName + " not found");
        } catch (InstantiationException e) {
            System.out.println("Can not initiate new instance of " + cacheName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void populateCache(Cache populatedCache) {
        populatedCache.cache(0,"zero");
        populatedCache.cache(1,"bondarmih");
        populatedCache.cache(2,"hello");
    }
}
