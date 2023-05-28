package org.lights1eep.tree.cluetree;

/**
 * 线索二叉树
 * @author lights1eep
 */
public class ClueTree {
    /**
     * 遍历的前一个节点
     */
    public ClueNode pre;
    /**
     * 使用递归并根据数组初始化树 数组中-1表示空节点
     * @param nums  数组
     * @param index 数组索引
     * @return  构建的节点
     */
    public ClueNode initTree(int[] nums, int index) {
        if(index >= nums.length) {
            return null;
        }
        if(nums[index] == -1) {
            return null;
        }
        ClueNode node = new ClueNode(nums[index]);
        node.setLeft(initTree(nums, index * 2 + 1));
        node.setRight(initTree(nums, index * 2 + 2));
        return node;
    }

    /**
     * 初始化中序线索二叉树
     * @param cur 二叉树节点
     */
    public void initMidOrderClueTree(ClueNode cur) {
        if(cur != null) {
            ClueNode left = cur.getLeft();
            initMidOrderClueTree(left);

            System.out.printf("%d ", cur.getData());

            // cur 的左孩子为空
            if(left == null) {
                cur.setLeft(pre);
                cur.setLeftFlag(1);
            }
            // pre 的右孩子为空
            if(pre != null && pre.getRight() == null) {
                pre.setRight(cur);
                pre.setRightFlag(1);
            }
            pre = cur;

            ClueNode right = cur.getRight();
            initMidOrderClueTree(right);
        }
    }

    /**
     * 遍历中序二叉线索树 从根节点开始遍历
     * @param node 根节点
     */
    public void traverseMidOrderClueTree(ClueNode node) {
        while(node != null) {
            while(node.getLeftFlag() == 0) {
                node = node.getLeft();
            }
            System.out.printf("%d ", node.getData());
            while(node.getRightFlag() == 1) {
                node = node.getRight();
                System.out.printf("%d ", node.getData());
            }
            node = node.getRight();
        }
    }

    /**
     * 遍历中序二叉线索树 从中序序列第一个节点开始遍历
     * @param node 中序序列第一个节点
     */
    public void traverseMidOrderClueTree2(ClueNode node) {
        ClueNode nextClueNode = getNextClueNode(node);
        while(nextClueNode != null) {
            System.out.printf("%d ", nextClueNode.getData());
            nextClueNode = getNextClueNode(nextClueNode);
        }
    }

    /**
     * 获取二叉线索树节点的下一个节点
     * @param node 节点
     * @return  下一个节点
     */
    public ClueNode getNextClueNode(ClueNode node) {
        if(node.getRightFlag() == 0) {
            node = node.getRight();
            while(node != null && node.getLeftFlag() == 0) {
                node = node.getLeft();
            }
            return node;
        }
        return node.getRight();
    }
    /**
     * 获取二叉线索树节点的上一个节点
     * @param node 节点
     * @return  上一个节点
     */
    public ClueNode getPreClueNode(ClueNode node) {
        if(node.getLeftFlag() == 0) {
            node = node.getLeft();
            while(node != null && node.getRightFlag() == 0) {
                node = node.getRight();
            }
            return node;
        }
        return node.getLeft();
    }
}
