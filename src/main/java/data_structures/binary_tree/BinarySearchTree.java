package data_structures.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> left, right;

        Node(T value) {
            this.value = value;
        }
    }


    public int height() {
        int count = 0;
        return count;
    }


//    public void insert(T value) {
//        Node<T> ptr = root;
//        while (ptr != null) {
//            if (value.compareTo(ptr.value) < 0) {
//                if (ptr.left == null) {
//                    ptr.left = new Node<>(value);
//                } else {
//                    ptr = ptr.left;
//                }
//            } else if (value.compareTo(ptr.value) == 0) {
//                break;
//            } else {
//                if (ptr.right == null) {
//                    ptr.right = new Node<>(value);
//                } else {
//                    ptr=ptr.right;
//                }
//            }
//        }
//    }


    public void insert(T value) {

        if (root == null) {
            root = new Node<>(value);
            size++;
            return;
        }
        while (root != null) {
            if (value.compareTo(root.value) < 0) {
                if (root.left == null) {
                    root.left = new Node<>(value);
                    size++;
                    return;
                } else {
                    root = root.left;
                }
            } else if (value.compareTo(root.value) == 0) {
            } else {
                if (root.right == null) {
                    root.right = new Node<>(value);
                    size++;
                    return;
                } else {
                    root = root.right;
                }
            }
        }
    }


    public boolean remove(T value) {
        Node<T> ptr = root;
        boolean result = false;

        while (ptr != null) {
            if (value.compareTo(ptr.value) == 0) {
                //если потомков нет можно смело удалять
                if (ptr.left == null && ptr.right == null) {
                    ptr = null;
                    result = true;
                    break;
                    //и тут самое весёлое
                } else {
                    //если у элемента одна дочка, слева
                    if (ptr.left != null && ptr.right == null) {
                        ptr = ptr.left;
                        ptr.left = null;
                        result = true;
                        break;
                        //дочка справа
                    } else if (ptr.left == null && ptr.right != null) {
                        ptr = ptr.right;
                        ptr.right = null;
                        result = true;
                        break;
                        //самое сложное когда 2 дочки
                    } else {
                        //тут не знаю что делать
                        break;
                    }
                }
                //левая часть
            } else if (value.compareTo(ptr.value) < 0) {
                if (ptr.left == null) {
                    break;
                } else {
                    ptr = ptr.left;
                }
                //правая часть
            } else {
                if (ptr.right == null) {
                    break;
                } else {
                    ptr = ptr.right;
                }
            }
        }
        return result;
    }

    public boolean contains(T value) {
        Node<T> ptr = root;
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        while (ptr != null) {
            if (value.compareTo(ptr.value) == 0) {
                return true;
            } else if (value.compareTo(ptr.value) < 0) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        return false;
    }

    public int size() {
        return size + 1;
    }

    public List<T> inOrder() {
        List<T> result = new ArrayList<>();

        return result;
    }

}
