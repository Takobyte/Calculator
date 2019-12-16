import java.math.BigDecimal;
import java.math.MathContext;

public class Square implements SpecialOperation {
    @Override
    public BigDecimal operation(String number) {
        BigDecimal ln = new BigDecimal(number);
        // setting precision to 11 means that in scientific notation,
        // it will always have 10 decimal places
        MathContext mc = new MathContext(11);
        BigDecimal result = ln.pow(2).round(mc);
        return result;
    }
}
