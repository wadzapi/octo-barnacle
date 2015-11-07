package org.wadzapi.combinatorics.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Юнит-тесты методов класса {@link org.wadzapi.combinatorics.math.BinomialCoefficientGenerator}
 * При использовании необходимо учитывать макс. значение {@link Integer#MAX_VALUE}
 */
public class BinomialCoefficientGeneratorTest {

    /**
     * Таблица биномальных коэффициентов для сверки полученных значений
     */
    private static final int[][] BINOMIAL_COEFFICIENTS_REFERENCE_TABLE = new int[][]{
            {1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}, {1, 5, 10, 10, 5, 1}, {1, 6, 15, 20, 15, 6, 1},
            {1, 7, 21, 35, 35, 21, 7, 1}, {1, 8, 28, 56, 70, 56, 28, 8, 1}, {1, 9, 36, 84, 126, 126, 84, 36, 9, 1}
    };

    /**
     * Значение мощности множества для тестирования генератора биномиальных коэффицентов на основе св-в
     */
    private static final int N_BINOM_PROPERTIES = 30;

    /**
     * Генератор биномиальных коэффициентов
     */
    private BinomialCoefficientGenerator binomialCoefficientGenerator;

    /**
     * Таблица значений биномиальных коэффициентов
     */

    @Before
    public void setUp() {
        binomialCoefficientGenerator = new BinomialCoefficientGenerator();
    }

    /**
     * Юнит-тесты метода класса {@link org.wadzapi.combinatorics.math.BinomialCoefficientGenerator#getBinomial(int, int)}
     */
    @Test
    public void testGetBinomial() {
        testBinomialTableValues();
        testBinomialProperties(N_BINOM_PROPERTIES);
    }

    /**
     * Метод генерации значений биномиальных коэффициентов c заполнением таблицы значений и сверкой с табличными значениями
     */
    private void testBinomialTableValues() {
        int maxN = BINOMIAL_COEFFICIENTS_REFERENCE_TABLE.length;
        BinomialCoefficientGenerator.BinomialCoefficient[][] pascalTriangleTable = new BinomialCoefficientGenerator.BinomialCoefficient[maxN][];
        for (int n = 0; n < maxN; n++) {
            pascalTriangleTable[n] = new BinomialCoefficientGenerator.BinomialCoefficient[n + 1];
            for (int k = 0; k <= n; k++) {
                pascalTriangleTable[n][k] = binomialCoefficientGenerator.getBinomial(n, k);
                Assert.assertEquals(BINOMIAL_COEFFICIENTS_REFERENCE_TABLE[n][k], pascalTriangleTable[n][k].getValue());
            }
        }
        //Arrays.stream(pascalTriangleTable).flatMap(Stream::of).forEach(System.out::println);
    }

    /**
     * Метод тестирования значений биномальных коэффициентов, согласно свойствам<br/>
     * для достаточно больших значения n и k
     * Подробнее см. <a href="http://e-maxx.ru/algo/binomial_coeff">Свойства биномиальных коэффициентов</a>
     */
    private void testBinomialProperties(int n) {
        int dk = n / 4;
        BinomialCoefficientGenerator.BinomialCoefficient binomialCoefficient = binomialCoefficientGenerator.getBinomial(n, dk);
        Assert.assertEquals(binomialCoefficient.getValue(), binomialCoefficientGenerator.getBinomial(n, n - dk).getValue());
        //TODO Реализовать проверку, согласно оставшимся св-вам биномиальных коэффициентов
    }
}