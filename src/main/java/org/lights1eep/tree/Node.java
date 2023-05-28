package org.lights1eep.tree;

/**
 * 二叉树节点
 * @author lights1eep
 */
public class Node {
    /**
     * 数值
     */
    int data;
    /**
     * 左孩子
     */
    Node left;
    /**
     * 右孩子
     */
    Node right;

    public Node(int data) {
        this(data, null, null);
    }

    Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
