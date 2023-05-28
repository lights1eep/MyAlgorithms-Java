package org.lights1eep.tree.hashtree;

/**
 * 哈希树
 * @author lights1eep
 */
public class HashTree {
    /**
     * 质数数组
     */
    private final int[] hashValues = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    /**
     * 根节点
     */
    private HashNode root;

    public HashTree() {
        this.root = new HashNode(-1, 2);
    }

    /**
     * 插入 num
     * @param num 数字
     */
    public void insertNum(int num) {
        HashNode node = this.root;
        for(int i = 0; i < this.hashValues.length; i++) {
            int num1 = num % this.hashValues[i];
            if(node.children[num1] != null) {
                if(!node.isFlag()) {
                    node.setNum(num);
                    node.setFlag(true);
                } else {
                    node = node.children[num1];
                }
            } else {
                node.children[num1] = new HashNode(num, this.hashValues[i + 1]);
                break;
            }
        }
    }

    /**
     * 判断 num 是否在哈希树中
     * @param num 数字
     * @return  true 在 false 不在
     */
    public boolean isExist(int num) {
        HashNode node = this.root;
        boolean isExist = false;
        for(int i = 0; i < this.hashValues.length; i++) {
            int num1 = num % this.hashValues[i];
            if(node.children[num1] != null) {
                if(node.children[num1].isFlag() && node.children[num1].getNum() == num) {
                    return true;
                } else {
                    node = node.children[num1];
                }
            } else {
                break;
            }
        }
        return isExist;
    }

    /**
     * 删除 num
     * @param num 数字
     */
    public void deleteNum(int num) {
        HashNode node = this.root;
        for(int i = 0; i < this.hashValues.length; i++) {
            int num1 = num % this.hashValues[i];
            if(node.children[num1] != null) {
                if(node.children[num1].isFlag() && node.children[num1].getNum() == num) {
                    node.children[num1].setFlag(false);
                    return;
                } else {
                    node = node.children[num1];
                }
            } else {
                break;
            }
        }
    }
}
