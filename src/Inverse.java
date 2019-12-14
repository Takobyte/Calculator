import java.math.BigDecimal;

public class Inverse implements SpecialOperation, MathOperation {

    @Override
    public BigDecimal operation(String number) {
        BigDecimal ln = new BigDecimal("1");
        BigDecimal rn = new BigDecimal(number);
        BigDecimal result = ln.divide(rn);
        return result;
    }

    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) {
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        BigDecimal result = rn.divide(ln);
        return result;
    }
}
