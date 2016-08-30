package bondarmih.edu.consumer;

import bondarmih.edu.cache.Cache;
import bondarmih.edu.util.InjectCache;

/**
 * Created by bondarm on 22.05.16.
 */
public class DailyCacheConsumer implements Consumer {

    @InjectCache(cacheName = "DailyCache")
    private Cache cacheHolder;


    public String getCachedItem(int itemId){
        return this.cacheHolder.getById(itemId);
    }

    public void printOutput() {
        System.out.println(this.getClass() + " cache output: ");
        for (int i = 0; i<cacheHolder.size();i++) {
            String cachedItem = this.getCachedItem(i);
            System.out.println(i + " : " + cachedItem);
        }
    }

}
