package org.wadzapi.combinatorics.math;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;

/**
 * Класс для работы с биномиальными коэффициентами
 * Используется для вычисления числа k-элементных подмножеств множества мощностью n
 */
public class BinomialCoefficientGenerator {

    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(BinomialCoefficientGenerator.class);

    /**
     * Метод вычисления биномиального коэффициента
     *
     * @param n Мощность исходного множества
     * @param k Мощность подмножества
     * @return число подможнеств из n по k (биномиальный коэффициент)
     * Реализация частично заимствована из Guava IntMath#binomial
     */
    private static int binomial(int n, int k) {
        log.trace("Генерация биномального коэффициента из {} по {}", n, k);
        if (n < 0 || k < 0 || n < k) {
            throw new IllegalArgumentException("Коэффициенты n и k должны быть неотрицательными и n >= k");
        }
        switch (k) {
            case 0:
                return 1;
            case 1:
                return n;
            default:
                long result = 1;
                for (int i = 0; i < k; i++) {
                    result *= n - i;
                    result /= i + 1;
                }
                return (int) result;
        }
    }

    /**
     * @param n Мощность исходного множества
     * @param k Мощность подмножества
     * @return биномиальный коэффициент
     */
    public BinomialCoefficient getBinomial(int n, int k) {
        return new BinomialCoefficient(n, k);
    }

    /**
     * Класс-контейнер биномиального коэффициента
     */
    public static final class BinomialCoefficient {

        /**
         * Шаблон для подстановки значений при преобразовании к строке
         */
        private static final String TO_STRING_PATTERN = "Биномиальный коэффициент из {0} по {1} равен {2}";

        /**
         * Мощность исходного множества
         */
        private final int n;

        /**
         * Мощность подмножества
         */
        private final int k;

        /**
         * Значение биномиального коэффициента
         */
        private final int value;

        /**
         * Конструктор класса
         *
         * @param n мощность исходного множества
         * @param k мощность подмножества
         */
        public BinomialCoefficient(int n, int k) {
            this.n = n;
            this.k = k;
            this.value = binomial(n, k);
        }

        public int getN() {
            return n;
        }

        public int getK() {
            return k;
        }

        /**
         * @return значение биномиального коэффициента
         */
        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return MessageFormat.format(TO_STRING_PATTERN, n, k, value);
        }
    }
}
