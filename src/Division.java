import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division implements MathOperation {

    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) {
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        BigDecimal result = ln.divide(rn,10, RoundingMode.HALF_UP);
        return result;
    }

}
