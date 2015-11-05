package org.wadzapi.combinatorics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс генерации перестановок по алгоритму Джонсона-Троттера
 * Алгоритм описан в Липский "Комбинаторика для программистов"
 * @author RodionGork
 * @see <a href="https://javatalks.ru/topics/36077">JavaTalks исходный вариант</a>/>
 */
public class JohnsonTrotterPermutation implements Iterable<Integer> {

    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(JohnsonTrotterPermutation.class);


    private List<Integer> nums;

    /**
     * Массив стрелок алгоритма
     */
    private List<Boolean> arrows;

    /**
     *
     */
    private long count;

    /**
     *
     */
    private long cur;


    public JohnsonTrotterPermutation(List<Integer> nums) {
        this.nums = nums;
        init(nums.size());

    }

    /**
     * @param n Мощность начального множества
     */
    public JohnsonTrotterPermutation(int n) {
        /**
         * TODO Удалить stub
         */
        nums = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        init(nums.size());
    }

    private void init(int n) {
        this.arrows = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            arrows.add(Boolean.FALSE);
        }

        count = 1;
        for (int i = 1; i <= n; i++) {
            count *= i;
        }
        cur = 0;
    }

    /**
     * Метод генерации перестановки для следующего шага
     */
    private void generateNextStep() {
        int biggest = 0;
        int pos = -1;
        cur++;
        int n = nums.size();
        for (int i = 1; i < n; i++) {
            if (!arrows.get(i)) {
                if (nums.get(i) > nums.get(i - 1) && nums.get(i) > biggest) {
                    pos = i;
                    biggest = nums.get(pos);
                }
            }
            if (arrows.get(i - 1)) {
                if (nums.get(i) < nums.get(i - 1) && nums.get(i - 1) > biggest) {
                    pos = i - 1;
                    biggest = nums.get(pos);
                }
            }
        }

        if (pos < 0) {
            return;
        }

        int nei = pos + (arrows.get(pos) ? 1 : -1);
        nums.set(pos, nums.get(nei));
        nums.set(nei, biggest);
        boolean t = arrows.get(pos);
        arrows.set(pos, arrows.get(nei));
        arrows.set(nei, t);

        for (int i = 0; i < n; i++) {
            if (nums.get(i) > biggest) {
                arrows.set(i, !arrows.get(i));
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return cur < count;
            }

            @Override
            public Integer next() {
                generateNextStep();
                String elemString = nums.stream().map(digit -> digit.toString()).collect(Collectors.joining());
                return Integer.valueOf(elemString);
            }
        };
    }

    /**
     * TODO Javadoc
     */
    private enum ArrowDirection {
        /**
         * TODO Javadoc
         */
        LEFT,
        /**
         * TODO Javadoc
         */
        RIGHT
    }
}
