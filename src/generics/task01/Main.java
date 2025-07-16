package generics.task01;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 2, 9, 1, 7);
        //Pair<Integer, Integer> result = minMax(numbers);
        //System.out.println(result.getKey());   // 1 (min)
        //System.out.println(result.getValue()); // 9 (max)



    }




}

 class Pair<K, V> {

    private K key;
    private V value;


     public Pair(K key, V value) {
         this.key = key;
         this.value = value;
     }

     Pair (){};

     public K getKey() {
         return key;
     }

     public void setKey(K key) {
         this.key = key;
     }

     public V getValue() {
         return value;
     }

     public void setValue(V value) {
         this.value = value;
     }




 }