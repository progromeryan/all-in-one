给定一个 list, 找到第一个大于给定 word 的 word

{hello, cow, cat, dog}, 找到 cat 的下一个字典顺序大的 word

```java
class Solution {
    public String findNext(String[] words, String target) {
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            set.add(word);
        }

        return set.higher(target);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String res = solution.findNext(new String[]{"ff", "b", "cd", "a"}, "b");
        System.out.println(res);
    }
}
```

O(nlogn)
fetch O(lgn)

Trie

build O(nlogn) + O(m \* n)
search O(m), it is fast when there are a lot of words

```java
class TrieNextWord {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        String word;
        String nextWord;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
            word = "";
            nextWord = "";
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here. nlogn
     */
    public TrieNextWord(String[] words) {
        root = new TrieNode();
        Arrays.sort(words);
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i < words.length - 1 ? words[i + 1] : "");
        }
    }


    /**
     * Inserts a word into the trie.
     */
    public void insert(String word, String nextWord) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;
        node.nextWord = nextWord;
    }

    /**
     * Returns if the word is in the trie.
     */
    public String findNext(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) return null;
            node = node.children[index];
        }

        return node.nextWord;
    }

    public static void main(String[] args) {
        TrieNextWord trie = new TrieNextWord(new String[]{"ff", "b", "cd", "a", "aa"});
        String res = trie.findNext("a");
        System.out.println(res);
    }
}
```
