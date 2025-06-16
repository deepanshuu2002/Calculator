package testCalculator;

import org.example.Calculator;
import org.junit.Ignore;
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

    @Test
    public void testNegativeAndDoubleComma() {
        assertEquals(
                "Negative not allowed : -1\n" +
                        "Number expected but ',' found at position 3.",
                Calculator.calculate("-1,,2")
        );
    }

    @Test
    public void testMultipleNegativesAndDoubleComma() {
        assertEquals(
                "Negative not allowed : -1\n" +
                        "Number expected but ',' found at position 3.\n" +
                        "Negative not allowed : -2",
                Calculator.calculate("-1,,-2")
        );
    }

    @Test
    public void testNegativeAndUnexpectedCharacter() {
        assertEquals(
                "Negative not allowed : -3\n" +
                        "',' expected but 'a' found at position 3.",
                Calculator.calculate("-3,a,2")
        );
    }

    @Test
    public void testNegativeUnexpectedNewline() {
        assertEquals(
                "Negative not allowed : -4\n" +
                        "Number expected but '\\n' found at position 3.",
                Calculator.calculate("-4,\n2")
        );
    }


    @Test
    public void testCustomDelimiterNegativeAndMismatchDelimiter() {
        assertEquals(
                "Negative not allowed : -2\n" +
                        "'|' expected but ',' found at position 6.",
                Calculator.calculate("//|\n-2,3|4")
        );
    }

    @Test
    public void testNegativeEOFandDoubleComma() {
        assertEquals(
                "Negative not allowed : -1\n" +
                        "Number expected but ',' found at position 3.\n" +
                        "Number expected but EOF found.",
                Calculator.calculate("-1,,")
        );
    }


    @Test
    public void testCustomDelimiterNegativeNewlineAndMismatchDelimiter() {
        assertEquals(
                "Negative not allowed : -2\n" +
                        "Number expected but '\\n' found at position 9.\n" +
                        "'@' expected but ',' found at position 10.",
                Calculator.calculate("//@\n1@-2@\n,3")
        );
    }

    @Test
    public void tt() {
        assertEquals(
                "6",
                Calculator.calculate("//|\n1|2|3")
        );
    }
}