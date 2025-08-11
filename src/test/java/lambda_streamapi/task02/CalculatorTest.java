package lambda_streamapi.task02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddition() {
        MathOperation add = (a, b) -> a + b;
        assertEquals(5.0, Calculator.calculate(add, 2, 3));
        assertEquals(-1.0, Calculator.calculate(add, -2, 1));
    }

    @Test
    void testSubtraction() {
        MathOperation sub = (a, b) -> a - b;
        assertEquals(-1.0, Calculator.calculate(sub, 2, 3));
        assertEquals(-3.0, Calculator.calculate(sub, -2, 1));
    }

    @Test
    void testMultiplication() {
        MathOperation mul = (a, b) -> a * b;
        assertEquals(6.0, Calculator.calculate(mul, 2, 3));
        assertEquals(-2.0, Calculator.calculate(mul, -2, 1));
    }

    @Test
    void testDivision() {
        MathOperation div = (a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        };
        assertEquals(2.0, Calculator.calculate(div, 4, 2));
        assertThrows(ArithmeticException.class, () -> Calculator.calculate(div, 5, 0));
    }
}
