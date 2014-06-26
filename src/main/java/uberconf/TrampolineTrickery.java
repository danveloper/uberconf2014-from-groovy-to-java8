package uberconf;

public class TrampolineTrickery {

    interface TrampolineFunction<T, R> {
        R apply(TrampolineFunction<T, R> f, T...objs);

        default Object trampoline(T...objs) {
            Object result = apply(this, objs);
            if (!(result instanceof TrampolineFunction)) {
                return result;
            } else {
                return this;
            }
        }
    }

    public static void main(String[] args) {
        TrampolineFunction<Integer, Object> trampoline = (TrampolineFunction<Integer, Object> func, Integer... objs) -> {
            Integer n = objs[0];
            Integer a = objs.length >= 2 ? objs[1] : 0;
            Integer b = objs.length >= 3 ? objs[2] : 1;
            if (n == 0) return a;
            else return func.trampoline(n-1, b, a+b);
        };
        System.out.println(trampoline.trampoline(1000));
    }
}
