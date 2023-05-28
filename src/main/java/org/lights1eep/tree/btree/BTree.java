package org.lights1eep.tree.btree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * B树
 * @author lights1eep
 */
public class BTree {
    /**
     * b树的阶
     */
    private int m;
    /**
     * 根节点
     */
    private BNode root;

    public BTree(int m) {
        this.m = m;
        this.root = new BNode(m);
    }

    /**
     * 插入关键字
     * @param num 关键字
     */
    public void insert(int num) {
        BNode node = queryLeafBNode(num);
        node.insertNum(num);
        // 节点的关键字个数超过m，分裂节点并得到分裂后的子树的根节点，继续判断直到不用分裂为止
        while(node.size == m) {
            node = splitBNode(node);
        }
        updateBTreeRoot();
    }

    /**
     * 根据num找到要插入的叶子节点
     * @param num 关键字
     * @return 叶子节点
     */
    public BNode queryLeafBNode(int num) {
        BNode node = this.root;
        while(!node.isLeaf) {
            int i = 0;
            while(i < node.size && num > node.nums[i]) {
                i++;
            }
            node = node.children[i];
        }
        return node;
    }

    /**
     * 查询包含num的节点
     * @param num 关键字
     * @return 包含num的节点
     */
    public BNode queryBNode(int num) {
        BNode node = this.root;
        while(node != null) {
            int i = 0;
            while(i < node.size && num > node.nums[i]) {
                i++;
            }
            if(node.nums[i] == num) {
                return node;
            }
            node = node.children[i];
        }
        return null;
    }

    /**
     * 当节点的关键字个数超过m时，分裂节点，并返回分裂后的子树的根
     * @param node 关键字个数超过m的节点
     * @return 分裂后的子树的根
     */
    public BNode splitBNode(BNode node) {
        // 找到节点关键字数组的中间值
        int parentNum = node.nums[this.m / 2];
        BNode parent = node.parent;
        // 如果节点的parent为空
        if(parent == null) {
            parent = new BNode(this.m);
            parent.isLeaf = false;
            node.parent = parent;
        }
        // 将中间值放到parent中
        parent.insertNum(parentNum);
        // 找到中间值在parent中的索引
        int index = parent.queryByNum(parentNum);
        // 创建right节点，作为分裂后的子树的根节点的右节点
        BNode right = new BNode(this.m);
        right.parent = parent;
        // 将中间值的右半部分给right节点
        for(int i = this.m / 2 + 1; i < this.m; i++) {
            right.insertNum(node.nums[i]);
            node.nums[i] = 0;
        }
        // 更新node节点
        node.size = this.m / 2;
        // 如果node节点不在parent中
        if(parent.queryByBNode(node) == -1) {
            parent.insertBNode(node, index);
        }
        parent.insertBNode(right, index + 1);
        // 如果node是非叶子节点，它的孩子节点数组也要分裂
        if(!node.isLeaf) {
            // 此时right节点就肯定不是叶子节点了
            right.isLeaf = false;
            // 将node节点的右半部分孩子节点给right节点
            for(int i = node.children.length / 2, index1 = 0; i < node.children.length; i++, index1++) {
                right.insertBNode(node.children[i], index1);
                node.children[i] = null;
            }
        }
        return parent;
    }

    /**
     * 更新根节点
     */
    public void updateBTreeRoot() {
        BNode node = this.root;
        while(node.parent != null) {
            node = node.parent;
        }

        while(node.size == 0) {
            node = node.children[0];
            node.parent = null;
        }

        this.root = node;


    }


    /**
     * 按照层序输出树
     */
    public void printBTree() {
        Queue<BNode> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()) {
            BNode node = queue.poll();
            System.out.println(node);
            if(!node.isLeaf) {
                for(int i = 0; i <= node.size; i++) {
                    queue.offer(node.children[i]);
                }
            }
        }
    }

    /**
     * 删除关键字
     * @param num 关键字
     */
    public void delete(int num) {

        BNode node = queryBNode(num);
        if(node == null) {
            return;
        }
        deleteBNodeByNum(node, num);
        updateBTreeRoot();

    }

    /**
     * 删除node节点中的num
     * @param node 节点
     * @param num   关键字
     */
    private void deleteBNodeByNum(BNode node, int num) {
        int index = node.queryByNum(num);

        // 叶子节点
        if(node.isLeaf) {
            node.deleteNum(num);
            // 如果关键字个数少于规定，则向兄弟节点借
            if(node.size < this.m / 2) {
                BNode parent = node.parent;
                int query = parent.queryByBNode(node);
                BNode leftBro  = null;
                BNode rightBro = null;
                if(query != 0) {
                    leftBro = parent.children[query - 1];
                }
                if(query != this.m - 1) {
                    rightBro = parent.children[query + 1];
                }
                if(leftBro != null && leftBro.size > this.m / 2) {
                    node.insertNum(parent.nums[query]);
                    parent.nums[query] = leftBro.nums[leftBro.size - 1];
                    leftBro.deleteNum(parent.nums[query]);
                } else if(rightBro != null && rightBro.size > this.m / 2) {
                    node.insertNum(parent.nums[query]);
                    parent.nums[query] = rightBro.nums[0];
                    rightBro.deleteNum(parent.nums[query]);
                } else {
                    // 兄弟节点的关键字个数也不足，开始合并
                    while(node != null && node.size < this.m / 2) {
                        node = mergeBNode(node);
                    }
                }
            }
        } else {    // 非叶子节点
            BNode left = node.children[index];
            BNode right = node.children[index + 1];
            // 找孩子借，转换成叶子节点删除的情况
            if(left.size > this.m / 2) {
                node.nums[index] = left.nums[left.size - 1];
                deleteBNodeByNum(left, node.nums[index]);
            }
            else {
                node.nums[index] = right.nums[0];
                deleteBNodeByNum(right, node.nums[index]);
            }
        }
    }

    /**
     * 合并节点，并返回合并后节点的父节点
     * @param node  要合并的节点
     * @return  合并节点的父节点
     */
    private BNode mergeBNode(BNode node) {
        BNode parent = node.parent;
        if(parent == null) {
            this.root = node;
            return null;
        }
        int index = parent.queryByBNode(node);
        BNode brother = null;
        if(index != 0) {
            brother = parent.children[index - 1];
            brother.insertNum(parent.nums[index - 1]);
            int j = brother.size;
            for(int i = 0; i < node.size; i++) {
                brother.insertNum(node.nums[i]);
            }
            if(!node.isLeaf) {
                for(int i = 0; i <= node.size; i++, j++) {
                    brother.insertBNode(node.children[i], j + 1);
                }
            }
            parent.deleteNum(parent.nums[index - 1]);
            parent.deleteNode(index);
        } else if(index < parent.size) {
            brother = parent.children[index + 1];
            brother.insertNum(parent.nums[index]);
            for(int i = 0; i < node.size; i++) {
                brother.insertNum(node.nums[i]);
            }
            if(!node.isLeaf) {
                for(int i = node.size; i >= 0; i--) {
                    brother.insertBNode(node.children[i], 0);
                }
            }
            parent.deleteNum(parent.nums[index]);
            parent.deleteNode(index);
        }
        return parent;
    }

}
