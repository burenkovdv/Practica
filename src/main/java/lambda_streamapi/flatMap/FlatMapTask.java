package lambda_streamapi.flatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapTask {
    public static List<String> uniqueWords(List<String> sentences) {
        // TODO:
        // 1) stream()
        // 2) flatMap(...) -> Stream<String>
        // 3) toLowerCase
        // 4) distinct()
        // 5) sorted()
        // 6) collect(Collectors.toList())
        return sentences.stream()
                .flatMap(x-> Arrays.stream(x.split(" ")))
                .map(String::toLowerCase)
                .map(x->x.replaceAll("[!.,-]+", ""))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "Java is great",
                "Streams are powerful",
                "Java and Streams work great together!"
        );

        List<String> words = uniqueWords(sentences);
        System.out.println(words);
        // Ожидается: [and, are, great, is, java, powerful, streams, together, work]
    }

}