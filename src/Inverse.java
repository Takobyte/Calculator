import java.math.BigDecimal;
import java.math.RoundingMode;

public class Inverse implements SpecialOperation, MathOperation {

    @Override
    public BigDecimal operation(String number) {
        BigDecimal ln = new BigDecimal("1");
        BigDecimal rn = new BigDecimal(number);
        BigDecimal result = ln.divide(rn, 10, RoundingMode.HALF_UP);
        return result;
    }

    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) {
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        BigDecimal result = rn.divide(ln,10, RoundingMode.HALF_UP);
        return result;
    }
}
