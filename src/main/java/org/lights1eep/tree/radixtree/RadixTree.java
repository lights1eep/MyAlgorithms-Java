package org.lights1eep.tree.radixtree;

/**
 * 基数树
 * @author lights1eep
 */
public class RadixTree {
    /**
     * 根节点
     */
    private RadixNode root;

    public RadixTree() {
        this.root = new RadixNode();
    }

    /**
     * 插入数字
     * @param num 数字
     */
    public void insertNum(int num) {
        RadixNode node = this.root;
        int num1 = num;
        for(int i = 15; i >= 0; i--) {
            int tmp = num1 / (int)Math.pow(4, i);
            num1 = num % (int)Math.pow(4, i);
            if(node.children[tmp] == null) {
                node.children[tmp] = new RadixNode();
            }
            node = node.children[tmp];
        }
        node.setLeaf(true);
        node.setNum(num);
    }

    /**
     * 判断数字是否存在树中
     * @param num 数字
     * @return true 在 false 不在
     */
    public boolean isExist(int num) {
        RadixNode node = this.root;
        int num1 = num;
        for(int i = 15; i >= 0; i--) {
            int tmp = num1 / (int)Math.pow(4, i);
            num1 = num % (int)Math.pow(4, i);
            if(node.children[tmp] != null) {
                node = node.children[tmp];
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除数字
     * @param num 数字
     */
    public void deleteNum(int num) {
        RadixNode node = this.root;
        RadixNode pre = null;
        int tmp = 0;
        int num1 = num;
        for(int i = 15; i >= 0; i--) {
            tmp = num1 / (int)Math.pow(4, i);
            num1 = num % (int)Math.pow(4, i);
            if(node.children[tmp] != null) {
                pre = node;
                node = node.children[tmp];
            } else {
                return;
            }
        }
        pre.children[tmp] = null;
    }
}
