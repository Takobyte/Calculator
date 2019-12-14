import java.math.BigDecimal;

public interface MathOperation {

    // Effect: calculate the number depending on the operators used
    // Example: leftNumber = 2.0, rightNumber = 3.0, If + operator is used
    // it should give back result = 5.0
    BigDecimal operation(String leftNumber, String rightNumber);
}
