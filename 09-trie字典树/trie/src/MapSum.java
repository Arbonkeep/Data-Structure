import java.util.Map;
import java.util.TreeMap;

/**
 * 声明一个类用于解决leetcode中677号问题
 */
public class MapSum {
    //声明一个内部类Node
    private class Node {
        public int value;

        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    //声明构造方法
    public MapSum() {
        root = new Node();
    }

    //声明一个插入元素的方法
    public void insert(String word, int value) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        cur.value = value;
    }

    //声明一个求传入前缀参数prefix对应的value和的方法sum
    public int sum(String prefix) {
        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }
    //声明求和方法递归实现
    private int sum(Node node) {

//        if (node.next.size() == 0) {//递归到底
//            return node.value;
//        }

        int result = node.value;

        for (char c : node.next.keySet()) {
            result += sum(node.next.get(c));
        }

        return result;
    }


}
