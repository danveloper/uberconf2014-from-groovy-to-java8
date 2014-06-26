package uberconf;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class TrickeryWithInterfaceDefaults {

    interface Transformer {
        Map<Transformer, Map<String, String>> cache = new WeakHashMap<>();

        default Map<String, String> getCache() {
            if (!cache.containsKey(this)) {
                cache.put(this, new HashMap<>());
            }
            return cache.get(this);
        }

        default String get(String key) {
            return transform(getCache().get(key));
        }

        default String put(String key, String val) {
            Map<String, String> cache = getCache();
            cache.put(key, val);
            return cache.get(key);
        }

        String transform(String val);
    }

    public static void main(String[] args) {
        Transformer transformer = (val) -> String.format("edited_%s", val);
        System.out.println(transformer.put("foo", "bar"));
        System.out.println(transformer.get("foo"));
    }
}
