package org.lights1eep.skiplist;

/**
 * 跳表节点
 * @author lights1eep
 */
class Node {
    /**
     * 数值
     */
    int val;
    /**
     * 下一个节点
     */
    Node[] next;

    Node(int val, int maxLevel) {
        this.val = val;
        this.next = new Node[maxLevel];
    }
}
