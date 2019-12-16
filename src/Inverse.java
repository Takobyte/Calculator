import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Inverse implements SpecialOperation, MathOperation {

    @Override
    public BigDecimal operation(String number) {
        BigDecimal ln = new BigDecimal("1");
        BigDecimal rn = new BigDecimal(number);
        // setting precision to 11 means that in scientific notation,
        // it will always have 10 decimal places
        MathContext mc = new MathContext(11);
        BigDecimal result = ln.divide(rn, mc);
        return result;
    }

    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) {
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        // setting precision to 11 means that in scientific notation,
        // it will always have 10 decimal places
        MathContext mc = new MathContext(11);
        BigDecimal result = rn.divide(ln,mc);
        return result;
    }
}
