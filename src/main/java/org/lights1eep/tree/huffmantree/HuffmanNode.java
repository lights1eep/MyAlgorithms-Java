package org.lights1eep.tree.huffmantree;

import java.util.Objects;

/**
 * 哈夫曼树节点
 * @author lights1eep
 */
public class HuffmanNode implements Comparable {
    /**
     * 字符
     */
    char c;
    /**
     * 出现次数
     */
    int num;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char c, int num) {
        this(c, num, null, null);
    }

    public HuffmanNode(char c, int num, HuffmanNode left, HuffmanNode right) {
        this.c = c;
        this.num = num;
        this.left = left;
        this.right = right;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HuffmanNode that = (HuffmanNode) o;
        return num == that.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "c=" + c +
                ", num=" + num +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        HuffmanNode node = (HuffmanNode) o;
        return this.getNum() - node.getNum();
    }
}
