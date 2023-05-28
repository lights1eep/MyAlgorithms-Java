package org.lights1eep.tree.trie;

/**
 * 字典树
 * @author lights1eep
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * 插入字符串
     * @param s 字符串
     */
    public void insert(String s) {
        TrieNode node = this.root;
        char[] chars = s.toCharArray();
        for(char c : chars) {
            if(node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
    }

    /**
     * 查找字符串
     * @param word 字符串
     * @return true 在 false 不在
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * 查找是否存在该前缀的字符串
     * @param prefix    前缀
     * @return  true 在 false 不在
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     * 查找前缀结尾节点
     * @param prefix 前缀
     * @return 前缀结尾节点
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = this.root;
        char[] chars = prefix.toCharArray();
        for(char c : chars) {
            if(node.children[c - 'a'] == null) {
                return null;
            }
            node = node.children[c - 'a'];
        }
        return node;
    }
}
