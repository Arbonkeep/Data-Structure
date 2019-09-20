import java.util.TreeMap;

public class Trie {

    private class Node {
        private boolean isWord;//定义一个成员变量表示是否为单词的结尾（是否为一个完整单词）

        private TreeMap<Character, Node> next;//到下一个节点的映射

        //定义有参构造
        public Node(Boolean isword) {
            this.isWord = isword;
            next = new TreeMap<>();
        }

        //定义无参构造
        public Node() {
            this(false);
        }
    }

    //定义Trie类的成员变量
    private Node root;//定义Trie树的根节点

    private int size;//trie的长度

    public Trie() {
        root = new Node();
        size = 0;
    }

    //获得Trie树中的存储单词的数量
    public int getSize() {
        return size;
    }

    //在trie中添加一个字符串单词word
    public void add(String word) {
        Node cur = root;//trie树的起始节点为根节点root

        for (int i = 0; i < word.length() ; i++) {
            char c = word.charAt(i);//获取到字符串中的每一个字符
            if (cur.next.get(c) == null) {//如果满足字符c不存在trie树中
                cur.next.put(c,new Node());//就将c添加到相应的trie树的节点上
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {//如果当前节点不是单词的末尾
            cur.isWord = true;
            size++;
        }
    }

    //查询trie中是否包含单词word`
    public boolean contains(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);

            if (cur.next.get(c) == null) {
                return false;
            }

            cur = cur.next.get(c);
        }

        return cur.isWord;//最后返回的是isword，这是
    }

    //查询在Trie中是否有单词以prefix为前缀

    public boolean isPrefix(String prefix) {
        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
