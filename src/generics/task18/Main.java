package generics.task18;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Number> target = new ArrayList<>(List.of(1, 2.5));
        List<Integer> source = List.of(2, 3, 4);

        mergeDistinct(target, source);

        System.out.println(target);  // [1, 2.5, 3, 4]

    }

    public static <T> void replaceAllOccurrences(
            List<? super T> list,
            T oldValue,
            T newValue
    ) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(oldValue)) {
                list.set(i,newValue);
            }
        }

    }

    public static <T> void mergeDistinct(
            List<? super T> target,
            List<? extends T> source
    ) {
        for (T element : source) {
            if(!target.contains(element)){target.add(element);}
        }
        
    }


}
