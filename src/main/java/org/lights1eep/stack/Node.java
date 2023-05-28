package org.lights1eep.stack;

/**
 * 链表节点
 * @author lights1eep
 */
class Node {
    /**
     * 数值
     */
    private int value;
    /**
     * 下一个节点
     */
    private Node next;

    Node() {
        this(0, null);
    }

    Node(int value) {
        this(value, null);
    }
    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
