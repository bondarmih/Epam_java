package bondarmih.edu.consumer;

import bondarmih.edu.cache.Cache;
import bondarmih.edu.util.InjectCache;

/**
 * Created by bondarm on 25.05.16.
 */
public class DailyCacheConsumerInherit extends HourlyCacheConsumer{
    @InjectCache(cacheName = "WeeklyCache")
    private Cache weekCacheHolder;


    public String getWeeklyCachedItem(int itemId){
        return this.weekCacheHolder.getById(itemId);
    }

    @Override
    public void printOutput() {
        super.printOutput();
        System.out.println(this.getClass() + " week cache output: ");
        for (int i = 0; i<weekCacheHolder.size();i++) {
            String cachedItem = this.getCachedItem(i);
            System.out.println(i + " : " + cachedItem);
        }
    }
}
