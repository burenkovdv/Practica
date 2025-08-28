package regex.task02;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneExtractor {
    public static List<String> extractPhones(String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\+?[78]\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String group = matcher.group();

            result.add(group);

        }

        return result;
    }
}
