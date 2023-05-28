package org.lights1eep.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 * @author lights1eep
 */
public class Tree {
    /**
     * 使用递归并根据数组初始化树 数组中-1表示空节点
     * @param nums  数组
     * @param index 数组索引
     * @return  构建的节点
     */
    public static Node initTree(int[] nums, int index) {
        if(index >= nums.length) {
            return null;
        }
        if(nums[index] == -1) {
            return null;
        }
        Node node = new Node(nums[index]);
        node.setLeft(initTree(nums, index * 2 + 1));
        node.setRight(initTree(nums, index * 2 + 2));
        return node;
    }

    /**
     * 层序遍历输出树
     * @param node  根节点
     */
    public static void printTree(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            Node tmp = queue.poll();
            System.out.print(" " + tmp.getData());
            if(tmp.getLeft() != null) {
                queue.offer(tmp.getLeft());
            }
            if(tmp.getRight() != null) {
                queue.offer(tmp.getRight());
            }
        }
    }

    /**
     * 递归 前序遍历
     * @param node 根节点
     */
    public static void preOrder1(Node node) {
        if(node == null) {
            return;
        }
        System.out.print(" " + node.getData());
        preOrder1(node.getLeft());
        preOrder1(node.getRight());
    }

    /**
     * 非递归 前序遍历
     * @param node 根节点
     */
    public static void preOrder2(Node node) {
        Deque<Node> stack = new ArrayDeque<>();
        while(node != null || !stack.isEmpty()) {
            // 一直访问左子树
            while(node != null) {
                System.out.print(" " + node.getData());
                stack.offerLast(node);
                node = node.getLeft();
            }
            // 左子树访问完，访问右子树
            if(!stack.isEmpty()) {
                node = stack.pollLast();
                node = node.getRight();
            }
        }
    }

    /**
     * 递归 中序遍历
     * @param node 根节点
     */
    public static void midOrder1(Node node) {
        if(node == null) {
            return;
        }
        midOrder1(node.getLeft());
        System.out.print(" " + node.getData());
        midOrder1(node.getRight());
    }

    /**
     * 非递归 中序遍历
     * @param node 根节点
     */
    public static void midOrder2(Node node) {
        Deque<Node> stack = new ArrayDeque<>();
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.offerLast(node);
                node = node.getLeft();
            }
            if(!stack.isEmpty()) {
                node = stack.pollLast();
                System.out.print(" " + node.getData());
                node = node.getRight();
            }
        }
    }
    /**
     * 递归 后序遍历
     * @param node 根节点
     */
    public static void postOrder1(Node node) {
        if(node == null) {
            return;
        }
        postOrder1(node.getLeft());
        postOrder1(node.getRight());
        System.out.print(" " + node.getData());
    }
    /**
     * 非递归 后序遍历
     * @param node 根节点
     */
    public static void postOrder2(Node node) {
        Deque<Node> stack = new ArrayDeque<>();
        Node pre = null;
        while(node != null || !stack.isEmpty()) {
            // 一直访问左子树
            while(node != null) {
                stack.offerLast(node);
                node = node.getLeft();
            }
            // 左子树访问完毕
            if(!stack.isEmpty()) {
                node = stack.pollLast();
                // 如果当前节点的右子树为空或已经访问过右子树 输出当前节点
                if(node.getRight() == null || pre == node.getRight()) {
                    System.out.print(" " + node.getData());
                    pre = node;
                    // 跳过访问左子树的循环
                    node = null;
                } else {
                    // 重新入栈，访问右子树
                    stack.offerLast(node);
                    node = node.getRight();
                }
            }
        }
    }

    /**
     * 层序遍历
     * @param node 根节点
     */
    public static void levelOrder(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            Node tmp = queue.poll();
            System.out.print(" " + tmp.getData());
            if(tmp.getLeft() != null) {
                queue.offer(tmp.getLeft());
            }
            if(tmp.getRight() != null) {
                queue.offer(tmp.getRight());
            }
        }
    }
}
