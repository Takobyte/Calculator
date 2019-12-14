import java.math.BigDecimal;

public class Square implements SpecialOperation {
    @Override
    public BigDecimal operation(String number) {
        BigDecimal ln = new BigDecimal(number);
        BigDecimal rn = new BigDecimal(number);
        BigDecimal result = ln.pow(2);
        return result;
    }
}
