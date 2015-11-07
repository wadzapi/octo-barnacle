package org.wadzapi.combinatorics.math;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Юнит-тесты для класса {@link Factorial}
 */
public class FactorialTest {

    /**
     * Минимально допустмое значение агрумента
     */
    private static final int MIN_N = 0;

    /**
     * Максимально допустмое значение агрумента
     */
    private static final int MAX_N = 20;

    /**
     * Тест для метода {@link Factorial#factorial(int)}
     */
    @Test
    public void testFactorial() {
        try {
            Factorial.factorial(MIN_N - 1);
            Assert.fail();
        } catch (Exception ex) {
            assertThat(ex, instanceOf(IllegalArgumentException.class));
        }
        try {
            Factorial.factorial(MAX_N + 1);
            Assert.fail();
        } catch (Exception ex) {
            assertThat(ex, instanceOf(IllegalArgumentException.class));
        }
        assertEquals(1L, Factorial.factorial(MIN_N));
        long factorialValue = 1L;
        for (int i = MIN_N + 1; i < MAX_N + 1; i++) {
            factorialValue *= i;
            assertEquals(factorialValue, Factorial.factorial(i));
        }
    }
}