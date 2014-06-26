package uberconf;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class MemoizationTrickery {

    interface MemoizedFunction<T, R> {
        Map<MemoizedFunction, Map> cache = new WeakHashMap<>();

        R calc(MemoizedFunction<T, R> func, T t);

        default Map getCache() {
            if (!cache.containsKey(this)) {
                cache.put(this, new HashMap<>());
            }
            return cache.get(this);
        }

        default R apply(T t) {
            Map cache = getCache();
            if (!cache.containsKey(t)) {
                cache.put(t, calc(this, t));
            }
            return (R)cache.get(t);
        }
    }

    public static void main(String[] args) {
        MemoizedFunction<Integer, Integer> fib = (func, n) -> {
          if (n == 0 || n == 1) return n;
           return func.apply(n-1)+func.apply(n-1);
        };
        System.out.println(fib.apply(20));
    }
}
