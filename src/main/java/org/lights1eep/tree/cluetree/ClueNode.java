package org.lights1eep.tree.cluetree;

/**
 * 线索二叉树节点
 * @author lights1eep
 */
public class ClueNode{
    int data;
    ClueNode left;
    ClueNode right;
    /**
     * 左节点标志 0 表示指向左孩子 1 表示指向前驱
     */
    int leftFlag;
    /**
     * 右节点标志 0 表示指向右孩子 1 表示指向后继
     */
    int rightFlag;

    public ClueNode(int data) {
        this(data, null, null);
    }

    ClueNode(int data, ClueNode left, ClueNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.leftFlag = 0;
        this.rightFlag = 0;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ClueNode getLeft() {
        return left;
    }

    public void setLeft(ClueNode left) {
        this.left = left;
    }

    public ClueNode getRight() {
        return right;
    }

    public void setRight(ClueNode right) {
        this.right = right;
    }

    public int getLeftFlag() {
        return leftFlag;
    }

    public void setLeftFlag(int leftFlag) {
        this.leftFlag = leftFlag;
    }

    public int getRightFlag() {
        return rightFlag;
    }

    public void setRightFlag(int rightFlag) {
        this.rightFlag = rightFlag;
    }
}
