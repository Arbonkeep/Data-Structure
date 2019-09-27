import java.util.ArrayList;

/**
 * 实现一个AVLTree
 */
public class AVLTree<K extends Comparable<K>, V> {//由于需要比较那么就需要实现Comparable接口
    //定义是一个节点类
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;//声明一下树的高度

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;//在创建一棵树时，高度为1
        }
    }

    //声明成员变量
    private Node root;
    private int size;

    //声明构造方法
    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //声明一个获取树高度的辅助函数
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //声明一个获取树平衡因子的方法
    private int getBalanceFactor(Node node ) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //检验二叉树是否为一课二分搜索树
    public boolean isBST() {
        //声明一个集合，存储二分搜索树中序遍历的结果
        ArrayList<K> list = new ArrayList<>();

        //中序遍历
        inOrder(root,list);

        //进行判断
        for (int i = 1; i < list.size(); i ++) {
            if (list.get(i -1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    //声明中序遍历的方法辅助查询是否为一棵BST
    private void inOrder(Node node,ArrayList<K> list ) {
        if (node == null) {
            return ;
        }
        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
    }

    //声明检验二叉树是否为一棵平衡树的方法
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        //获取平衡因子
        int balanceFactor = getBalanceFactor(root);

        if (Math.abs(balanceFactor) > 1) {
            return false;
        }else {
            //return true;      //注意这里不能够直接返回true,需要递归判断子树
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }

    //声明右旋转的方法
    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;//记录一下旋转前的x
        Node T3 = x.right;

        //进行右旋转
        x.right = y;
        y.left = T3;

        //更新高度，注意这里需要优先更新y的高度，因为y的高度会影响到x的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;//将x返回，因为此时x已经是根节点
    }

    //声明左旋转的方法
    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        //进行左旋转
        x.left = y;
        y.right = T2;

        //更新高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    //向AVL树中添加元素
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {//递归到底的情况
            size++;
            return new Node(key, value);//由于是新建一棵树，所以在这里不再需要维护height
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;//key相等时，直接将值覆盖即可
        }

        //需要更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));//树的高度就是较高的子树加上1
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanceed:" + balanceFactor );
//        }

        //维护平衡
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //进行右旋转
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            //进行左旋转
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            //首先进行对x（node.left）进行左旋转,这样就会转换成LL
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            //首先对x(node.right)进行右旋转，然后会转换成RR
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //返回以node为根的二分搜索树中,key所对应的节点
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

    //判断二分搜索树中是否包含某节点
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    //通过key获取对应的value
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    //将key所在的值设置为newValue
    public void set(K key, V value) {
        Node node = getNode(root, key);

        if (node == null) {
            throw new IllegalArgumentException("key is no exsit");
        }
        node.value = value;
    }

    //返回以node为根二分搜索树最小值的节点
    public Node minimum(Node node) {
        if (node.left == null) {//如果左子树为空直接返回该节点
            return node;
        }else {
            return minimum(node.left);//递归
        }
    }

    //删除以node为根的二分搜索树中最小值的节点
    //返回删除最小值后新的二分搜索树的根
    public Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    //从二分搜索树中删除键位key的节点，并将对应节点的value返回
    public V remove(K key) {
        Node node = getNode(root, key);

        if (node == null) {
            return null;
        }else {
            root = remove(root, key);
            return node.value;
        }
    }

    //删除元素的具体实现
    private Node remove(Node node, K key) {
        if (node == null) {//如果node为null直接将null返回
            return null;
        }
        //声明一个retNode记录我们将要返回的node
        Node retNode;

        if (key.compareTo(node.key) < 0) {//如果传入的key比node.key小的话
            node.left = remove(node.left, key);//就到左子树去删除
            retNode = node;//将要返回的node赋值给retNode,这是因为node可能是不平衡的所以需要记录后面维护平衡

        }else if(key.compareTo(node.key) > 0) {//如果传入的key比node.key要大的话
            node.right = remove(node.right, key);//就到右子树去删除
            retNode = node;

        }else {//key.compareTo(node.key) == 0;

            if (node.right == null) {//如果要删除节点的右子树为null
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;//将需要返回的节点赋值给retNode

            }else if (node.left == null) {//如果要删除节点的左子树为null
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;//将需要返回的节点赋值给retNode

            }else {//如果需要删除的节点左右子树都不为null

                //此时，我们需要找到比要删除节点要大的节点中最小的那个节点，也就是右子树中的最小节点
                Node successor = minimum(node.right);//将右子树中个最小的节点记录起来
//                successor.right = removeMin(node.right);//返回的是最小节点的根
                /*
                    注意：在这里removeMin()这个方法并没有对二分搜索树进行维持平衡，那么我们有两种
                         方式解决这一问题：
                         <1>在removeMin方法中进行维持平衡操作

                         <2>如下方法：复用remove（递归），由于需要将node.right最小值删除，而该值存储在
                                    successor.key

                 */
                successor.right = remove(node.right,successor.key);//将node.right最小值删除
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (retNode == null) {//如果要返回的节点为null，就不需要维护平衡
            return null;
        }

        //更新高度
         retNode.height = 1 + Math.max(getHeight(retNode.right), getHeight(retNode.left));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //维护平衡

        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            //进行右旋转
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right ) <= 0) {
            //进行左旋转
            return leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);//转换成LL
            return rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right ) > 0) {
            retNode.right = rightRotate(retNode.right);//转换成RR
            return leftRotate(retNode);
        }

        return retNode;//不需要维护平衡直接返回
    }


    public static void main(String[] args) {
        System.out.println("a.txt");

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\11-AVL\\AVLTree\\src\\a.txt", words)) {
            System.out.println("总字数为：" + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                }else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of worst: " + map.get("worst"));
            System.out.println("Frequency of all: " + map.get("all"));

            System.out.println("is BST :" + map.isBST());
            System.out.println("is balanced:" + map.isBalanced());

            for (String word : words) {
                map.remove(word);
                if (!map.isBalanced() &&!map.isBST()) {
                    throw new IllegalArgumentException("error");
                }
            }


        }

    }

}
