package uberconf;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsExample {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> newList = list.parallelStream().map((n) -> n * 2).collect(Collectors.toList());
        System.out.println(newList);
    }
}
