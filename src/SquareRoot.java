import java.math.BigDecimal;
import java.math.MathContext;

public class SquareRoot implements SpecialOperation {
    @Override
    public BigDecimal operation(String number) {
        BigDecimal result, ln;
        ln = new BigDecimal(number);

        try {
            // set precision to 0, which is exact value
            MathContext mc = new MathContext(10);
            result = ln.sqrt(mc);

        } catch (Exception e) {
            System.out.println(e);
            result = new BigDecimal("0");
        }
        return result;
    }
}
