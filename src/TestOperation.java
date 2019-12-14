import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class TestOperation {
    private Addition testAddition;
    private Division testDivison;
    private Subtraction testSubtraction;


    @Before
    public void runBefore() {
        testAddition = new Addition();
        testDivison = new Division();
        testSubtraction = new Subtraction();
    }


    // Test addition for no decimal value
    @Test
    public void testAdditionInt() {
        BigDecimal result = testAddition.operation("5","6");
        BigDecimal expected = new BigDecimal("11");
        assertEquals(expected, result);
    }

    // Test addition for decimal value
    @Test
    public void testAdditionDecimal() {
        BigDecimal result = testAddition.operation("5.1", "6.0");
        BigDecimal expected = new BigDecimal("11.1");
        assertEquals(expected,result);
    }

}
