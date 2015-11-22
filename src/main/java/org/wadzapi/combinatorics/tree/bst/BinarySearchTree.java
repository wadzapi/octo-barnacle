package org.wadzapi.combinatorics.tree.bst;

import org.wadzapi.combinatorics.tree.Tree;

import java.util.Iterator;

/**
 * Структра бинарного дерева поиска
 * В основе реализации http://algs4.cs.princeton.edu/32bst/BST.java.html
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements Tree<Key, Value> {

    //TODO Логирование, javaDoc

    /**
     * Корневой узел дерева
     */
    private final BstNode<Key, Value> root;

    /**
     * Конструктор класса
     * @param root корневой узел
     */
    public BinarySearchTree(BstNode<Key, Value> root) {
        this.root = root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new NullPointerException("Ключ не может быть равен null");
        } else {
            BstNode<Key, Value> bstNode = null;
            BstSearchHandler<Key, Value> bstSearchHandler = new BstSearchHandler<>(root, key, false);
            Iterator<BstNode<Key, Value>> bstSearchIterator = bstSearchHandler.iterator();
            while (bstSearchIterator.hasNext()) {
                bstNode = bstSearchIterator.next();
            }
            return bstNode == null ? null : bstNode.getValue();
        }
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public void set(Key key, Value value) {
        if (key == null) {
            throw new NullPointerException("Ключ не может быть равен null");
        } else {
            BstNode<Key, Value> bstNode = (BstNode<Key, Value>) search(key, true);
            bstNode.setValue(value);
        }
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public Node<Key, Value> search(Key key) {
        return search(key, false);
    }

    /**
     * Метод поиска узла по значению ключа
     * @param key              ключ
     * @param createUnresolved признак создания ненайденного узла
     * @return узел
     */
    private Node<Key, Value> search(Key key, boolean createUnresolved) {
        BstNode<Key, Value> bstNode = null;
        BstSearchHandler<Key, Value> bstSearchHandler = new BstSearchHandler<>(root, key, createUnresolved);
        Iterator<BstNode<Key, Value>> bstSearchIterator = bstSearchHandler.iterator();
        while (bstSearchIterator.hasNext()) {
            bstNode = bstSearchIterator.next();
        }
        if (createUnresolved) {
            bstNode = bstSearchIterator.next();
        }
        return bstNode;
    }

    /**
     * Поиск максимального ключа в структуре
     * @return максимальный ключ в структуре
     */
    public Key max() {
        //TODO RightIterator?
        BstVisitor<Key, Value> bstVisitor = new BstVisitor<>(root);
        BstNode<Key, Value> bstNode = null;
        while (bstVisitor.goRight()) {
            bstNode = bstVisitor.getCurrentBstNode();
        }
        return bstNode == null ? null : bstNode.getKey();
    }

    /**
     * Поиск минимального ключа в структуре
     * @return минимальный ключ в структуре
     */
    public Key min() {
        //TODO LeftIterator?
        BstVisitor<Key, Value> bstVisitor = new BstVisitor<>(root);
        BstNode<Key, Value> bstNode = null;
        while (bstVisitor.goLeft()) {
            bstNode = bstVisitor.getCurrentBstNode();
        }
        return bstNode == null ? null : bstNode.getKey();
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public boolean delete(Key key) {
        BstNode<Key, Value> deleteNode = (BstNode<Key, Value>) search(key);
        //TODO Реализация метода удаления
        return deleteNode != null;
    }

    @Override
    public Node<Key, Value> getRoot() {
        return root;
    }
}
