import java.math.BigDecimal;

public class Addition implements MathOperation{

    @Override
    public BigDecimal operation(String leftNumber, String rightNumber) {
        BigDecimal ln = new BigDecimal(leftNumber);
        BigDecimal rn = new BigDecimal(rightNumber);
        BigDecimal result = ln.add(rn);
        return result;
    }
}
