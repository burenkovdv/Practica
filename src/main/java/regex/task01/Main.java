package regex.task01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+(\\d+)[a-zA-Z]+");
        Matcher matcher = pattern.matcher("Wor123d 45 6some");
        while (matcher.find()) {
            String group = matcher.group(1);
            System.out.println(group);
        }
    }
}
