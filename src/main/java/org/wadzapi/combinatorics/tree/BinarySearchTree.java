package org.wadzapi.combinatorics.tree;

/**
 * Структра бинарного дерева поиска
 * В основе реализации http://algs4.cs.princeton.edu/32bst/BST.java.html
 */
public class BinarySearchTree {

    /**
     * Корневой узел дерева
     */
    private final Node root;

    /**
     * Конструктор класса
     * @param root корневой узел
     */
    public BinarySearchTree(Node root) {
        this.root = root;
    }

    /**
     * Метод получения значения по ключу
     * @param key ключ
     * @return значение узла
     */
    public Object get(String key) {
        return get(key, root);
    }

    /**
     * Метод поиска узла по ключу с получением хранимого значения
     * @param key искомый ключ
     * @param root корневой узел поиска
     * @return значение в искомом узле
     */
    private static Object get(String key, Node root) {
        if (root == null) {
            return null;
        } else if (key.compareTo(root.key) < 0){
            return get(key, root.left);
        } else if (key.compareTo(root.key) > 0) {
            return get(key, root.right);
        } else {
            return root.value;
        }
    }

    /**
     * Метод записи значения в узел по ключу
     * @param key ключ
     * @param value значение
     */
    public void put(String key, Object value) {
        if (key == null) {
            throw new NullPointerException("Ключ не может быть равен null");
        } else {
            put(key, value, root);
        }
    }

    /**
     * Метод записи значения в узел по ключу
     * @param key ключ
     * @param value значение
     * @param root узел
     * @return узел куда было положено значение
     */
    private static Node put(String key, Object value, Node root) {
        if (root == null) {
            return new Node(key, value);
        } else {
            int compare = key.compareTo(root.key);
            if (compare < 0) {
                root.left = put(key, value, root.left);
            } else if (compare > 0) {
                root.right = put(key, value, root.right);
            } else {
                root.value = value;
            }
            return root;
        }
    }

    /**
     * Поиск максимального ключа в структуре
     * @return максимальный ключ в структуре
     */
    public String max() {
        return right(root).key;
    }

    /**
     * Метод поиска крайнего правого узла дерева
     * @param node узел начала поиска
     * @return крайний правый узел структуры
     */
    private static Node right(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return right(node.right);
        }
    }

    /**
     * Поиск минимального ключа в структуре
     * @return минимальный ключ в структуре
     */
    public String min() {
        return left(root).key;
    }

    /**
     * Метод поиска крайнего левого узла
     * @param node узел начала поиска
     * @return найденный узел
     */
    private static Node left(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return left(node.left);
        }
    }

    /**
     * Метод удаления узла по ключу
     * @param key ключ
     * @return {@code true} - если узел удален, иначе {@code false}
     */
    public boolean delete(String key) {
        return delete(key, root) != null;
    }

    //TODO Выделить поиск узла в отдельный метод

    /**
     * Метод удаления узла
     * @param key ключ
     * @param node узел начала поиска
     * @return удаленный узел, если узел найден, иначе {@code null}
     */
    private static Node delete(String key, Node node) {
        //TODO Реализаци метода
        return null;
    }

    /**
     * Класс структуры, представляющей узел бинарного дерева
     */
    private static final class Node {
        /**
         * Ключ
         */
        private final String key;
        /**
         * Значение
         */
        private Object value;
        /**
         * Левый дочерний узел
         */
        private Node left;
        /**
         * Правый дочерний узел
         */
        private Node right;

        /**
         * Конструктор класса
         * @param key ключ
         * @param value значение
         */
        private Node(String key, Object value) {
            if (key == null) {
                throw new NullPointerException("Ключ не может быть равен null");
            }
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Класс билдера для построения деревьев с использованием структуры {@link org.wadzapi.combinatorics.tree.BinarySearchTree.Node}
     */
    private static final class BSTBuilder {

        private Node root;

        private Node current;


        public BinarySearchTree buid() {
            return new BinarySearchTree(root);
        }

        public Node getLeft() {
            return root.left;
        }

        public void addLeft() {

        }

        public Node getRight() {
            return root.right;
        }

        public void addRight() {

        }

        public Node getRoot() {
            return root;
        }

        public Node getCurrent() {
            return current;
        }

    }
}
