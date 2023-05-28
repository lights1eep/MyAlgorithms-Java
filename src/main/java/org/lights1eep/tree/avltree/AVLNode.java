package org.lights1eep.tree.avltree;

/**
 * 二叉平衡树节点
 * @author lights1eep
 */
public class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    AVLNode parent;
    /**
     * 高度
     */
    int height;
    /**
     * 平衡因子
     */
    int bf;

    AVLNode(int data) {
        this.data = data;
        this.left = this.right = this.parent = null;
        this.height = 0;
        this.bf = 0;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public AVLNode getParent() {
        return parent;
    }

    public void setParent(AVLNode parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "data=" + data +
                ", parent=" + (parent != null ? "" + parent.getData() : "-1") +
                ", height=" + height +
                ", bf=" + bf +
                '}';
    }
}
