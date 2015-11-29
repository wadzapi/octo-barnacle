package org.wadzapi.combinatorics.tree.bst;

import org.junit.Before;
import org.junit.Test;
import org.wadzapi.combinatorics.tree.Tree;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;


/**
 * Юнит-тесты для класса {@link org.wadzapi.combinatorics.tree.bst.BstNode}
 */
public class BstNodeTest {

/*    *//**
     * Счетчик для последовательности значений ключей
     *//*
    private static final AtomicInteger COUNTER = new AtomicInteger();*/

    /**
     * Билдер для построения узлов
     */
    private BstNode.NodeBuilder<Integer> nodeBuilder;

    /**
     * Тестовый ключ
     */
    private static final Integer TEST_KEY = 11;

    @Before
    public void setUp() {
        nodeBuilder = new BstNode.NodeBuilder<>();
    }

    /**
     * Юнит-тест для метода {@link BstNode#getKey()}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetKey() throws Exception {
        try {
            Tree.Node<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            assertEquals(TEST_KEY, testNode.getKey());
        } catch (Exception e) {
            fail();
        }
        try {
            Tree.Node<Integer, Integer> testNode = nodeBuilder.setKey(TEST_KEY).build();
            assertEquals(TEST_KEY, testNode.getKey());
        } catch (Exception e) {
            fail();
        }
        try {
            Tree.Node<Integer, BigInteger> testNode = nodeBuilder.setKey(TEST_KEY).build();
            assertEquals(TEST_KEY, testNode.getKey());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Юнит-тест для метода {@link BstNode#getValue()}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetValue() throws Exception {
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            assertEquals(null, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            String testString = String.valueOf(111);
            testNode.setValue(testString);
            assertEquals(testString, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
        try {
            BstNode<Integer, Integer> testNode = nodeBuilder.setKey(TEST_KEY).build();
            Integer testIntValue = 11;
            testNode.setValue(testIntValue);
            assertEquals(testIntValue, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
        try {
            BstNode<Integer, BigInteger> testNode = nodeBuilder.setKey(TEST_KEY).build();
            BigInteger testBigIntValue = BigInteger.valueOf(111);
            testNode.setValue(testBigIntValue);
            assertEquals(testBigIntValue, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Юнит-тест для метода {@link BstNode#getLinkedNodes()}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetLinkedNodes() throws Exception {
        BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
        try {
            //без ссылок на левое и правое поддерево
            Collection<Tree.Node> linkedNodes = testNode.getLinkedNodes();
            Iterator<Tree.Node> iterator = linkedNodes.iterator();
            assertTrue(iterator.hasNext());
            int linkedCounter = 0;
            while (iterator.hasNext()) {
                Tree.Node nextNode = iterator.next();
                assertTrue(nextNode == null);
                ++linkedCounter;
            }
            assertEquals(2, linkedCounter);
        } catch (Exception e) {
            fail();
        }
        try {
            //со ссылками на левое и правое поддерево
            Integer leftKey = 12;
            BstNode<Integer, String> referenceLeft = nodeBuilder.setKey(leftKey).build();
            Integer rightKey = 13;
            BstNode<Integer, String> referenceRight = nodeBuilder.setKey(rightKey).build();
            testNode.setLeft(referenceLeft);
            testNode.setRight(referenceRight);
            Collection<Tree.Node> linkedNodes = testNode.getLinkedNodes();
            assertFalse(linkedNodes == null);
            Iterator<Tree.Node> iterator = linkedNodes.iterator();
            assertTrue(iterator.hasNext());
            Tree.Node nextNode = iterator.next();
            assertTrue(nextNode instanceof BstNode);
            BstNode<Integer, String> testLeft = (BstNode<Integer, String>) nextNode;
            assertEquals(referenceLeft.getKey(), testLeft.getKey());
            assertEquals(referenceLeft.getValue(), testLeft.getValue());
            assertTrue(iterator.hasNext());
            nextNode = iterator.next();
            assertTrue(nextNode instanceof BstNode);
            BstNode<Integer, String> testRight = (BstNode<Integer, String>) nextNode;
            assertEquals(referenceRight.getKey(), testRight.getKey());
            assertEquals(referenceRight.getValue(), testRight.getValue());
            assertFalse(iterator.hasNext());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Юнит-тест для метода {@link BstNode#getLeft()}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetLeft() throws Exception {
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            Integer leftKey = 12;
            BstNode<Integer, String> referenceLeft = nodeBuilder.setKey(leftKey).build();
            testNode.setLeft(referenceLeft);
            BstNode<Integer, String> testLeft = testNode.getLeft();
            assertEquals(referenceLeft.getKey(), testLeft.getKey());
            assertEquals(referenceLeft.getValue(), testLeft.getValue());
            assertTrue(testNode.getRight() == null);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Юнит-тест для метода {@link BstNode#setLeft(BstNode)}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSetLeft() throws Exception {
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            Integer leftKey = 12;
            BstNode<Integer, String> referenceLeft = nodeBuilder.setKey(leftKey).build();
            testNode.setLeft(referenceLeft);
            BstNode<Integer, String> testLeft = testNode.getLeft();
            assertEquals(referenceLeft.getKey(), testLeft.getKey());
            assertEquals(referenceLeft.getValue(), testLeft.getValue());
            assertTrue(testNode.getRight() == null);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Юнит-тест для метода {@link BstNode#getRight()}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetRight() throws Exception {
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            Integer rightKey = 12;
            BstNode<Integer, String> referenceRight = nodeBuilder.setKey(rightKey).build();
            testNode.setRight(referenceRight);
            BstNode<Integer, String> testRight = testNode.getRight();
            assertEquals(referenceRight.getKey(), testRight.getKey());
            assertEquals(referenceRight.getValue(), testRight.getValue());
            assertTrue(testNode.getLeft() == null);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Юнит-тест для метода {@link BstNode#setRight(BstNode)}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSetRight() throws Exception {
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            Integer rightKey = 12;
            BstNode<Integer, String> referenceRight = nodeBuilder.setKey(rightKey).build();
            testNode.setRight(referenceRight);
            BstNode<Integer, String> testRight = testNode.getRight();
            assertEquals(referenceRight.getKey(), testRight.getKey());
            assertEquals(referenceRight.getValue(), testRight.getValue());
            assertTrue(testNode.getLeft() == null);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Юнит-тест для метода {@link BstNode#setValue(Object)}
     *
     * @throws Exception ошибка теста
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSetValue() throws Exception {
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            assertEquals(null, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
        try {
            BstNode<Integer, String> testNode = nodeBuilder.setKey(TEST_KEY).build();
            String testString = String.valueOf(111);
            testNode.setValue(testString);
            assertEquals(testString, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
        try {
            BstNode<Integer, Integer> testNode = nodeBuilder.setKey(TEST_KEY).build();
            Integer testIntValue = 111;
            testNode.setValue(testIntValue);
            assertEquals(testIntValue, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
        try {
            BstNode<Integer, BigInteger> testNode = nodeBuilder.setKey(TEST_KEY).build();
            BigInteger testBigIntValue = BigInteger.valueOf(111);
            testNode.setValue(testBigIntValue);
            assertEquals(testBigIntValue, testNode.getValue());
        } catch (Exception e) {
            fail();
        }
    }
}