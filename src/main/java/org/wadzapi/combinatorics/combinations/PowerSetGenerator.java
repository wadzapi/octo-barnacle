package org.wadzapi.combinatorics.combinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс-генератор множества всех подмножеств
 */
public class PowerSetGenerator implements Iterable<Set<Integer>> {

    //TODO Логгирование
    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(PowerSetGenerator.class);

    /**
     * Множество всех подмножеств
     */
    private final Set<Set<Integer>> powerSet;

    /**
     * Конструктор класса
     */
    public PowerSetGenerator(Set<Integer> elements) {
        this.powerSet = Collections.unmodifiableSet(generatePowerSet(elements));
    }

    /**
     * генерация powerset
     * Код с stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java
     *
     * @param originalSet TODO Javadoc
     * @param <T>
     * @return
     * @author João Silva
     */
    private static <T> Set<Set<T>> generatePowerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<>());
            return sets;
        }
        List<T> list = new ArrayList<>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<>(list.subList(1, list.size()));
        for (Set<T> set : generatePowerSet(rest)) {
            Set<T> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(Collections.unmodifiableSet(newSet));
            sets.add(Collections.unmodifiableSet(set));
        }
        return sets;
    }

    /**
     * @return множество всех подмножеств
     */
    public Set<Set<Integer>> getPowerSet() {
        return powerSet;
    }

    /**
     * Метод получения отфильтрованного по мощности множества всех подмножеств
     *
     * @param n мощность подмножеств
     * @return подмножества мощности n
     */
    public Set<Set<Integer>> getSubset(final int n) {
        log.debug("{} фильтрации подмножеств мощности {}", "Начало", n);
        Set<Set<Integer>> subset = powerSet.stream().filter(subSet -> subSet.size() == n).collect(Collectors.toSet());
        log.debug("{} фильтрации подмножеств мощности {}. Результат: {}", "Конец", n, subset);
        return subset;
    }

    @Override
    public Iterator<Set<Integer>> iterator() {
        return powerSet.iterator();
    }
}
