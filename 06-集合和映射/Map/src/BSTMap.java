import java.util.ArrayList;

public class BSTMap<K extends Comparable, V> implements Map<K,V> {
    //定义节点类
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
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

    //向二分搜索树中添加元素
    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    //向以node为根的二分搜索树插入元素（key,value），递归
    //返回插入新节点后的根
    private Node add(Node node , K key, V value) {
        if(node == null) {
            size ++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }else if(key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else {//key.compareTo(node.key) == 0
            node.value = value;
        }

        return node;
    }

    //返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if(node == null) {
            return null;
        }
        if(key.compareTo(node.key) == 0) {
            return node;
        }else if(key.compareTo(node.key) < 0) {
            return getNode(node.left,key);
        }else {
            return getNode(node.right,key);
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root,key);
        if(node == null) {
            throw new IllegalArgumentException(key + "不存在");
        }
        node.value = value;
    }

    //获取最小值节点的方法
    private Node minimum(Node node) {
        if(node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //删除掉以node为节点的二分搜索树的最小节点
    //返回删除节点后新的二分搜索树的根节点
    public Node removeMin(Node node) {
        if(node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override//实现删除函数需要借助几个辅助函数
    public V remove(K key) {
        Node node = getNode(root, key);//获取需要删除的那个节点
        if(node == null) {//如果这个节点为null
            return null;//直接返回null
        }
        root = remove(root,key);//如果节点不为空的话，就进行递归调用
        return node.value;//将node的value返回
    }

    //删除掉以node为根的二分搜索树中键为（key）的节点，递归
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node , K key) {
        if(node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
            return node;
        }else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            return node;
        }else {//待删除节点就是该节点
            //待删除节点没有左子树的情况
            if(node.left == null) {
                Node rightNode = node.right;//将右子树保存下来
                node.right = null;
                size--;
                return rightNode;
            }else if(node.right == null) {
                Node leftNode = node.left;//将左子树保存下来
                node.left = null;
                size--;
                return leftNode;
            }else {//就是左右子树都有的情况
                Node successor = minimum(node.right);//找到右子树中的最小节点
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;
                return successor;

            }
        }

    }

    //定义main函数进行测试
    public static void main(String[] args) {
        System.out.println("a.txt");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\06-集合和映射\\Map\\src\\a.txt",words)) {
            System.out.println("总单词数" + words.size());

            BSTMap<String,Integer> map = new BSTMap<>();
            for(String word : words) {
                if(map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                }else  {
                    map.add(word,1);
                }
            }
            System.out.println("不相同的总单词数为：" + map.getSize());
            System.out.println("you出现的次数：" + map.get("you"));
        }
    }

}
