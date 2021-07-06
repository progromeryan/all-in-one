给一个 String of words. 每个单词可能是被空格，逗号，分号等给分割开，让你找到第一个 重复的词

```java
public String findFirstDuplicate(String words) {
    String[] splits = words.split("[,;.]");
    Set<String> seen = new HashSet<>();

    for(String word : splits) {
        if (word.equals("")) continue;
        if(seen.contains(word)) {
            return word;
        }

        seen.add(word);
    }

    return "";
}
```
