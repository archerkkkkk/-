package Test;

import Calculator.Calculate;
import java.util.List;

/**
 * @author Archer
 */
public class Test {
    public static void main(String[] args) {
        String result = "1.0+1";
        System.out.println(Calculate.toInfixExpression(result));
        System.out.println(Calculate.toSuffixExpression(Calculate.toInfixExpression(result)));
        List<String> ls = Calculate.toSuffixExpression(Calculate.toInfixExpression(result));
        double out = Calculate.calculate(ls);
        System.out.println(out);

    }
}
