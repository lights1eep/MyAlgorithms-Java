package org.lights1eep.stack;

/**
 * 数组实现栈
 * @author lights1eep
 */
public class MyStack1 implements MyAbstractStack {
    /**
     * 数组容量
     */
    private int capacity;
    /**
     * 栈中元素数量
     */
    private int size;
    /**
     * 栈顶指针
     */
    private int head;
    /**
     * 底层数组
     */
    private int[] arr;

    MyStack1() {
        this(16);
    }

    MyStack1(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = 0;
        this.arr = new int[this.capacity];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void push(int value) {
        if(this.size == this.capacity) {
            expendStack();
        }
        this.arr[this.head] = value;
        this.head++;
        this.size++;
    }

    /**
     * 将栈扩充为原来的两倍
     */
    private void expendStack() {
        this.capacity *= 2;
        int[] tmp = new int[this.capacity];
        System.arraycopy(this.arr, 0, tmp, 0, this.arr.length);
        this.arr = tmp;
    }

    @Override
    public int pop() {
        int value = this.peek();
        this.head--;
        this.size--;
        return value;
    }

    @Override
    public int peek() throws NullPointerException {
        if(this.isEmpty()) {
            throw new NullPointerException("栈为空");
        }
        return this.arr[this.head - 1];
    }
}
