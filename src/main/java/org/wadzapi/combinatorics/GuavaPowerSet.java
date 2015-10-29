package org.wadzapi.combinatorics;

import java.util.*;

/**
 * Имлементация метода получения powerSet из guava
 * @see <a href="https://code.google.com/r/baggiogamp-guava/source/browse/guava/src/com/google/common/collect/Sets.java">Исходный код</a>
 * TODO Подумать над дальнейшей реализацией
 */
public class GuavaPowerSet {


    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        Set<E> input = Collections.unmodifiableSet(set);
        return new PowerSet<E>(input);
    }

    private static final class PowerSet<E> extends AbstractSet<Set<E>> {
        final Set<E> inputSet;
        final List<E> inputList;
        final int powerSetSize;

        PowerSet(Set<E> input) {
            this.inputSet = input;
            this.inputList = new ArrayList<>(input);
            this.powerSetSize = 1 << input.size();
        }

        @Override
        public int size() {
            return powerSetSize;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Iterator<Set<E>> iterator() {
            return new Iterator<Set<E>>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Set<E> next() {
                    return null;
                }
            };
        }
    }
}
