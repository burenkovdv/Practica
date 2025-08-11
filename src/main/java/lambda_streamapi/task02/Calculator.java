package lambda_streamapi.task02;

public class Calculator {

    public static double calculate(MathOperation op, double a, double b) {
        return op.apply(a,b);
    }
}
