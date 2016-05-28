package bondarmih.edu.cache;

import javax.annotation.Nullable;

/**
 * Created by bondarm on 22.05.16.
 */
public class CacheFactory {

    private static final String cacheClassPath = "bondarmih.edu.cache.";
    @Nullable
    public static Cache getCache(String CacheName) {
        try {
            Cache cache = (Cache)Class.forName(CacheNameHolder.getCacheLocation(CacheName)).newInstance();
            populateCache(cache);
            return cache;
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + CacheName + " not found");
        } catch (InstantiationException e) {
            System.out.println("Can not initiate new instance of " + CacheName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void populateCache(Cache populatedCache) {
        populatedCache.cache(0,"zero");
        populatedCache.cache(1,"bondarmih");
        populatedCache.cache(2,"hello");
    }
}
