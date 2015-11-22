package org.wadzapi.combinatorics.tree.bst;

import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO Посмотреть реализации паттерна visitor
//TODO Подумать как прикрутить к общему интерфейсу дерева

/**
 * Класс, релизующий итератор поиска по бинарному дереву
 * @param <Key>   тип ключа
 * @param <Value> тип значения
 */
public class BstSearchHandler<Key extends Comparable<Key>, Value> extends BstVisitor implements Iterable<BstNode<Key, Value>> {

    //TODO Логирование, javaDoc

    /**
     * Ключ поиска
     */
    private final Key searchKey;

    /**
     * Признак создания элемента на месте ненайденного
     */
    private boolean createUnresolved;

    /**
     * Последние из направлений обхода
     */
    private TraverseDirection traverseDirection;

    /**
     * Конструктор класса
     * @param bstNode          начальный узел поиска
     * @param searchKey        ключ поиска
     * @param createUnresolved признак создания элемента на месте ненайденного
     */
    public BstSearchHandler(BstNode<Key, Value> bstNode, Key searchKey, boolean createUnresolved) {
        super(bstNode);
        this.searchKey = searchKey;
        this.createUnresolved = createUnresolved;
    }

    /**
     * Метод для вычисления следующего элемента
     * @return {@code true} - если элемент найден, иначе {@code false}
     */
    private BstNode<Key, Value> resolveNext() {
        if (currentBstNode == null) {
            traverseDirection = TraverseDirection.NOT_FOUND;
            return null;
        } else {
            int compareTo = currentBstNode.getKey().compareTo(searchKey);
            if (compareTo < 0) {
                traverseDirection = TraverseDirection.LEFT;
                return currentBstNode.getLeft();
            } else if (compareTo > 0) {
                traverseDirection = TraverseDirection.RIGHT;
                return currentBstNode.getRight();
            } else {
                traverseDirection = TraverseDirection.FOUND;
                return currentBstNode;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<BstNode<Key, Value>> iterator() {
        return new Iterator<BstNode<Key, Value>>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return (traverseDirection == TraverseDirection.FOUND) || (traverseDirection == TraverseDirection.NOT_FOUND);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public BstNode<Key, Value> next() {
                BstNode<Key, Value> nextNode = resolveNext();
                if (nextNode != null) {
                    currentBstNode = nextNode;
                    return currentBstNode;
                } else if (createUnresolved) {
                    BstNode<Key, Value> newNode = new BstNode.NodeBuilder<>().setKey(searchKey).build();
                    switch (traverseDirection) {
                        case LEFT:
                            currentBstNode.setLeft(newNode);
                            break;
                        case RIGHT:
                            currentBstNode.setRight(newNode);
                            break;
                        default:
                            throw new IllegalArgumentException("Передан некорректный тип обхода дерева " + traverseDirection);
                    }
                    return newNode;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Направление обхода
     */
    private enum TraverseDirection {
        /**
         * Левое поддерево
         */
        LEFT,
        /**
         * Правое поддерево
         */
        RIGHT,
        /**
         * Искомый узел найден
         */
        FOUND,
        /**
         * Искомый узел не найден
         */
        NOT_FOUND;
    }
}