package org.lights1eep.tree.trie;

/**
 * 字典树节点
 * @author lights1eep
 */
public class TrieNode {
    /**
     * 字符串结尾标志
     */
    boolean isEnd;
    TrieNode[] children;

    public TrieNode() {
        this.isEnd = false;
        this.children = new TrieNode[26];
    }
}
