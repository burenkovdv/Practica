package regex.task03;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneFormatter {


    /**
     * Извлекает все телефоны из текста, нормализует их в формат +7XXXXXXXXXX
     * и дополнительно преобразует к читаемому виду: +7 (XXX) XXX-XX-XX.
     *
     * Правила:
     * - На входе те же правила, что и в прошлой задаче (поддержка +7 и 8).
     * - Сохраняется порядок появления и убираются дубли.
     * - Результат в формате: "+7 (999) 123-45-67".
     */
    public static List<String> extractAndFormatPhones(String text) {
        Set<String> result = new LinkedHashSet<>();
        Pattern pattern = Pattern.compile("\\+?[78]\\s?\\(?-?\\d{3}\\s?\\)?-?\\s?\\d{3}\\s?-?\\d{2}\\s?-?\\d{2}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group()
                    .replaceAll("\\(|\\)|-|\\s", "")
                    .replaceAll("^8", "+7");
            if (group.length() == 12 && !group.matches("\\+\\d0{3}\\d+")) {
                StringBuilder builder = new StringBuilder();
                builder.append(group.substring(0, 2))
                        .append(" ")
                        .append("(")
                        .append(group.substring(2, 5))
                        .append(")")
                        .append(" ")
                        .append(group.substring(5, 8))
                        .append("-")
                        .append(group.substring(8, 10))
                        .append("-")
                        .append(group.substring(10, 12));
                result.add(builder.toString());
            }
        }
        return new ArrayList<>(result);
    }
}
