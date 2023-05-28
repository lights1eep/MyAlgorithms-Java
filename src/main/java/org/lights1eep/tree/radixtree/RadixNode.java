package org.lights1eep.tree.radixtree;

/**
 * 基数树节点
 * @author lights1eep
 */
public class RadixNode {
    int num;
    /**
     * 是否是叶子节点
     */
    boolean isLeaf;
    RadixNode[] children;

    public RadixNode() {
        int num = 0;
        this.isLeaf = false;
        this.children = new RadixNode[4];
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean setLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
