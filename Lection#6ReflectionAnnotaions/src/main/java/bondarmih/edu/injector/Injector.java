package bondarmih.edu.injector;

import bondarmih.edu.classinspector.ClassInspector;
import bondarmih.edu.cache.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bondarm on 22.05.16.
 */
public class Injector {

    public static void inject(Object target) {

        Field[] annotatedFields = ClassInspector.getAnnotatedField(target.getClass(), bondarmih.edu.util.InjectCache.class);
        List<Field> fieldList = new LinkedList<Field>();
        Collections.addAll(fieldList, annotatedFields);
        System.out.println(fieldList.size() + " field(s) found");
        for (Field field: fieldList) {
            if (field == null) throw new IllegalArgumentException("Injected cache is null.");
            System.out.println("Class name: " + field.getDeclaringClass() +", Field name: "+ field.getName());

            String annotateCache = field.getAnnotation(bondarmih.edu.util.InjectCache.class).cacheName();
            Class<Cache>[] caches;
            Cache injectedCache = null;
            try {
                 caches = CacheFactory.getCaches();
                for (int i = 0; i < caches.length; i++) {
                    boolean isAnnotationForCache = ClassInspector.isAnnotaionExist(caches[i], bondarmih.edu.util.Cache.class);
                    if (isAnnotationForCache ){
                        String cacheNameValue = caches[i].getAnnotation(bondarmih.edu.util.Cache.class).name();
                        boolean isAnnotationCacheNamesEquals = cacheNameValue.equals(annotateCache);
                        if (isAnnotationCacheNamesEquals) {
                            injectedCache = CacheFactory.getCache(caches[i]);
                            break;
                        }
                    }
                }
            } catch (IllegalStateException e) {
                System.out.println("Can not get Cache from CacheFactory");
            }
            if (injectedCache != null) {
                try {
                    field.setAccessible(true);
                    field.set(target, injectedCache);
                    System.out.println("Cache " + injectedCache.toString() + " is injected to consumer " + target.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Nothing to inject. Can not find proper cache class for annotation "+ annotateCache);
                throw new IllegalStateException();
            }

        }
    }




}
