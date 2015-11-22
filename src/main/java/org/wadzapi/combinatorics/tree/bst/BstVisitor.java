package org.wadzapi.combinatorics.tree.bst;

//TODO Посмотреть реализации паттерна visitor
//TODO Подумать как прикрутить к общему интерфейсу дерева

/**
 * Класс для обхода структуры бинарного дерева
 * @param <Key>   тип ключа
 * @param <Value> тип значения
 */
public class BstVisitor<Key extends Comparable<Key>, Value> {

    //TODO Логирование, javaDoc

    /**
     * Текущий узел посещения
     */
    protected BstNode<Key, Value> currentBstNode;

    /**
     * Констркуктор класса
     * @param BstNode начальный узел поиска
     */
    public BstVisitor(BstNode<Key, Value> BstNode) {
        this.currentBstNode = BstNode;
    }

    /**
     * Метод перехода на левый узел
     * @return {@code true} - если узел существует, иначе {@code false}
     */
    public boolean goLeft() {
        if (currentBstNode != null) {
            currentBstNode = currentBstNode.getLeft();
            return true;
        }
        return false;
    }

    /**
     * Метод перехода на правый узел
     * @return {@code true} - если узел существует, иначе {@code false}
     */
    public boolean goRight() {
        if (currentBstNode != null) {
            currentBstNode = currentBstNode.getRight();
            return true;
        }
        return false;
    }

    /**
     * Метод получения текущего узла
     * @return текущий узел поиска
     */
    public BstNode<Key, Value> getCurrentBstNode() {
        return currentBstNode;
    }
}