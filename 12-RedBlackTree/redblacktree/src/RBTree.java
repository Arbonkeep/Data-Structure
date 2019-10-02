import com.sun.javafx.collections.ElementObservableListDecorator;
import com.sun.org.apache.regexp.internal.RE;

public class RBTree <K extends Comparable<K>, V> {
    //声明静态常量
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    //声明节点类
    private class Node {
        public K key;
        public V value;
        public Node left,right;
        public Boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    //声明成员变量
    private int size;
    private Node root;

    //声明构造方法
    public RBTree() {
        root = null;
        size = 0;
    }

    //获取size
    public int getSize() {
        return size;
    }

    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //声明一个辅助函数，获取color(判断节点node的颜色)
    public boolean isRed(Node node) {
        if (node == null) {//如果为空就是black
            return BLACK;
        }else {
            return node.color;
        }
    }

    //传入一个节点，将该节点进行左旋转
    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;//记录一下x节点（旋转之后该节点作为根节点返回）

        node.right = x.left;//将x的左子树作为node的右子树
        x.left = node;//将node作为x的左子树

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //传入一个节点，将该节点进行右旋转
    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    public Node rightRotate(Node node) {
        Node x = node.left;//记录x

        node.left = x.right;//将x的右子树作为node的左子树
        x.right = node;//将node作为x的右子树

        x.color = node.color;
        node.color = RED;//这是因为旋转后三个节点之间还是（2-3树中4-节点的关系）

        return x;
    }

    //颜色翻转
    public void fildColor(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //声明添加元素的方法
    public void add(K key, V value) {
        root = add(root, key, value);
        //在添加了一个元素之后需要维护红黑树根节点的颜色
        root.color = BLACK;
    }
    //向以node为根的红黑树中插入元素（key,value）
    //并将插入新元素后的红黑树的根返回
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);//这时返回的根节点为red，在构造方法中指明
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else {//key.compareTo(node.key) == 0
            node.value = value;
        }

        //对红黑树进行维护(根据md文件中的笔记对其进行维护)
        if (isRed(node.right) && isRed(node.left)) {//如果节点的左右子节点都是红色
            fildColor(node);//对node进行颜色翻转
        }

        if (isRed(node.left) && isRed(node.left.left)) {//如果节点的左子节点与左子节点的子节点都为红色
            node = rightRotate(node);//对node节点进行右旋转，并将返回的节点赋值给传入的node
        }

        if (isRed(node.right) && !isRed(node.left)) {//如果节点的左子节点不是红色，而右子节点为红色
            node = leftRotate(node);//对node节点进行左旋转，并将返回的节点赋值给传入的node
        }

        return node;

        //注意：在这里，我们并没有对上面三种情况进行互斥的判断（没有else），这是由于我们需要进行逐一判断，有可能
        //存在我们在md笔记中讲述的第三种情况（添加的元素在node.left.right），那么只需要一次进行左旋转，右旋转，
        //颜色翻转即可。如果满足上面的判断条件就会一次执行
    }

    //返回以node为根节点的红黑树中，key所在的节点
    public Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        }else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        }else {
            return getNode(node.right, key);
        }
    }

    //传入key判断是否包含
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    //通过key获取value
    public V get(K key) {
        Node node = getNode(root, key);//获取到对应的node
        return node == null ? null : node.value;
    }

    //设置key处value的值
    public void set(K key, V newValue) {
        Node node = getNode(root, key);

        if (node == null) {
            throw new IllegalArgumentException("error");
        }
        node.value = newValue;
    }
}
