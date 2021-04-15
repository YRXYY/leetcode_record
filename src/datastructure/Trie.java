package datastructure;

/**
 * prefix tree 前缀树
 * @author 少司礼
 */
public class Trie {
    private Trie[] children;
    private Boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] arr = word.toCharArray();
        Trie node = this;
        for (char c : arr) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie res = searchPrefix(word);
        return res != null && res.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie res = searchPrefix(prefix);
        return res != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        char[] arr = prefix.toCharArray();
        for (char c : arr) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */