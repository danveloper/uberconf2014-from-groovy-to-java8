package uberconf;

import java.util.function.Supplier;

public class Playground {

    static class Funk {
        private int i = 0;

        String dansFunc() {
            return String.valueOf(i++);
        }
    }

    public static void main(String[] args) {
        Funk funk = new Funk();
        funcTaker(funk::dansFunc);
        funcTaker(funk::dansFunc);
        funcTaker(funk::dansFunc);
        funcTaker(funk::dansFunc);
        funcTaker(funk::dansFunc);
    }

    static void funcTaker(Supplier<String> func) {
        System.out.println(func.get());
    }
}
