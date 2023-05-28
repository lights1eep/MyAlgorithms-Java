package org.lights1eep.tree.sortedtree;


import org.lights1eep.tree.Node;

/**
 * 二叉排序树
 * @author lights1eep
 */
public class SortedTree {
    public Node root;

    public SortedTree() {
        this(null);
    }

    public SortedTree(Node root) {
        this.root = root;
    }

    /**
     * 根据 num 查询节点
     * @param num   数值
     * @return  查询到的节点 没有则为 null
     */
    public Node searchNode(int num) {
        return searchNodeByNum(this.root, num);
    }

    /**
     * 递归实现根据 num 查询节点
     * @param node 根节点
     * @param num 数值
     * @return 查询到的节点 没有则为 null
     */
    private Node searchNodeByNum(Node node, int num) {
        if(node != null) {
            int data = node.getData();
            if(data == num) {
                return node;
            } else {
                return data > num ? searchNodeByNum(node.getLeft(), num) : searchNodeByNum(node.getRight(), num);
            }
        }
        return null;
    }

    /**
     * 插入节点
     * @param num 插入值
     */
    public void insertNode(int num) {
        if(this.root == null) {
            this.root = new Node(num);
        } else {
            insertNodeByNum(this.root, num);
        }
    }

    /**
     * 递归实现插入节点
     * @param node 根节点
     * @param num 数值
     */
    private void insertNodeByNum(Node node, int num) {
        if(node == null) {
            node = new Node(num);
        } else {
            int data = node.getData();
            if(data == num) {
                return;
            } else if(data > num) {
                if(node.getLeft() == null) {
                    node.setLeft(new Node(num));
                } else {
                    insertNodeByNum(node.getLeft(), num);
                }
            } else {
                if(node.getRight() == null) {
                    node.setRight(new Node(num));
                } else {
                    insertNodeByNum(node.getRight(), num);
                }
            }
        }
    }

    /**
     * 根据 num 删除节点
     * @param num 要删除的节点值
     * @return true 删除成功 ; false 删除失败
     */
    public boolean deleteNode(int num) {
        // 查询是否存在该节点
        Node node = searchNode(num);
        if(node == null) {
            return false;
        }
        if(node.getLeft() == null && node.getRight() == null) { // 要删除的节点是叶子节点
            if(this.root == node) {
                this.root = null;
            } else {
                replaceNodeByOther(node, null);
            }
        } else if (node.getRight() == null) {    // 要删除的节点只有左子树
            replaceNodeByOther(node, node.getLeft());
        } else if (node.getLeft() == null) {   // 要删除的节点只有右子树
            replaceNodeByOther(node, node.getRight());
        } else {
            // 找到该节点的后继节点和后继节点的父节点
            Node s = node.getRight();
            Node p = node;
            while(s.getLeft() != null) {
                p = s;
                s = s.getLeft();
            }
            // 将要删除的节点值进行替换
            node.setData(s.getData());
            // 对于后继节点，对其右子树进行处理
            if(p.getLeft() == s) {  // 如果父节点的左孩子是后继节点
                p.setLeft(s.getRight());
            } else {    // 如果父节点的右孩子是后继节点
                p.setRight(s.getRight());
            }
        }
        return true;
    }

    /**
     * 使用 node2 替换 node1
     * @param node1 被替换节点
     * @param node2 替换节点
     */
    private void replaceNodeByOther(Node node1, Node node2) {
        Node parent = searchParentNode(node1.getData());
        if(parent.getLeft() == node1) {
            parent.setLeft(node2);
        } else {
            parent.setRight(node2);
        }
    }

    /**
     * 根据 num 查询父节点
     * @param num 数值
     * @return 父节点 如果没有则为 null 根节点也没有父节点
     */
    public Node searchParentNode(int num) {
        return searchParentNodeByNum(this.root, num);
    }

    /**
     * 根据 num 查询父节点
     * @param node 根节点
     * @param num 数值
     * @return 父节点 如果没有则为 null 根节点也没有父节点
     */
    private Node searchParentNodeByNum(Node node, int num) {
        if((node.getLeft() != null && node.getLeft().getData() == num) ||
                (node.getRight() != null && node.getRight().getData() == num)) {
            return node;
        } else {
            if(node.getData() > num && node.getLeft() != null) {
                return searchParentNodeByNum(node.getLeft(), num);
            } else if(node.getData() < num && node.getRight() != null) {
                return searchParentNodeByNum(node.getRight(), num);
            }
        }
        return null;
    }
}
