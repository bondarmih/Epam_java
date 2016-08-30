package bondarmih.edu.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bondarm on 28.05.16.
 */
public class CacheNameHolder {
    private static Map<String, String> cacheNames = new HashMap<String, String>();

    static {
        cacheNames.put("DailyCache", "bondarmih.edu.cache.DailyCache");
        cacheNames.put("HourlyCache", "bondarmih.edu.cache.HourlyCache");
        cacheNames.put("WeeklyCache", "bondarmih.edu.cache.WeeklyCache");
    }

    public static String getCacheLocation(String cacheName) throws IllegalArgumentException {
        if (cacheNames.containsKey(cacheName)){
            return cacheNames.get(cacheName);
        }
        else throw new IllegalArgumentException("No map for CacheName = " + cacheName);
    }
}
