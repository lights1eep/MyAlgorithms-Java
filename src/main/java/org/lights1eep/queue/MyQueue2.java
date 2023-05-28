package org.lights1eep.queue;

/**
 * 使用链表实现队
 * @author lights1eep
 */
public class MyQueue2 implements MyAbstractQueue {
    /**
     * 队列中元素数量
     */
    private int size;
    /**
     * 队头指针
     */
    private Node head;
    /**
     * 队尾指针
     */
    private Node tail;

    MyQueue2() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void push(int value) {
        Node node = new Node(value);
        if(this.isEmpty()) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = this.tail.getNext();
        }
        this.size++;
    }

    @Override
    public int pop() {
        int value = this.front();
        this.head = this.head.getNext();
        this.size--;
        return value;
    }

    @Override
    public int front() throws NullPointerException {
        if(this.isEmpty()) {
            throw new NullPointerException("队列为空");
        }
        return this.head.getValue();
    }

    @Override
    public int back() throws NullPointerException {
        if(this.isEmpty()) {
            throw new NullPointerException("队列为空");
        }
        return this.tail.getValue();
    }
}
