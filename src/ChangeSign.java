import java.math.BigDecimal;

public class ChangeSign implements SpecialOperation {
    @Override
    public BigDecimal operation(String number) {
        BigDecimal result = new BigDecimal(number);
        return result.negate();
    }
}
