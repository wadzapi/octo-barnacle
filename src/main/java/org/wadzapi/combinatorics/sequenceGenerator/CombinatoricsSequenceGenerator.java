package org.wadzapi.combinatorics.sequenceGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wadzapi.combinatorics.combinations.PowerSetGenerator;
import org.wadzapi.combinatorics.permutations.JohnsonTrotterPermutation;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс генерации последовательности чисел без повтора цифр
 */
public class CombinatoricsSequenceGenerator {

    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(CombinatoricsSequenceGenerator.class);

    /**
     * Минимальное значение
     */
    private static final int MIN_VALUE = 0;

    /**
     * Максимальное значение
     */
    private static final int MAX_VALUE = 9;

    /**
     * Начальный набор символов
     */
    private static final Set<Integer> DIGITS_SET = new TreeSet<Integer>() {{
        for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
            add(i);
        }
    }};

    /**
     * Метод генерации последовательности
     */
    public void generateSequence() {
        PowerSetGenerator powerSetGenerator = new PowerSetGenerator(DIGITS_SET);
        Set<Set<Integer>> powerSet = powerSetGenerator.getPowerSet();
        for (Set<Integer> combination : powerSet) {
            log.info("{} перестанок последовательности: {}", "Начало", combination);
            if (combination.size() > 0) {
                for (Integer nextPermutation : new JohnsonTrotterPermutation(new ArrayList<>(combination))) {
                    log.info(String.valueOf(nextPermutation));
                }
            }
            log.info("{} перестанок последовательности: {}", "Конец", combination);
        }
    }
}
