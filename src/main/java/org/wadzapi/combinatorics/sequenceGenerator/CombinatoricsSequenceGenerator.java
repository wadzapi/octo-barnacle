package org.wadzapi.combinatorics.sequenceGenerator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wadzapi.combinatorics.CombinationGenerator;
import org.wadzapi.combinatorics.JohnsonTrotterPermutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс генерации последовательности чисел без повтора и замера затраченного на процедуру времени
 */
public class CombinatoricsSequenceGenerator {

    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(CombinatoricsSequenceGenerator.class);

    public void generateSequence() {
        log.debug("Вызов метода main для класса Permutator");
        int maxDigits = Integer.MAX_VALUE / 10;
        log.info("Начало перестановок для " + maxDigits + " элементов");
        CombinationGenerator combinationGenerator = new CombinationGenerator();
        Set<Set<Integer>> powerSet = combinationGenerator.getPowerSet();
        //TODO Заменить геттер powerSet на проход по Enumeration
        for (int n = 1; n < maxDigits; n++) {
            final int finalN = n;
            log.debug("Subset for n=" + n + " ; finalN= " + finalN);
            Set<Set<Integer>> subset = powerSet.stream().filter(subSet -> subSet.size() == finalN).collect(Collectors.toSet());
            log.debug("subset: " + subset);
            log.info("Начало перестановки из " + n + " элементов");
            for (Set<Integer> nextValue : subset) {
                List<Integer> subSetList = new ArrayList<>(nextValue);
                for (Integer nextPermutation : new JohnsonTrotterPermutation(subSetList)) {
                    log.debug(String.valueOf(nextPermutation));
                    System.out.println(String.valueOf(nextPermutation));
                }
            }
        }
    }
}
