import java.math.BigDecimal;

public class Percent implements SpecialOperation {

    // Effect: gives decimal value of percent number
    @Override
    public BigDecimal operation(String number) {
        BigDecimal ln = new BigDecimal(number);
        BigDecimal rn = new BigDecimal("0.01");
        BigDecimal result = ln.multiply(rn);
        return result;
    }
}
