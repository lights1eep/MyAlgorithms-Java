package org.lights1eep.tree.rbtree;

/**
 * 红黑树
 * @author lights1eep
 */
public class RBTree {
    private RBNode root;
    /**
     * 哨兵节点
     */
    private RBNode nil;

    RBTree() {
        this.nil = new RBNode(-1);
        setColor(this.nil, RBColor.BLACK);
        this.root = this.nil;
    }

    /**
     * 给节点设置颜色
     * @param node  节点
     * @param color 颜色
     */
    private void setColor(RBNode node, RBColor color) {
        node.color = color;
    }

    /**
     * 插入数值
     * @param data  数值
     */
    public void insert(int data) {
        RBNode z = new RBNode(data);
        RBNode x = this.root;
        RBNode y = this.nil;
        while(x != this.nil) {
            y = x;
            if(z.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if(y == this.nil) {
            this.root = z;
        } else if(z.data < y.data) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = this.nil;
        z.right = this.nil;
        insertFixUp(z);

    }

    /**
     * 插入后的调整
     * @param node 插入的节点
     */
    private void insertFixUp(RBNode node) {
        while(node != this.nil && node != this.root && node.parent.color == RBColor.RED) {
            if(node.parent == node.parent.parent.left) {
                RBNode uncle = node.parent.parent.right;
                if(uncle.color == RBColor.RED) {
                    setColor(node.parent, RBColor.BLACK);
                    setColor(uncle, RBColor.BLACK);
                    setColor(node.parent.parent, RBColor.RED);
                    node = node.parent.parent;
                } else {
                    if(node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    setColor(node.parent, RBColor.BLACK);
                    setColor(node.parent.parent, RBColor.RED);
                    rightRotate(node.parent.parent);
                }
            } else {
                RBNode uncle = node.parent.parent.left;
                if(uncle.color == RBColor.RED) {
                    setColor(node.parent, RBColor.BLACK);
                    setColor(uncle, RBColor.BLACK);
                    setColor(node.parent.parent, RBColor.RED);
                    node = node.parent.parent;
                } else {
                    if(node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    setColor(node.parent, RBColor.BLACK);
                    setColor(node.parent.parent, RBColor.RED);
                    leftRotate(node.parent.parent);
                }
            }
        }
        setColor(this.root, RBColor.BLACK);
    }

    /**
     * 对以node的根的子树进行左旋
     * @param node  节点
     */
    private void leftRotate(RBNode node) {
        RBNode right = node.right;
        node.right = right.left;
        if(right.left != this.nil) {
            right.left.parent = node;
        }
        right.parent = node.parent;
        if(node.parent == this.nil) {
            this.root = right;
        } else if(node == node.parent.left){
            node.parent.left = right;
        } else {
            node.parent.right = right;
        }
        right.left = node;
        node.parent = right;
    }
    /**
     * 对以node的根的子树进行右旋
     * @param node  节点
     */
    private void rightRotate(RBNode node) {
        RBNode left = node.left;
        node.left = left.right;
        if(left.right != this.nil) {
            left.right.parent = node;
        }
        left.parent = node.parent;
        if(node.parent == this.nil) {
            this.root = left;
        } else if(node == node.parent.left){
            node.parent.left = left;
        } else {
            node.parent.right = left;
        }
        left.right = node;
        node.parent = left;
    }

    public RBNode query(int data) {
        RBNode node = this.root;
        while(node != this.nil) {
            if(node.data == data) {
                break;
            } else if(node.data > data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    /**
     * 用node2替换node1
     * @param node1 被替换节点
     * @param node2 替换节点
     */
    private void transplant(RBNode node1, RBNode node2) {
        if(node1.parent == this.nil) {
            this.root = node2;
        } else if(node1 == node1.parent.left) {
            node1.parent.left = node2;
        } else {
            node1.parent.right = node2;
        }
        node2.parent = node1.parent;
    }

    /**
     * 删除节点
     * @param data 要删除的数字
     */
    public void delete(int data) {
        RBNode node = query(data);
        RBNode y = node;
        RBColor yOriginColor = y.color;
        RBNode x = null;
        if(node.left == this.nil) {
            x = node.right;
            transplant(node, node.right);
        } else if(node.right == this.nil) {
            x = node.left;
            transplant(node, node.left);
        } else {
            y = node.right;
            while(y.left != this.nil) {
                y = y.left;
            }
            yOriginColor = y.color;
            x = y.right;
            if(y.parent == node) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            transplant(node, y);
            y.left = node.left;
            y.left.parent = y;
            setColor(y, node.color);
        }
        if(yOriginColor == RBColor.BLACK) {
            deleteFixup(x);
        }
    }

    /**
     * 删除后调整
     * @param node 被替换的节点
     */
    private void deleteFixup(RBNode node) {
        while(node != this.root && node.color == RBColor.BLACK) {
            if(node == node.parent.left) {
                RBNode brother = node.parent.right;
                if(brother.color == RBColor.RED) {
                    setColor(brother, RBColor.BLACK);
                    setColor(node.parent, RBColor.RED);
                    leftRotate(node.parent);
                    brother = node.parent.right;
                }
                if(brother.left.color == RBColor.BLACK && brother.right.color == RBColor.BLACK) {
                    setColor(brother, RBColor.RED);
                    node = node.parent;
                } else {
                    if(brother.right.color == RBColor.BLACK) {
                        setColor(brother.left, RBColor.BLACK);
                        setColor(brother, RBColor.RED);
                        rightRotate(brother);
                        brother = node.parent.right;
                    }
                    setColor(brother, node.parent.color);
                    setColor(node.parent, RBColor.BLACK);
                    setColor(brother.right, RBColor.BLACK);
                    leftRotate(node.parent);
                    node = this.root;
                }
            } else {
                RBNode brother = node.parent.left;
                if(brother.color == RBColor.RED) {
                    setColor(brother, RBColor.BLACK);
                    setColor(node.parent, RBColor.RED);
                    rightRotate(node.parent);
                    brother = node.parent.left;
                }
                if(brother.left.color == RBColor.BLACK && brother.right.color == RBColor.BLACK) {
                    setColor(brother, RBColor.RED);
                    node = node.parent;
                } else {
                    if(brother.left.color == RBColor.BLACK) {
                        setColor(brother.right, RBColor.BLACK);
                        setColor(brother, RBColor.RED);
                        leftRotate(brother);
                        brother = node.parent.left;
                    }
                    setColor(brother, node.parent.color);
                    setColor(node.parent, RBColor.BLACK);
                    setColor(brother.left, RBColor.BLACK);
                    rightRotate(node.parent);
                    node = this.root;
                }
            }
        }
        setColor(node, RBColor.BLACK);
    }
}
