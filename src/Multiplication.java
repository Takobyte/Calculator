import java.math.BigDecimal;

public class Multiplication implements MathOperation {
    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) {
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        BigDecimal result = ln.multiply(rn);
        return result;
    }
}
