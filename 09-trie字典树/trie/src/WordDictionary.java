import java.util.TreeMap;

/**
 * leetcode中211号问题的解决方案
 * 添加与搜索单词-数据结构设计
 *
 */
public class WordDictionary {
    //声明一个内部节点类
    private class Node {
        private boolean isword;

        private TreeMap<Character, Node> next;

        public Node(boolean isword) {
            this.isword = isword;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    //声明成员变量root
    private Node root;

    //构造方法
    public WordDictionary() {
        root = new Node();
    }

    //添加一个元素的方法
    public void addWord(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);//获取到word每一个字符

            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
            cur.isword = true;
    }

    //查询word是否为一个单词的方法
    public boolean search(String word) {
        //调用私有方法match实现查询
        return match(root,word, 0);
    }

    //声明判断是否为word的方法（递归实现）
    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {//这是递归到底的情况，将所有字符都进行了匹配
            return node.isword;
        }
        //获取到所有字符
        char c = word.charAt(index);
        if (c != '.') {//如果c不为一个.的情况
            if (node.next.get(c) == null) {//如果c不在trie中就返回false
                return false;
            }else {
                return match(node.next.get(c), word, index + 1);
            }
        }else {//如果c为一个.的情况
            for (char nextChar : node.next.keySet()) {//获取到所有键的值
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

}
