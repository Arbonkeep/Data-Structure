import java.util.ArrayList;

/**
 * 实现一个二分搜索树类(通过映射实现)
 */
public class BST<K extends Comparable<K>, V>{
    //声明一个节点类
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    //声明二分搜索树的成员变量
    public Node root;
    public int size;

    //声明构造方法
    public BST() {
        root = null;
        size = 0;
    }

    //声明返回size的方法
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //向二分搜索树中添加元素(key,value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向二分搜索树中添加元素，并返回插入新元素后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) { //递归到底的情况
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else {//key.compareTo(node.key) == 0
            node.value = value;
        }

        return node;
    }

    //返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        }else if(key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        }else {//key.compareTo(node.key) > 0
            return getNode(node.right, key);
        }
    }

    //判断是否包含的方法
    public boolean contains(K key) {
        return getNode(root, key) != null;
        //只要返回key所在的节点不为null，就说明包含，如果为null说明没有对应的节点，即不包含该元素
    }

    //声明通过对应的K获取对应的V的方法
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    //声明set方法，将指定的V设置在K处
    public void set(K key, V value) {
        Node node = getNode(root, key);

        if (node == null) {
            throw new IllegalArgumentException("key is no exsit");
        }
        node.value = value;
    }

    //返回以node为根二分搜索树中最小值的节点
    public  Node minimum(Node node) {
        if (node.left == null){
            return node;
        }else {
            return minimum(node.left);
        }
    }

    //删除以node为根的二分搜索树中最小值的节点
    //返回删除最小值后新的二分搜索树的根
    public Node removeMin(Node node) {
        if (node.left == null) {        //递归到底的情况
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    //从二分搜索树中删除键位key的节点，并将对应节点的value返回
    public V remove(K key) {
        //1.获取对应键的根节点
        Node node = getNode(root, key);

        //2.进行判断
        if (node != null) {//如果节点不为空，辣么就进行递归调用
            root = remove(root,key);
            return node.value;
        }else {//否则直接返回null
            return null;
        }
    }

    //声明私有的remove，底层实现删除逻辑
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {//需要删除的元素在左边
            node.left = remove(node.left, key);
            return node;
        }else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        }else {//key.compareTo(node.key) == 0  也就是说需要删除的节点就是该节点

            if (node.left == null) {//如果该节点的左子树为空的情况

                Node rightNode = node.right;//记录一下右子树
                node.right = null;
                size--;
                return rightNode;

            }else if (node.right == null) {//如果该节点的右子树为空的情况

                Node leftNode = node.left;//将左子树记录一下
                node.left = null;
                size--;
                return leftNode;

            }else {//如果左右子树都不为空的情况

                //找到比待删除节点大的最小节点（待删除节点右子树中最小节点）
                //将找到的这个节点与要删除节点进行替换
                Node sucessor = minimum(node.right);
                sucessor.right = removeMin(node.right);
                sucessor.left = node.left;

                node.left = node.right = null;

                return sucessor;
            }
        }
    }

    //声明一个main方法
    public static void main(String[] args) {
        System.out.println("a.txt");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\11-AVL\\AVLTree\\src\\a.txt", words)) {
            System.out.println("Total words: " + words.size());

            BST<String, Integer> map = new BST<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of worst: " + map.get("worst"));
            System.out.println("Frequency of all: " + map.get("all"));
        }

        System.out.println();
    }

}
