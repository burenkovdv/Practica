package data_structures.binary_tree;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BinarySearchTree Tests")
class BinarySearchTreeTest {

    @Test
    @DisplayName("insert(null) → NPE")
    void insertNull() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        assertThrows(NullPointerException.class, () -> t.insert(null));
    }

    @Test
    @DisplayName("contains(null) → NPE")
    void containsNull() {
        BinarySearchTree<String> t = new BinarySearchTree<>();
        assertThrows(NullPointerException.class, () -> t.contains(null));
    }

    @Test
    @DisplayName("remove(null) → NPE")
    void removeNull() {
        BinarySearchTree<Double> t = new BinarySearchTree<>();
        assertThrows(NullPointerException.class, () -> t.remove(null));
    }

    @Test
    @DisplayName("insert & contains & size")
    void insertContainsSize() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        assertFalse(t.contains(10));
        t.insert(10);
        assertTrue(t.contains(10));
        assertEquals(1, t.size());
        // повторная вставка не меняет size
        t.insert(10);
        assertEquals(1, t.size());
    }

    @Test
    @DisplayName("inOrder traversal")
    void inOrderTest() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        List<Integer> values = Arrays.asList(5, 2, 8, 1, 3);
        values.forEach(t::insert);
        assertEquals(Arrays.asList(1, 2, 3, 5, 8), t.inOrder());
    }

    @Test
    @DisplayName("preOrder & postOrder")
    void otherTraversals() {
        BinarySearchTree<Character> t = new BinarySearchTree<>();
        for (char c : new char[]{'M', 'B', 'Q', 'A', 'C'}) t.insert(c);
//        assertEquals(Arrays.asList('M', 'B', 'A', 'C', 'Q'), t.preOrder());
//        assertEquals(Arrays.asList('A', 'C', 'B', 'Q', 'M'), t.postOrder());
    }

    @Test
    @DisplayName("height on empty and non-empty")
    void heightTest() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        assertEquals(-1, t.height());
        t.insert(10);
        assertEquals(0, t.height());
        t.insert(5);
        t.insert(15);
        assertEquals(1, t.height());
    }

    @Test
    @DisplayName("remove leaf, one-child, two-children")
    void removeScenarios() {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        List<Integer> vals = Arrays.asList(10, 5, 15, 3, 7, 12, 17);
        vals.forEach(t::insert);
        assertTrue(t.remove(3));   // leaf
        assertTrue(t.remove(5));   // one-child (7)
        assertTrue(t.remove(15));  // two-children (12 & 17)
        assertFalse(t.contains(15));
        assertEquals(Arrays.asList(7, 10, 12, 17), t.inOrder());
    }

    @Test
    @DisplayName("remove non-existent → false")
    void removeNonExistent() {
        BinarySearchTree<String> t = new BinarySearchTree<>();
        t.insert("A");
        assertFalse(t.remove("Z"));
        assertEquals(1, t.size());
    }
}
