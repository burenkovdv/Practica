package lambda_streamapi.task06;

import java.util.Arrays;
import java.util.List;

public class ReduceTask {
    public static int sumOfOddSquares(List<Integer> numbers) {
        return numbers.stream().filter(x -> x % 2 != 0)
                .map(x -> x * x)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        int result = sumOfOddSquares(nums);
        System.out.println(result); // Ожидается: 35
    }
}
