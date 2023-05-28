package org.lights1eep.queue;


/**
 * 使用循环数组实现队
 * @author lights1eep
 */
public class MyQueue1 implements MyAbstractQueue{
    /**
     * 数组容量
     */
    private int capacity;
    /**
     * 队列中元素数量
     */
    private int size;
    /**
     * 队头指针
     */
    private int head;
    /**
     * 队尾指针
     */
    private int tail;
    /**
     * 底层数组
     */
    private int[] arr;

    MyQueue1() {
        this(16);
    }

    MyQueue1(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
        this.arr = new int[this.capacity];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * 扩展数组为原来的两倍长度
     */
    private void expendQueue() {
        int len = this.capacity;
        this.capacity *= 2;
        int[] tmp = new int[this.capacity];
        for(int i = this.head, j = 0; j < this.size; i = (++i) % len, j++) {
            tmp[j] = this.arr[i];
        }
        this.arr = tmp;
        this.head = 0;
        this.tail = this.size - 1;
    }

    @Override
    public void push(int value) {
        // 如果底层数组已满，则进行数组扩容
        if(this.size == this.capacity) {
            expendQueue();
        }
        this.arr[this.tail] = value;
        this.tail = (this.tail + 1) % this.capacity;
        this.size++;
    }

    @Override
    public int pop() {
        int value = this.front();
        this.head = (this.head + 1) % this.capacity;
        this.size--;
        return value;
    }

    @Override
    public int front() throws NullPointerException {
        if(this.isEmpty()) {
            throw new NullPointerException("队列为空");
        }
        return this.arr[this.head];
    }

    @Override
    public int back() throws NullPointerException {
        if(this.isEmpty()) {
            throw new NullPointerException("队列为空");
        }
        return this.arr[(this.tail - 1 + this.capacity) % this.capacity];
    }
}
