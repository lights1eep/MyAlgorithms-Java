package org.lights1eep.tree.avltree;

/**
 * 二叉平衡树
 * @author lights1eep
 */
public class AVLTree {
    /**
     * 根节点
     */
    private AVLNode root;
    /**
     * 最近被插入的点 或 最近被删除的点的父节点
     */
    private AVLNode curNode;


    public AVLTree() {
        this.root = null;
        this.curNode = null;
    }

    public AVLNode getRoot() {
        return root;
    }

    /**
     * 插入节点
     * @param data 插入值
     */
    public void insertNode(int data) {
        if(this.root == null) {
            this.root = new AVLNode(data);
        } else {
            insertNodeByData(this.root, data);
            updateHeightAndBf(this.root);
            rotateAVLTree();
        }
    }

    /**
     * 递归实现插入节点
     * @param node 根节点
     * @param num 数值
     */
    private void insertNodeByData(AVLNode node, int num) {
        int data = node.getData();
        if(data == num) {
            return;
        } else if(data > num) {
            if(node.getLeft() == null) {
                AVLNode newNode = new AVLNode(num);
                node.setLeft(newNode);
                newNode.setParent(node);
                this.curNode = newNode;
            } else {
                insertNodeByData(node.getLeft(), num);
            }
        } else {
            if(node.getRight() == null) {
                AVLNode newNode = new AVLNode(num);
                node.setRight(newNode);
                newNode.setParent(node);
                this.curNode = newNode;
            } else {
                insertNodeByData(node.getRight(), num);
            }
        }
    }

    /**
     * 更新节点的高度height和平衡因子bf
     * @param node 节点
     * @return 节点的高度
     */
    private int updateHeightAndBf(AVLNode node) {
        if(node == null) {
            return 0;
        }
        int leftHeight = updateHeightAndBf(node.getLeft());
        int rightHeight = updateHeightAndBf(node.getRight());
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
        node.setBf(leftHeight - rightHeight);
        return node.getHeight();
    }


    /**
     * 按照中序输出节点 递归
     * @param node 节点
     */
    public void printAVLTree(AVLNode node) {
        if(node == null) {
            return;
        }
        printAVLTree(node.getLeft());
        System.out.println(node);
        printAVLTree(node.getRight());
    }

    /**
     * 旋转二叉平衡树
     */
    private void rotateAVLTree() {
        AVLNode treeRoot = getMinUnbalancedSubTreeRoot();
        // 要找到根节点与最深的节点的关系
        if(treeRoot == null) {
            return;
        }
        AVLNode left = treeRoot.getLeft();
        AVLNode right = treeRoot.getRight();
        if(treeRoot.getBf() > 0 && left.getBf() > 0) {    //在节点的左孩子的左子树插入 LL
            rotateLL(treeRoot);
        } else if(treeRoot.getBf() > 0 && left.getBf() <= 0) {     //在节点的左孩子的右子树插入 LR
            rotateLR(treeRoot);
        } else if (treeRoot.getBf() < 0 && right.getBf() < 0) {   //在节点的右孩子的右子树插入 RR
            rotateRR(treeRoot);
        } else if(treeRoot.getBf() < 0 && right.getBf() >= 0) {    //在节点的右孩子的左子树插入 RL
            rotateRL(treeRoot);
        }
        this.curNode = null;
        updateRoot(treeRoot);
        updateHeightAndBf(this.root);
    }

    /**
     * 寻找最小失衡子树根节点
     * @return 最小失衡子树根节点
     */
    private AVLNode getMinUnbalancedSubTreeRoot() {
        AVLNode node = this.curNode;
        while(node!= null) {
            if(Math.abs(node.getBf()) > 1) {
                break;
            }
            node = node.getParent();
        }
        return node;
    }

