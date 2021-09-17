package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    @Test
    void zeroPlusZero() {
        Assertions.assertEquals(new Fraction(0), new Fraction(0).plus(new Fraction(0)));
    }

    @Test
    void notZeroPlusZero() {
        Assertions.assertEquals(new Fraction(4), new Fraction(4).plus(new Fraction(0)));
    }

    @Test
    void zeroPlusNotZero() {
        Assertions.assertEquals(new Fraction(7), new Fraction(0).plus(new Fraction(7)));
    }

    @Test
    void anyIntegers() {
        Assertions.assertEquals(new Fraction(12), new Fraction(9).plus(new Fraction(3)));
    }

    @Test
    void nonIntegersWithTheSameDenominator() {
        Assertions.assertEquals(
                new Fraction(3, 5),
                new Fraction(1, 5).plus(new Fraction(2, 5)));
    }

    @Test
    void oneDenominatorIsAMultipleOfTheOther() {
        Assertions.assertEquals(
                new Fraction(11, 8),
                new Fraction(3, 4).plus(new Fraction(5, 8)));
    }

    @Test
    void denominatorsOnlyHaveACommonFactor() {
        Assertions.assertEquals(
                new Fraction(26, 45),
                new Fraction(4, 9).plus(new Fraction(2, 15)));
    }

    @Test
    void denominatorsHaveNoCommonFactors() {
        Assertions.assertEquals(
                new Fraction(41, 28),
                new Fraction(5, 7).plus(new Fraction(3, 4)));
    }

    @Test
    void rejectZeroDenominator() {
        try {
            new Fraction(5, 0);
            Assertions.fail("How did you create a Fraction with a 0 denominator?!");
        } catch (IllegalArgumentException success) {
        }
    }

    private static final class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            if (denominator == 0)
                throw new IllegalArgumentException("Denominator can't be 0.");

            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            return new Fraction(
                    this.numerator * that.denominator + that.numerator * this.denominator,
                    this.denominator * that.denominator);
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator == that.numerator * this.denominator;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return String.format("%d/%d", numerator, denominator);
        }
    }
}
