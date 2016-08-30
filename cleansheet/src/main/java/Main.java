package main.java;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by bondarm on 17.05.16.
 *
 */
public class Main extends TreeMap<Integer, String> {




    public static void main(String[] args) {
        Map map = new TreeMap();
        map.put(null, "NullKey");
        System.out.println(map.size());
        System.out.println(map.get(null));
    }
}
