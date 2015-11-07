package org.wadzapi.combinatorics.math;

/**
 * Класс для вычисления факториала
 */
public class Factorial {

    /**
     * Табличные значения факториала
     */
    private static final long[] FACTORIAL_TABLE = new long[]{1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L, 39916800L,
            479001600L, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L,
            121645100408832000L, 2432902008176640000L};

    /**
     * Метод вычисления факториала
     *
     * @param n аргумент факторила
     * @return факториал от n
     */
    public static long factorial(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("Значение должно быть неотрицательным числом меньше 21");
        }
        return FACTORIAL_TABLE[n];
    }
}
