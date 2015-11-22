package org.wadzapi.combinatorics.tree;

import java.util.Collection;

public interface Tree<Key extends Comparable<Key>, Value> {

    /**
     * Метод получения корня дерева
     * @return корень дерева
     */
    Node<Key, Value> getRoot();

    /**
     * Метод получения узла по ключу с заданным значением
     * @param key ключ
     * @return значение
     */
    Value get(Key key);

    /**
     * Метод добавления узла по ключу с заданным значением
     * @param key ключ
     */
    void set(Key key, Value value);

    /**
     * Метод поиска значения по ключу
     * @param key ключ
     * @return значение
     */
    Node<Key, Value> search(Key key);

    /**
     * Метод удаления узла по ключу
     * @param key ключ
     * @return {@code true} - если узел удален, иначе {@code false}
     */
    boolean delete(Key key);

    /**
     * Класс структуры, представляющей узел бинарного дерева
     */
    interface Node<Key, Value> {

        /**
         * @return ключ
         */
        Key getKey();

        /**
         * @return значение
         */
        Value getValue();

        /**
         * @param value значение
         */
        void setValue(Value value);

        //TODO Разобраться с рекурсивностью объявления структуры дерева, т.к. коллекция по сути дерево?
        Collection<Node> getLinkedNodes();

        /**
         * Класс билдера для построения деревьев с использованием структуры {@link Node}
         */
        interface NodeBuilder<Key> {

            /**
             * @param key ключ
             */
            NodeBuilder setKey(Key key);

            /**
             * Метод построения узла
             * @return узел
             */
            Node build();
        }
    }
}