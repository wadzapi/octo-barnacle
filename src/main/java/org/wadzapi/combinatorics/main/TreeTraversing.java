package org.wadzapi.combinatorics.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Основной класс для запуска программы работы и изучения алгоритмов над деревьями
 */
public class TreeTraversing {

    /**
     * Логгер
     */
    private static final Logger LOGGER = LogManager.getLogger(TreeTraversing.class);

    /**
     * Основной класс для запуска программы работы с деревьями
     * @param args аргументы коммандной строки
     */
    public static void main(String[] args) {
        final String logMsg = "{} выполнения программы работы с древовидными структурами";
        LOGGER.info(logMsg, "Начало");
        LOGGER.info(logMsg, "Конец");
    }
}
