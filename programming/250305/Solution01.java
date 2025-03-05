import java.util.logging.Logger;

public class Solution01 {
    public static void main(String[] args) {
        ICalculator calculator = new Calculator();
        Logger logger = Logger.getLogger("calculator");
        logger.info("%d".formatted(calculator.add(1, 2)));
        logger.info("%d".formatted(calculator.subtract(1, 2)));
        ICalculatorLambda calculatorLambda = (a, b) -> a / b;
        logger.info("%d".formatted(calculatorLambda.run(5, 12)));
        ICalculatorLambda calculatorLambda2 = calculator::add;
        logger.info("%d".formatted(calculatorLambda2.run(5, 12)));
        ICalculatorLambda calculatorLambda3 = Calculator::pythagoras;
        logger.info("%d".formatted(calculatorLambda3.run(5, 12)));
    }
}

@FunctionalInterface
interface ICalculatorLambda {
    int run(int a, int b);
}

class Calculator implements ICalculator{

    public static int pythagoras(int a, int b) {
        return (int)Math.pow(Math.pow(a, 2) * Math.pow(b, 2), 0.5);
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}

interface ICalculator {
    int add(int a, int b);
    int subtract(int a, int b);
}