    /**
     * 在节点的左孩子的左子树插入
     * @param node 最小失衡子树根节点
     * @return 旋转后的子树的根节点
     */
    private AVLNode rotateLL(AVLNode node) {
        AVLNode left = node.getLeft();
        node.setLeft(left.getRight());
        if(left.getRight() != null) {
            left.getRight().setParent(node);
        }
        left.setRight(node);
        left.setParent(node.getParent());
        node.setParent(left);
        return left;
    }
    /**
     * 在节点的右孩子的右子树插入
     * @param node 最小失衡子树根节点
     * @return 旋转后的子树的根节点
     */
    private AVLNode rotateRR(AVLNode node) {
        AVLNode right = node.getRight();
        node.setRight(right.getLeft());
        if(right.getLeft() != null) {
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
        right.setParent(node.getParent());
        node.setParent(right);
        return right;
    }
    /**
     * 在节点的左孩子的右子树插入
     * @param node 最小失衡子树根节点
     * @return 旋转后的子树的根节点
     */
    private void rotateLR(AVLNode node) {
        AVLNode newLeft = rotateRR(node.getLeft());
        node.setLeft(newLeft);
        rotateLL(node);
    }
    /**
     * 在节点的右孩子的左子树插入
     * @param node 最小失衡子树根节点
     * @return 旋转后的子树的根节点
     */
    private void rotateRL(AVLNode node) {
        AVLNode newRight = rotateLL(node.getRight());
        node.setRight(newRight);
        rotateRR(node);
    }

    /**
     * 更新根节点
     * @param node 最小失衡子树根节点
     */
    private void updateRoot(AVLNode node) {
        while(node.getParent() != null) {
            node = node.getParent();
        }
        this.root = node;
    }

    /**
     * 根据 data 查询是否在二叉平衡树中
     * @param data 被查找数据
     * @return 找到的节点 或 null
     */
    public AVLNode queryNode(int data) {
        return queryNodeByData(this.root, data);
    }


    /**
     * 根据data查询节点
     * @param node 节点
     * @param data 数据
     * @return 找到的节点 或 null
     */
    private AVLNode queryNodeByData(AVLNode node, int data) {
        if(node == null) {
            return null;
        }
        if(node.getData() == data) {
            return node;
        } else if(node.getData() > data){
            return queryNodeByData(node.getLeft(), data);
        }
        return queryNodeByData(node.getRight(), data);
    }

    /**
     * 根据data删除节点
     * @param data 数据
     */
    public void deleteNode(int data) {
        AVLNode node = queryNode(data);
        if(node != null) {
            AVLNode left = node.getLeft();
            AVLNode right = node.getRight();
            AVLNode parent = node.getParent();
            if(left == null && right == null) {                 // 叶子节点
                if(parent == null) {
                    this.root = null;
                    this.curNode = null;
                } else {
                    if(parent.getData() > node.getData()) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                    node.setParent(null);
                    this.curNode = parent;
                }
            } else if(left != null && right == null) {          // 只有左孩子
                left.setParent(parent);
                this.curNode = left;
                if(parent != null) {
                    if(parent.getData() > node.getData()) {
                        parent.setLeft(left);
                    } else {
                        parent.setRight(left);
                    }
                }
                if(this.root == node) {
                    this.root = left;
                }
                node.setLeft(null);
                node.setRight(null);
            } else if(left == null && right != null) {          // 只有右孩子
                right.setParent(parent);
                this.curNode = right;
                if(parent != null) {
                    if(parent.getData() > node.getData()) {
                        parent.setLeft(right);
                    } else {
                        parent.setRight(right);
                    }
                }
                if(this.root == node) {
                    this.root = right;
                }
                node.setLeft(null);
                node.setRight(null);
            } else {                                            // 左右孩子都有
                int bf = node.getBf();
                if(bf == 0 || bf == 1) {
                    AVLNode max = left;
                    while(max.getRight() != null) {
                        max = max.getRight();
                    }
                    node.setData(max.getData());
                    AVLNode maxParent = max.getParent();
                    this.curNode = maxParent;
                    if(maxParent.getData() >= max.getData()) {
                        maxParent.setLeft(max.getLeft());
                    } else {
                        maxParent.setRight(max.getLeft());
                    }
                    max.setParent(null);
                } else {
                    AVLNode min = right;
                    while(min.getLeft() != null) {
                        min = min.getLeft();
                    }
                    node.setData(min.getData());
                    AVLNode minParent = min.getParent();
                    this.curNode = minParent;
                    if(minParent.getData() >= min.getData()) {
                        minParent.setLeft(min.getRight());
                    } else {
                        minParent.setRight(min.getRight());
                    }

                    min.setParent(null);
                }
            }
            updateHeightAndBf(this.root);
            rotateAVLTree();
        }
    }

}
