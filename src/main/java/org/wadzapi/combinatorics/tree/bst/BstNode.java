package org.wadzapi.combinatorics.tree.bst;

import org.wadzapi.combinatorics.tree.Tree.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс структуры, представляющей узел бинарного дерева
 */
public class BstNode<Key extends Comparable<Key>, Value> implements Node<Key, Value> {

    //TODO Логирование, javaDoc
    /**
     * Размер коллекции связанных узлов
     */
    private static final int LINKED_NODES_SIZE = 2;
    /**
     * Ключ
     */
    private final Key key;
    /**
     * Значение
     */
    private Value value;

    /**
     * Информация о связях узла
     */
    private List<BstNode> bstNodeLinks;

    /**
     * Конструктор класса
     * @param nodeBuilder билдер узлов дерева
     */
    public BstNode(NodeBuilder<Key> nodeBuilder) {
        if (nodeBuilder.key == null) {
            throw new NullPointerException("Ключ не может быть равен null");
        }
        this.key = nodeBuilder.key;
        this.bstNodeLinks = new ArrayList<>(LINKED_NODES_SIZE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Key getKey() {
        return key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Value getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Node> getLinkedNodes() {
        return (Collection) bstNodeLinks;
    }

    /**
     * Метод получения левого узла
     * @return левый узел
     */
    public BstNode<Key, Value> getLeft() {
        return bstNodeLinks.get(0);
    }

    /**
     * Метод установки левого узла
     * @param node левый узел
     */
    public void setLeft(BstNode<Key, Value> node) {
        bstNodeLinks.set(0, node);
    }

    /**
     * Метод получения правого узла
     * @return правый узел
     */
    public BstNode<Key, Value> getRight() {
        return bstNodeLinks.get(1);
    }

    /**
     * Метод установки правого узла
     * @param node правого узел
     */
    public void setRight(BstNode<Key, Value> node) {
        bstNodeLinks.set(1, node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Класс билдера для построения деревьев с использованием структуры {@link BstNode}
     */
    public static final class NodeBuilder<Key> implements Node.NodeBuilder<Key> {

        /**
         * Ключ
         */
        private Key key;

        /**
         * {@inheritDoc}
         */
        @Override
        public NodeBuilder setKey(Key key) {
            this.key = key;
            return this;
        }

        /**
         * Метод построения узла
         * @return узел
         */
        public BstNode build() {

            return new BstNode(this);
        }
    }
}