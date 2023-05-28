package org.lights1eep.skiplist;

import java.util.Arrays;

/**
 * 跳表
 * @author lights1eep
 */
public class SkipList implements AbstractSkipList {
    /**
     * 跳表最大层数
     */
    private static final int MAX_LEVEL = 16;
    /**
     * 确定新节点层数的可能性
     */
    private static final double POSSIBILITY = 0.25;
    /**
     * 跳表当前层数
     */
    private int level;
    /**
     * 跳表头节点
     */
    private Node head;

    public SkipList() {
        this.head = new Node(-1, MAX_LEVEL);
        this.level = 0;
    }

    @Override
    public boolean search(int target) {
        Node cur = this.head;
        // 从高到低遍历
        for(int i = this.level - 1; i >= 0; i--) {
            while(cur.next[i] != null && cur.next[i].val < target) {
                cur = cur.next[i];
            }
        }
        // 已经到了最底层，获取最底层的节点
        cur = cur.next[0];
        return cur != null && cur.val == target;
    }

    @Override
    public void add(int num) {
        Node cur = this.head;
        // updates存储了每一次最后一个被遍历的节点
        Node[] updates = new Node[MAX_LEVEL];
        Arrays.fill(updates, this.head);
        for(int i = this.level - 1; i >= 0; i--) {
            while(cur.next[i] != null && cur.next[i].val < num) {
                cur = cur.next[i];
            }
            updates[i] = cur;
        }
        // 对新节点生成层数
        int randomLevel = randomLevel();
        // 更新跳表最大层数
        this.level = Math.max(this.level, randomLevel);
        Node node = new Node(num, randomLevel);
        // 对跳表进行更新
        for(int i = 0; i < randomLevel; i++) {
            node.next[i] = updates[i].next[i];
            updates[i].next[i] = node;
        }
    }

    /**
     * 生成随机层数
     * @return  随机层数
     */
    private int randomLevel() {
        int level = 1;
        while(Math.random() < POSSIBILITY && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    @Override
    public boolean erase(int num) {
        Node cur = this.head;
        Node[] updates = new Node[MAX_LEVEL];
        Arrays.fill(updates, this.head);
        for(int i = this.level - 1; i >= 0; i--) {
            while(cur.next[i] != null && cur.next[i].val < num) {
                cur = cur.next[i];
            }
            updates[i] = cur;
        }
        cur = cur.next[0];
        // 不存在num节点
        if(cur == null || cur.val != num) {
            return false;
        }
        // 删除num节点
        for(int i = 0; i < this.level; i++) {
            if(updates[i].next[i] != cur) {
                break;
            }
            updates[i].next[i] = cur.next[i];
        }
        // 更新跳表最大层数
        while(this.level > 1 && this.head.next[this.level - 1] == null) {
            this.level--;
        }
        return true;
    }
}
