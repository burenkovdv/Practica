package regex.task01;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractor {

    public static List<String> extractEmails(String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("[\\w-.]+@[\\w-.]+\\.[a-z]{2,}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String group = matcher.group();
            result.add(group);
        }
        return result;
    }
}
