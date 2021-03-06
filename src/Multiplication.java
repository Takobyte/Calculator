import java.math.BigDecimal;
import java.math.MathContext;

public class Multiplication implements MathOperation {
    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) throws ArithmeticException{
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        // setting precision to 11 means that in scientific notation,
        // it will always have 10 decimal places
        MathContext mc = new MathContext(11);
        BigDecimal result = ln.multiply(rn).round(mc);
        return result;
    }
}
