package org.lights1eep.tree.huffmantree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 哈夫曼树
 * @author lights1eep
 */
public class HuffmanTree {
    /**
     * 根节点
     */
    private HuffmanNode root;
    /**
     * 哈夫曼编码字符串
     */
    private String s;
    /**
     * 哈夫曼编码
     */
    private Map<Character, String> huffmanCodeMap;

    public HuffmanTree(String s) {
        this.s = s;
        this.root = null;
        huffmanCodeMap = new HashMap<>();
        initHuffmanTree();
        getHuffmanCode(this.root, "");
    }

    /**
     * 初始化哈夫曼树
     */
    private void initHuffmanTree() {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = this.s.toCharArray();
        for(char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        while(!queue.isEmpty()) {
            HuffmanNode node1 = queue.poll();
            if(queue.isEmpty()) {
                this.root = node1;
                return;
            }
            HuffmanNode node2 = queue.poll();
            // 用 ! 代表非叶子节点
            HuffmanNode node = new HuffmanNode('!', node1.getNum() + node2.getNum(), node1, node2);
            queue.offer(node);
        }
    }

    /**
     * 获取哈夫曼编码
     * @param node  节点
     * @param huffmanCode   哈夫曼编码
     */
    private void getHuffmanCode(HuffmanNode node, String huffmanCode) {
        HuffmanNode left = node.getLeft();
        HuffmanNode right = node.getRight();
        if(node.getC() != '!') {
            this.huffmanCodeMap.put(node.getC(), huffmanCode);
        } else {
            getHuffmanCode(left, huffmanCode + "0");
            getHuffmanCode(right, huffmanCode + "1");
        }
    }

    /**
     * 根据 c 获取哈夫曼编码
     * @param c 字符
     * @return  哈夫曼编码
     */
    public String getHuffmanCodeByChar(char c) {
        return this.huffmanCodeMap.getOrDefault(c, "");
    }

}
