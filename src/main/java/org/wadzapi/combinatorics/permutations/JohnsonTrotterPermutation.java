package org.wadzapi.combinatorics.permutations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс генерации перестановок по алгоритму Джонсона-Троттера
 * Алгоритм описан в Липский "Комбинаторика для программистов" стр. 26
 *
 * @author RodionGork
 * @see <a href="https://javatalks.ru/topics/36077">JavaTalks исходный вариант</a>/>
 */
public class JohnsonTrotterPermutation implements Iterable<List<Integer>> {
    //TODO Логгирование
    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(JohnsonTrotterPermutation.class);

    /**
     * TODO Javadoc
     */
    private List<Integer> nums;

    /**
     * Массив стрелок алгоритма
     */
    private List<Boolean> arrows;

    /**
     * TODO Javadoc
     */
    private long count;

    /**
     * TODO Javadoc
     */
    private long cur;

    /**
     * Конструктор класса
     *
     * @param nums TODO Javadoc
     */
    public JohnsonTrotterPermutation(List<Integer> nums) {
        log.trace("{} инициализации класса генерации перестановок по Джонсону-Троттеру", "Начало");
        this.nums = nums;
        init(nums.size());
        log.trace("{} инициализации класса генерации перестановок по Джонсону-Троттеру", "Конец");
    }

    /**
     * TODO Javadoc
     *
     * @param n
     */
    private void init(int n) {
        log.debug("{} инициализации механизма перестновок по Джонсону-Троттеру", "");
        this.arrows = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            arrows.add(Boolean.FALSE);
        }

        count = 1;
        for (int i = 1; i <= n; i++) {
            count *= i;
        }
        cur = 0;
        log.debug("{} инициализации механизма перестновок по Джонсону-Троттеру", "");
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
    public Iterator<List<Integer>> iterator() {
        return new Iterator<List<Integer>>() {
            @Override
            public boolean hasNext() {
                return cur < count;
            }

            @Override
            public List<Integer> next() {
                generateNextStep();
                return nums;
            }
        };
    }

    //TODO Рефакторинг boolean на enum

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