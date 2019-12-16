import java.math.BigDecimal;
import java.math.MathContext;

public class SquareRoot implements SpecialOperation {
    @Override
    public BigDecimal operation(String number) throws ArithmeticException {
        BigDecimal result, ln;
        ln = new BigDecimal(number);


        // set precision to 0, which is exact value
        MathContext mc = new MathContext(11);
        result = ln.sqrt(mc);


        return result;
    }
}
