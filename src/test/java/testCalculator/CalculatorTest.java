package testCalculator;

import org.example.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testEmptyInput() {
        assertEquals("0", Calculator.calculate(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals("5", Calculator.calculate("5"));
    }

    @Test
    public void testSimpleCommaSeparatedNumbers() {
        assertEquals("6", Calculator.calculate("1,2,3"));
    }

    @Test
    public void testSimpleCommaAtEnd() {
        assertEquals("Number expected but EOF found.", Calculator.calculate("1,2,3,"));
    }

    @Test
    public void testNewlineSeparatedNumbers() {
        assertEquals("10", Calculator.calculate("4\n3\n3"));
    }

    @Test
    public void testCustomDelimiter() {
        assertEquals("6", Calculator.calculate("//|\n1|2|3"));
    }

    @Test
    public void testInvalidInputDoubleComma() {
        assertEquals("Number expected but ',' found at position 2.", Calculator.calculate("1,,2"));
    }

    @Test
    public void testInvalidCustomDelimiterMismatch() {
        assertEquals("'sep' expected but 'X' found at position 14.", Calculator.calculate("//sep\n1sep2sepX"));
    }

    @Test
    public void testCustomDelimiterWithLetters() {
        assertEquals("6", Calculator.calculate("//sep\n1sep2sep3"));
    }

    @Test
    public void testDecimalNumbers() {
        assertEquals("6.6", Calculator.calculate("2.2,2.2,2.2"));
    }
}
