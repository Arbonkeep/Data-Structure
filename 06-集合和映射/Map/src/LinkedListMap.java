import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K,V> {
    //定义节点类的实现
    private class Node {
        public K key;
        public V value;
        private Node next;

        //构造
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        //一个参数的构造
        public Node(K key) {
            this(key,null,null);
        }

        //无参构造
        public Node() {
            this(null, null, null );
        }

        //toString
        public String toString() {
            return key.toString() + ":" + value.toString();
        }

    }

    private Node dummyHead;
    private int size;

    //定义一个构造
    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    //定义一个私有辅助类(获取当前节点)
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while(cur != null) {//判断是否到达链表结尾
            if(cur.key.equals(key)) {//如果当前节点的key等于传入的key
                return cur;//直接将当前节点返回
            }
            cur = cur.next;//否则就将当前节点的值赋为下一个节点
        }
        return  null;//如果配不成功就直接返回null
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;//如果在获取节点时，等于null说明不包含key，不等于null说明包含key
    }

    @Override
    public V get(K key) {//通过键获取值
        Node node = getNode(key);//获取key对应的节点
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value) {
        //1.通过对应的键获取对应的节点
        Node node = getNode(key);
        //2.判断
        if(node == null) {//如果里面每没有对应的键的节点，那么就新创建一个节点添加进去
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else {//如果里面有，就将原来的key对应的value修改为传入的value
            node.value = value;
        }
    }

    @Override
    public void set(K key, V newValue) {
        //1.通过键获取到对应的节点
        Node node = getNode(key);
        //2.判断
        if(node == null) {
            throw new IllegalArgumentException(key + "不存在");
        }else {
            node.value = newValue;
        }

    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while(prev.next != null) {
            if(prev.next.key.equals(key)) {//如果满足就说明是我们需要删除的那个节点
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null) {//执行删除逻辑
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;

    }


    //定义主方法紧进行测试
    public static void main(String[] args) {
        System.out.println("a.txt");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\06-集合和映射\\Map\\src\\a.txt",words)){
            System.out.println("total words" + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if(map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                }else {
                    map.add(word, 1);
                }
            }

            System.out.println("total difenrent words : " + map.getSize());
            System.out.println("you的出现次数 ：" + map.get("you"));

        }
    }

}
