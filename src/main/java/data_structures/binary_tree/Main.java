package data_structures.binary_tree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree p1 = new BinarySearchTree();
        p1.insert(10);
        p1.insert(19);
        p1.insert(1);
        p1.insert(99);



        System.out.println(p1.contains(10));
        System.out.println(p1.contains(19));
        System.out.println(p1.contains(99));

        System.out.println(p1.size());
    }
}
