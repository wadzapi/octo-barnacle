package org.wadzapi.combinatorics;

import java.util.*;

/**
 * Класс-генератор сочетаний из n по k
 **/
public class CombinationGenerator implements Enumeration<Integer> {

    private final Set<Set<Integer>> powerSet;

    private static final int MIN_VALUE = 0;

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
     * Длина комбинации
     */
    private int combinationLength;

    /**
     * Конструктор класса
     */
    public CombinationGenerator() {
        this.powerSet = generatePowerSet(DIGITS_SET);
    }

    @Override
    public boolean hasMoreElements() {
        //TODO Реализация
        return false;
    }

    @Override
    public Integer nextElement() {
        //TODO Реализация
        return null;
    }

    /**
     * генерация powerset
     * Код с stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java
     * @author João Silva
     * @param originalSet
     * @param <T>
     * @return
     */
    private static final <T> Set<Set<T>> generatePowerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : generatePowerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    public Set<Set<Integer>> getPowerSet() {
        return powerSet;
    }

    public int getCombinationLength() {
        return combinationLength;
    }

    public void setCombinationLength(int combinationLength) {
        this.combinationLength = combinationLength;
    }
}
