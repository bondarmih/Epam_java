package bondarmih.edu.cache;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 22.05.16.
 */
public class CacheFactory {

    public static Class[] getCaches(){

        try {
            Class[] cacheList = {
                    Class.forName("bondarmih.edu.cache.HourlyCache"),
                    Class.forName("bondarmih.edu.cache.DailyCache"),
                    Class.forName("bondarmih.edu.cache.WeeklyCache")
            };
            return cacheList;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cache class not found in bondarmih.edu.cache");
        }
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
