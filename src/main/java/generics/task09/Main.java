package generics.task09;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Carol");
        List<Integer> scores = List.of(90, 85, 92, 75);

        List<Pair<String, Integer>> zipped = zip(names, scores);
        System.out.println(zipped);
// → [(Alice,90), (Bob,85), (Carol,92)]

    }

    /**
     * “Сшивает” два списка в список пар.
     * Итерация идёт пока в обоих списках есть элементы:
     * пара формируется из элементов с одинаковым индексом.
     *
     * @param a   первый список элементов типа A
     * @param b   второй список элементов типа B
     * @param <A> тип элементов первого списка
     * @param <B> тип элементов второго списка
     * @return новый список Pair<A, B>, длина равна min(a.size(), b.size())
     */
    public static <A, B> List<Pair<A, B>> zip(List<? extends A> a, List<? extends B> b) {
        List<Pair<A, B>> result = new ArrayList<>();
        int q = Math.min(a.size(), b.size());
        for (int i = 0; i < q; i++) {
            result.add(new Pair<>(a.get(i),b.get(i)));
        }
        return result;
    }

}

class Pair<A, B> {

    private A key;
    private B value;


    public Pair(A key, B value) {
        this.key = key;
        this.value = value;
    }

    Pair() {
    }

    ;

    public A getKey() {
        return key;
    }

    public void setKey(A key) {
        this.key = key;
    }

    public B getValue() {
        return value;
    }

    public void setValue(B value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
