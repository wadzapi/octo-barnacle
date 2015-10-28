package org.wadzapi.combinatorics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * Основной класс для вывода чисел с неповторяющимися символами
 **/
public class WadzapiAppDaddy {

    /**
     * Логгер
     */
    private static final Logger log = LogManager.getLogger(WadzapiAppDaddy.class);


    //private static final int DEFAULT_MIN = Integer.MIN_VALUE;
    private static final int DEFAULT_MIN = 0;


    private static final int DEFAULT_MAX = Integer.MAX_VALUE;

    /**
     * Конструктор класса
     **/
    private WadzapiAppDaddy() {
        log.debug("Test App 4 AppDaddy! Hey-hey-hey");
    }

    public static void printNumbers() {
        printNumbers(DEFAULT_MIN, DEFAULT_MAX);
    }

    public static void printNumbers(int min, int max) {
        for (AtomicInteger counter = new AtomicInteger(min); counter.get() <= max; counter.getAndIncrement()) {
            int currVal = counter.get();
            String digitString = String.valueOf(currVal);
            List<Character> charList = digitString.chars().mapToObj(digitChar -> Character.valueOf((char) digitChar)).collect(Collectors.toList());
            Set<Character> charSet = new HashSet<>(charList);
            //log.debug("listLen: " + charList.size());
            //log.debug("setLen: " + charSet.size());
            //log.debug("-------");
            if (charSet.size() == charList.size()) {
                System.out.println(digitString);
                log.debug(digitString);
            }

        }
    }

    public static void main(String[] args) {
        log.debug("Вызов метода main для класса WadZapiAppDaddy");
        //printNumbers(98, 103);
        long currTime = System.nanoTime();
        printNumbers();
        long elapsed = System.nanoTime() - currTime;
        System.out.println("elapsed : " + elapsed + " ms");
        log.debug("elapsed : " + elapsed + " ms");
    }

}
