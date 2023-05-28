package org.lights1eep.tree.rbtree;

/**
 * 红黑树节点
 * @author lights1eep
 */
public class RBNode {
    int data;
    RBNode parent;
    RBNode left;
    RBNode right;
    /**
     * 节点颜色
     */
    RBColor color;

    public RBNode(int data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.color = RBColor.RED;
    }

    @Override
    public String toString() {
        return "RBNode{" +
                "data=" + data +
                ", color=" + color +
                '}';
    }
}
