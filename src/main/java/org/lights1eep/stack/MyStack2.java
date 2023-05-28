package org.lights1eep.stack;

/**
 * 链表实现栈
 * @author lights1eep
 */
public class MyStack2 implements MyAbstractStack {
    /**
     * 栈中元素数量
     */
    private int size;
    /**
     * 栈顶指针
     */
    private Node head;

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void push(int value) {
        Node node = new Node(value);
        if (this.isEmpty()) {
            this.head = node;
        } else {
            node.setNext(this.head);
            this.head = node;
        }
        this.size++;
    }

    @Override
    public int pop() {
        int value = this.peek();
        this.head = this.head.getNext();
        this.size--;
        return value;
    }

    @Override
    public int peek() throws NullPointerException {
        if(this.isEmpty()) {
            throw new NullPointerException("栈为空");
        }
        return this.head.getValue();
    }
}
