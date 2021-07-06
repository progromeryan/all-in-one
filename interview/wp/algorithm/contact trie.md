给定一个通讯录, 输入前几个字母, 就可以显示左右以这几个字母开头的联系人列表

Trie

```java
class Contact {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
            word = "";
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Contact(String[] contacts) {
        root = new TrieNode();
        for (int i = 0; i < contacts.length; i++) {
            insert(contacts[i]);
        }
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.word = word;
        node.isWord = true;
    }

    public List<String> findPrefixWords(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (node.children[index] == null) return new ArrayList<>();
            node = node.children[index];
        }

        List<String> res = find(node);
        if (node.isWord) {
            res.add(prefix);
        }

        return res;
    }

    private List<String> find(TrieNode curr) {
        if (curr == null) return null;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (curr.children[i] == null) continue;
            // next level
            List<String> sub = find(curr.children[i]);
            if (sub != null) {
                list.addAll(sub);
            }
            // curr level
            if (curr.children[i].isWord) {
                list.add(curr.children[i].word);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        Contact contact = new Contact(new String[]{"ff", "b", "cd", "a", "aa", "apple"});
        List<String> res = contact.findPrefixWords("a");
        System.out.println(res);
    }
}
```

time: search O(26^M)
