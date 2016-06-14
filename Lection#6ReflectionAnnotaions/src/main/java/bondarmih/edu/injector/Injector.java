package bondarmih.edu.injector;

import bondarmih.edu.cache.Cache;
import bondarmih.edu.classinspector.ClassInspector;
import bondarmih.edu.cache.*;
import bondarmih.edu.util.*;

import java.lang.annotation.Annotation;
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
            processField(field, target);
        }
    }

    private static void processField(Field field, Object target) {
        System.out.println("Class name: " + field.getDeclaringClass() +", Field name: "+ field.getName());
        String annotateCache = field.getAnnotation(bondarmih.edu.util.InjectCache.class).cacheName();
        Cache injectedCache = findCache(annotateCache);
        if (injectedCache != null) {
            try {
                field.setAccessible(true);
                field.set(target, injectedCache);
                System.out.println("Cache " + injectedCache.toString() + " is injected to consumer " + target.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("Injection to field failed: access error. Field: " + field.toString() + " in class" +target.getClass());
                System.exit(-1);
            }
        } else {
            System.out.println("Nothing to inject. Can not find proper cache class for annotation "+ annotateCache);
            System.exit(-1);
        }
    }

    private static Cache findCache(String cacheAnnotationString) {
        Class[] caches = CacheFactory.getCaches();
        for (int i = 0; i < caches.length; i++) {
            if (caches[i].isAnnotationPresent(bondarmih.edu.util.Cache.class)) {
                bondarmih.edu.util.Cache cache =
                        (bondarmih.edu.util.Cache) caches[i].getAnnotation(bondarmih.edu.util.Cache.class);
                String cacheNameValue = cache.name();
                boolean isAnnotationCacheNamesEquals = cacheNameValue.equals(cacheAnnotationString);
                if (isAnnotationCacheNamesEquals) {
                    return CacheFactory.getCache(caches[i]);
                }
            }
        }
        return null;
    }
}
