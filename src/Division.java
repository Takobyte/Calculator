import java.math.BigDecimal;

public class Division implements MathOperation {

    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) {
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        BigDecimal result = ln.divide(rn);
        return result;
    }

}
