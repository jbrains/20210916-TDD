package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    @Test
    void zeroPlusZero() {
        Assertions.assertEquals(0, new Fraction(0).plus(new Fraction(0)).intValue());
    }

    @Test
    void notZeroPlusZero() {
        Assertions.assertEquals(4, new Fraction(4).plus(new Fraction(0)).intValue());
    }

    @Test
    void zeroPlusNotZero() {
        Assertions.assertEquals(7, new Fraction(0).plus(new Fraction(7)).intValue());
    }

    private static class Fraction {
        private int integerValue;

        public Fraction(int integerValue) {
            this.integerValue = integerValue;
        }

        public Fraction plus(Fraction that) {
            if (that.integerValue == 0)
                return this;
            else
                return that;
        }

        public int intValue() {
            return integerValue;
        }
    }
}
