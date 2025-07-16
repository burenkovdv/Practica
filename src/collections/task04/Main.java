package task04;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> input = List.of("apple", "banana", "Apple", "orange", "banana","");

        Set<String> result = doubleString(input);
        System.out.println(result);

    }

    public static Set<String> doubleString (List<String> item){
            Set<String> res = new HashSet<>();
            String p="";
        for (int i = 0; i < item.size() ; i++) {
            if(item.isEmpty()) {continue;};
                p=item.get(i).toLowerCase();
            for (int j = i + 1; j < item.size() ; j++) {
                if(p.equals(item.get(j).toLowerCase())) {
                    res.add(p);
                }
            }
        }
        return res;
    }

}
