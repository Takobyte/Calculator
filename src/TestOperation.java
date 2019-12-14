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

    // Test addition for decimal value .0 ex. 11 =/= 11.0
    @Test
    public void testAdditionDecimalNotEql() {
        BigDecimal result = testAddition.operation("5.0", "6.0");
        BigDecimal expected = new BigDecimal("11");
        assertNotEquals(expected,result);
    }

    // Test negative value addition
    @Test
    public void testAdditionNegVal() {
        BigDecimal result = testAddition.operation("-5.0", "6.0");
        BigDecimal expected = new BigDecimal("1.0");
        assertEquals(expected,result);
    }

    // Test negative result addition
    @Test
    public void testAdditionNegResult() {
        BigDecimal result = testAddition.operation("5.0", "-6.0");
        BigDecimal expected = new BigDecimal("-1.0");
        assertEquals(expected,result);
    }

    // Test 0 value addition
    @Test
    public void testAdditionZeroVal() {
        BigDecimal result = testAddition.operation("5.0", "-5.0");
        BigDecimal expected = new BigDecimal("0.0");
        assertEquals(expected,result);
    }

}
