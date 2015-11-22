package org.wadzapi.combinatorics.tree.dom;

/**
 * Класс ошибки при работе с DOM-деревом
 */
public class DomException extends Exception {

    /**
     * Конструктор класса
     * @param message сообщение об ошибке
     * @param cause причина ошибки
     */
    public DomException(String message, Throwable cause) {
        super(message, cause);
    }
}
