package org.wadzapi.combinatorics.sequenceGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Основсной класс для запуска программы генерации последовательностей чисел, в которых не встречаются повторы
 * и замера производительности различных алгоритмов их реализующих
 */
public class SequenceBenchmarking {

    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(SequenceBenchmarking.class);

    /**
     * Основной метод генерации последовательности с заданными из консоли параметрами
     * @param args параметры генерации последовательности чисел без повтора
     */
    public static void main(String... args) {
        //TODO Реализовать нормальный стенд замера производительности алгоритмов
        benchmarkCombinatoricsGenerator();
        benchmarkExhaustiveGenerator();
    }

    /**
     * Метод замера производительности для алгоритма переборной генерации
     */
    private static void benchmarkExhaustiveGenerator() {
        log.info("Начало переборной генерации последовательности");
        long currTime = System.nanoTime();
        ExhaustiveSearchSequenceGenerator exhaustiveSearchSequenceGenerator = new ExhaustiveSearchSequenceGenerator();
        exhaustiveSearchSequenceGenerator.generateSequence();
        long elapsed = System.nanoTime() - currTime;
        System.out.println("ExhaustiveGenerator elapsed : " + elapsed + " ms");
        log.info("Конец переборной генерации последовательности. Затрачено: {} мс", elapsed);
    }

    /**
     * Метод замера производительности для алгоритма комбинаторной генерации
     */
    private static void benchmarkCombinatoricsGenerator() {
        log.info("Начало комбинаторной генерации последовательности");
        long currTime = System.nanoTime();
        CombinatoricsSequenceGenerator combinatoricsSequenceGenerator = new CombinatoricsSequenceGenerator();
        combinatoricsSequenceGenerator.generateSequence();
        long elapsed = System.nanoTime() - currTime;
        System.out.println("CombinatoricsGenerator elapsed : " + elapsed + " ms");
        log.info("Конец комбинаторной генерации последовательности. Затрачено: {} мс", elapsed);
    }
}
