import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class TestOperation {
    private Addition testAddition;
    private Division testDivison;
    private Subtraction testSubtraction;
    private Calculator testCalculator;


    @Before
    public void runBefore() {
        testAddition = new Addition();
        testDivison = new Division();
        testSubtraction = new Subtraction();
        testCalculator = new Calculator();
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

    // Text addition calculation
    @Test
    public void testAdditionCalc() {
        testCalculator.setLeftNumber("5");
        testCalculator.setRightNumber("6");
        testCalculator.setOperator(Operators.ADDITION);
        testCalculator.calculateResult();
        assertEquals("11", testCalculator.getResultText());
    }

    @Test
    public void testSubtractionCalc() {
        testCalculator.setLeftNumber("5");
        testCalculator.setRightNumber("6");
        testCalculator.setOperator(Operators.SUBTRACTION);
        testCalculator.calculateResult();
        assertEquals("-1", testCalculator.getResultText());
    }

    @Test
    public void  testMultiplicationCalc() {
        testCalculator.setLeftNumber("5");
        testCalculator.setRightNumber("5.5");
        testCalculator.setOperator(Operators.MULTIPLICATION);
        testCalculator.calculateResult();
        assertEquals("27.5", testCalculator.getResultText());
    }

    // Test for max on multiplication
    @Test
    public void testMaxMultiplication() {
        testCalculator.setLeftNumber("9999999999");
        testCalculator.setRightNumber("9999999999");
        testCalculator.setOperator(Operators.MULTIPLICATION);
        testCalculator.calculateResult();
        assertEquals("9.9999999980E+19", testCalculator.getResultText());
    }

    // Test for min on multiplication
    @Test
    public void testMinMultiplication() {
        testCalculator.setLeftNumber("-9999999999");
        testCalculator.setRightNumber("9999999999");
        testCalculator.setOperator(Operators.MULTIPLICATION);
        testCalculator.calculateResult();
        assertEquals("-9.9999999980E+19", testCalculator.getResultText());
    }

    // Test for division precision, should round up to 10th decimal place
    @Test
    public void testDivision() {
        testCalculator.setLeftNumber("1");
        testCalculator.setRightNumber("8");
        testCalculator.setOperator(Operators.DIVISION);
        testCalculator.calculateResult();
        assertEquals("0.125", testCalculator.getResultText());
    }

    @Test
    public void testDivisionMaxPrecision() {
        testCalculator.setLeftNumber("5");
        testCalculator.setRightNumber("5.5");
        testCalculator.setOperator(Operators.DIVISION);
        testCalculator.calculateResult();
        assertEquals("0.90909090909", testCalculator.getResultText());
    }

    @Test
    public void testDivisionSciNotationPrecision() {
        testCalculator.setLeftNumber("1");
        testCalculator.setRightNumber("9999999999");
        testCalculator.setOperator(Operators.DIVISION);
        testCalculator.calculateResult();
        assertEquals("1.0000000001E-10", testCalculator.getResultText());
    }

    @Test
    public void testDivisionNegSciNotationPrecision() {
        testCalculator.setLeftNumber("-1");
        testCalculator.setRightNumber("9999999999");
        testCalculator.setOperator(Operators.DIVISION);
        testCalculator.calculateResult();
        assertEquals("-1.0000000001E-10", testCalculator.getResultText());
    }

    @Test
    public void testDivisionZero() {
        testCalculator.setLeftNumber("0");
        testCalculator.setRightNumber("9999999999");
        testCalculator.setOperator(Operators.DIVISION);
        testCalculator.calculateResult();
        assertEquals("0", testCalculator.getResultText());
    }

    @Test
    public void testDivisionError() {
        testCalculator.setLeftNumber("0");
        testCalculator.setRightNumber("0");
        testCalculator.setOperator(Operators.DIVISION);
        testCalculator.calculateResult();
        assertEquals("Division Undefined", testCalculator.getResultText());
    }

    @Test
    public void testInverse() {
        testCalculator.setLeftNumber("5");
        testCalculator.setOperator(Operators.INVERSE);
        testCalculator.calculateResult();
        assertEquals("0.2", testCalculator.getResultText());
    }

    @Test
    public void testPercent() {
        testCalculator.setLeftNumber("5");
        testCalculator.setOperator(Operators.PERCENT);
        testCalculator.calculateResult();
        assertEquals("0.05", testCalculator.getResultText());
    }

    @Test
    public void testPercentZero() {
        testCalculator.setLeftNumber("0");
        testCalculator.setOperator(Operators.PERCENT);
        testCalculator.calculateResult();
        assertEquals("0.00", testCalculator.getResultText());
    }

    @Test
    public void testPercentMin() {
        testCalculator.setLeftNumber("-9999999999");
        testCalculator.setOperator(Operators.PERCENT);
        testCalculator.calculateResult();
        assertEquals("-99999999.99", testCalculator.getResultText());
    }

    @Test
    public void testPercentMax() {
        testCalculator.setLeftNumber("9999999999");
        testCalculator.setOperator(Operators.PERCENT);
        testCalculator.calculateResult();
        assertEquals("99999999.99", testCalculator.getResultText());
    }

    @Test
    public void testSquare() {
        testCalculator.setLeftNumber("5");
        testCalculator.setOperator(Operators.SQUARE);
        testCalculator.calculateResult();
        assertEquals("25", testCalculator.getResultText());
    }

    @Test
    public void testSquareZero() {
        testCalculator.setLeftNumber("0");
        testCalculator.setOperator(Operators.SQUARE);
        testCalculator.calculateResult();
        assertEquals("0", testCalculator.getResultText());
    }

    @Test
    public void testSquareMin() {
        testCalculator.setLeftNumber("-9999999999");
        testCalculator.setOperator(Operators.SQUARE);
        testCalculator.calculateResult();
        assertEquals("9.9999999980E+19", testCalculator.getResultText());
    }

    @Test
    public void testSquareMax() {
        testCalculator.setLeftNumber("9999999999");
        testCalculator.setOperator(Operators.SQUARE);
        testCalculator.calculateResult();
        assertEquals("9.9999999980E+19", testCalculator.getResultText());
    }

    @Test
    public void testSquareRoot() {
        testCalculator.setLeftNumber("25");
        testCalculator.setOperator(Operators.SQUAREROOT);
        testCalculator.calculateResult();
        assertEquals("5", testCalculator.getResultText());
    }

    @Test
    public void testSquareRootZero() {
        testCalculator.setLeftNumber("0");
        testCalculator.setOperator(Operators.SQUAREROOT);
        testCalculator.calculateResult();
        assertEquals("0", testCalculator.getResultText());
    }

    @Test
    public void testSquareRootNegative() {
        testCalculator.setLeftNumber("-25");
        testCalculator.setOperator(Operators.SQUAREROOT);
        testCalculator.calculateResult();
        assertEquals("Invalid Input", testCalculator.getResultText());
    }

    @Test
    public void testSquareRootMax() {
        testCalculator.setLeftNumber("9999999999");
        testCalculator.setOperator(Operators.SQUAREROOT);
        testCalculator.calculateResult();
        assertEquals("99999.999995", testCalculator.getResultText());
    }

}
