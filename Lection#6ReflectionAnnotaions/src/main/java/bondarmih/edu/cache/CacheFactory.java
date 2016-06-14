package bondarmih.edu.cache;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 22.05.16.
 */
public class CacheFactory {

    public static Class[] getCaches(){

        Class[] cacheList = {
                bondarmih.edu.cache.HourlyCache.class,
                bondarmih.edu.cache.DailyCache.class,
                bondarmih.edu.cache.WeeklyCache.class
        };
        return cacheList;
    }

    @Nullable
    public static Cache getCache(Class cacheClass) {
        try {
            Cache cache = (Cache)cacheClass.newInstance();
            populateCache(cache);
            return cache;
        } catch (InstantiationException e) {
            System.out.println("Can not initiate new instance of " + cacheClass.getName());
            e.printStackTrace();
            System.exit(-1);
        } catch (IllegalAccessException e) {
            System.out.println("Can not get access to " + cacheClass.getName());
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    private static void populateCache(Cache populatedCache) {
        populatedCache.cache(0,"zero");
        populatedCache.cache(1,"bondarmih");
        populatedCache.cache(2,"hello");
    }
}
