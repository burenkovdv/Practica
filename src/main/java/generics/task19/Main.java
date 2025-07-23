package generics.task19;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Пример 1: 3×3
        List<List<Integer>> m1 = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        System.out.println(rotateCounterClockwise(m1));
        // → [[3, 6, 9], [2, 5, 8], [1, 4, 7]]

        // Пример 2: 2×3
        List<List<Character>> m2 = List.of(
                List.of('a', 'b', 'c'),
                List.of('d', 'e', 'f')
        );
        System.out.println(rotateCounterClockwise(m2));
        // → [[c, f], [b, e], [a, d]]


    }


    /**
     * Транспонирует заданную «матрицу» — список списков равной длины:
     * возвращает новую матрицу, где ряды и столбцы поменяны местами.
     *
     * @param matrix исходный список списков, в котором каждый вложенный список
     *               имеет одинаковую длину N
     * @param <T>    тип элементов
     * @return новый список списков размера N×M, где M — число вложенных списков в matrix
     */
    public static <T> List<List<T>> transpose(List<? extends List<? extends T>> matrix) {
        List<List<T>> result = new ArrayList<>();
        if (matrix.isEmpty()) return result;
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        // создаём по одному списку-столбцу
        for (int j = 0; j < cols; j++) {
            List<T> column = new ArrayList<>(rows);
            // пробегаем по всем строкам
            for (int i = 0; i < rows; i++) {
                column.add(matrix.get(i).get(j));
            }
            result.add(column);
        }
        return result;
    }


    /**
     * Поворачивает заданную «матрицу» (список списков равной длины) на 90° по часовой стрелке.
     *
     * @param matrix исходный список списков (матрица M×N)
     * @param <T>    тип элементов
     * @return новую матрицу размера N×M, повернутую на 90° вправо
     */
    public static <T> List<List<T>> rotateClockwise(List<? extends List<? extends T>> matrix) {
        List<List<T>> result = new ArrayList<>();
        if (matrix.isEmpty()) return result;
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        for (int j = cols - 1; j >= 0; j--) {
            List<T> column = new ArrayList<>(rows);
            // пробегаем по всем строкам
            for (int i = rows - 1; i >= 0; i--) {
                column.add(matrix.get(i).get(j));
            }
            result.add(column);
        }
        return result;
    }


    /**
     * Поворачивает заданную «матрицу» (список списков равной длины) на 90° против часовой стрелки.
     *
     * @param matrix исходный список списков (матрица M×N)
     * @param <T>    тип элементов
     * @return новую матрицу размера N×M, повернутую на 90° влево
     */
    public static <T> List<List<T>> rotateCounterClockwise(
            List<? extends List<? extends T>> matrix
    ) {
        List<List<T>> result = new ArrayList<>();
        if (matrix.isEmpty()) return result;
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        for (int j = cols - 1; j >=0 ; j--) {
            List<T> column = new ArrayList<>(rows);
            // пробегаем по всем строкам
            for (int i = 0; i < rows; i++) {
                column.add(matrix.get(i).get(j));
            }
            result.add(column);
        }


        return result;
    }


}
