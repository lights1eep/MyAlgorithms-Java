package org.lights1eep.tree.hashtree;

/**
 * 哈希树节点
 * @author lights1eep
 */
public class HashNode {
    int num;
    /**
     * 标志位 表示该节点是否有值
     */
    boolean flag;
    HashNode[] children;

    public HashNode(int num, int size) {
        this.num = num;
        this.flag = true;
        this.children = new HashNode[size];
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
