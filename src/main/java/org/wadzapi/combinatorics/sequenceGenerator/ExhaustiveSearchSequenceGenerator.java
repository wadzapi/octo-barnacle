package org.wadzapi.combinatorics.sequenceGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Реалзиация глупого алгоритма перебора с фильтрацией по условия с использованием предиката
 **/
public class ExhaustiveSearchSequenceGenerator {
    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(ExhaustiveSearchSequenceGenerator.class);

    /**
     * Минимальное значение перебора
     */
    private static final int DEFAULT_MIN = 0;

    /**
     * Максимальное значение перебора
     */
    private static final int DEFAULT_MAX = Integer.MAX_VALUE;

    /**
     * Метод перебора и вывода фильтрованных значений
     */
    public void generateSequence() {
        generateSequence(DEFAULT_MIN, DEFAULT_MAX);
    }

    /**
     * Метод перебора и вывода фильтрованных значений
     * TODO убрать if внутрь предиката
     * @param min минимальное значение перебора
     * @param max максимальное значение перебора
     */
    private void generateSequence(int min, int max) {
        for (AtomicInteger counter = new AtomicInteger(min); counter.get() <= max; counter.getAndIncrement()) {
            int currVal = counter.get();
            String digitString = String.valueOf(currVal);
            List<Character> charList = digitString.chars().mapToObj(digitChar -> Character.valueOf((char) digitChar)).collect(Collectors.toList());
            Set<Character> charSet = new HashSet<>(charList);
            if (charSet.size() == charList.size()) {
                System.out.println(digitString);
                log.debug(digitString);
            }
        }
    }
}
