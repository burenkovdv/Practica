package lambda_streamapi.task01;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = Stream.iterate(0, x -> x <= 20, x -> x + 1)
                .collect(Collectors.toList());
        nums.removeIf(x -> x % 2 == 1);


        System.out.println(nums);
        nums.replaceAll(x -> x * x);
        System.out.println(nums);
        nums.sort(Comparator.reverseOrder());
        System.out.println(nums);
    }
}
