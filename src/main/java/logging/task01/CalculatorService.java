package logging.task01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatorService {

    public static void main(String[] args) {
        System.out.println(add(10, 15));
        System.out.println(divide(15, 30));
        System.out.println(divide(10, 0));
    }

    static int add(int a, int b) {
        log.debug("Вызов метода с входными параметрами: a = {}, b = {}", a, b);
        int c = a + b;
        log.info("Результат работы метода = {}", c);
        return c;

    }

    static int divide(int a, int b) {
        log.debug("Вызов метода с входными параметрами: a = {}, b = {}", a, b);
        try {
            int c = a / b;
            log.info("Результат работы метода = {}", c);
            return c;
        } catch (ArithmeticException exception) {
            log.error("Деление на ноль", exception);
            throw exception;
        }
    }
}
