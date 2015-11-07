package org.wadzapi.combinatorics.permutations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wadzapi.combinatorics.math.Factorial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Юнит-тесты методов класса {@link org.wadzapi.combinatorics.permutations.JohnsonTrotterPermutation}
 */
public class JohnsonTrotterPermutationTest {

    /**
     * Класс-генератор перестановок
     */
    private JohnsonTrotterPermutation johnsonTrotterPermutation;

    private static final int ELEMENTS_NUM = 10;

    /**
     * Исходное множество элементов
     */
    private static final List<Integer> ELEMENTS;

    static {
        ELEMENTS = new ArrayList<>(ELEMENTS_NUM);
        for (int i = 0; i < ELEMENTS_NUM; i++) {
            ELEMENTS.add(i);
        }
    }

    @Before
    public void setUp() {
        johnsonTrotterPermutation = new JohnsonTrotterPermutation(ELEMENTS);
    }

    /**
     * Тест метода {@link org.wadzapi.combinatorics.permutations.JohnsonTrotterPermutation#iterator()}
     */
    @Test
    public void testIterator() {
        Iterator<List<Integer>> permutationIterator = johnsonTrotterPermutation.iterator();
        int permutationCounter = 0;
        while (permutationIterator.hasNext()) {
            List<Integer> nums = permutationIterator.next();
            Assert.assertEquals(nums.size(), new HashSet<>(nums).size());
            //TODO Добавить assertThat для проверки на содержание элементов исходного множества
            String numsString = nums.stream().map(digit -> digit.toString()).collect(Collectors.joining());
            //System.out.println(numsString);
            ++permutationCounter;
        }
        Assert.assertEquals(Factorial.factorial(ELEMENTS_NUM), permutationCounter);
    }
}