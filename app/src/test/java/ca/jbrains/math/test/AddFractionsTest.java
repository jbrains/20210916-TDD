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

    private static final class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            return new Fraction(this.numerator + that.numerator, this.denominator);
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator == that.numerator * this.denominator;
            }
            else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
