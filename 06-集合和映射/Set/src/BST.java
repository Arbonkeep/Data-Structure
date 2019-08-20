import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 定义一个二分搜索树类
 * @param <E>
 */
public class BST<E extends Comparable<E>> {//二分搜索树必须要有可比较性，需要实现comparable接口
    //定义内部类Node
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    //定义成员变量
    private Node root;//根节点
    private int size;

    //构造方法
    public BST() {
        root = null;
        size = 0;
    }

    //获取size
    public int size() {
        return size;
    }

    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

/*    //在二分搜索树中添加元素
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size ++;
        }else {
            add(root,e);
        }
    }

    private void add(Node node, E e) {
        if (e.equals(node.e)) {//判断传入的元素是否与根节点的元素相等
            return;
        }else if(e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size ++;
            return;
        }else if(e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if(e.compareTo(node.e) < 0) {
            add(node.left,e);
        }else {
            add(node.right,e);
        }
    }*/

    //改进后的添加元素的方法
    public void add(E e) {
        root = add(root, e);
    }

    //向以node为根的二分搜索树中插入元素e
    //返回插入新节点后二分搜索树的根
    private Node add(Node node , E e) {
        if(node == null) {//如果跟为空，那么就直接级将新的节点插入
            size ++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0) {
            node.left = add(node.left,e);
        }else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right,e);
        }
        return node;
    }

    //定义二分搜索树的查询是否包含元素的方法
    public boolean contains(E e) {
        return contains(root ,e);
    }

    private boolean contains(Node node , E e) {
        if(node == null) {//如果为空，返回false
            return false;
        }

        if(e.compareTo(node.e) == 0) {//说明根节点就是该元素
            return true;
        }else if(e.compareTo(node.e) < 0) {//如果e小于根节点就到左边查找
            return contains(node.left,e);
        }else {                            //如果e大于根节点就到右边查找
            return contains(node.right,e);
        }
    }


    //二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if(node == null) {//如果二叉树为null，不需要遍历（终止条件）
            return ;
        }

        System.out.println(node.e);//将元素打印
        preOrder(node.left);
        preOrder(node.right);

    }

    //二分搜索树非递归前序遍历
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            //由于栈先进后出的特点需要先将右子树压入栈中
            if(cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null) {
                stack.push(cur.left);
            }
        }
    }



    //二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    //递归实现中序遍历
    private void inOrder(Node node) {
        if (node == null) {
            return ;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    //递归实现后序遍历
    private void postOrder(Node node ) {
        if (node == null) {
            return ;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //二分搜索树的层序遍历
    public void levelOrder() {
        //1.创建队列对象
        Queue<Node> queue = new LinkedList<>();
        //2.将根节点添加到队列
        queue.add(root);
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    //定义二分搜索树中寻找最小元素的方法
    public E minmum() {
        if(size == 0) {
            throw new IllegalArgumentException("二分树为空");
        }
        return minmum(root).e;
    }

    //返回以node为根的二分搜索树最小值所在节点
    private Node minmum(Node node) {
        if(node.left == null) {//终止条件
            return node;
        }

        return minmum(node.left);//递归调用
    }

    //定义二分搜索树寻找最大元素
    public E maxmum() {
        if (size == 0) {
            throw new IllegalArgumentException("二分树为空");
        }
        return maxmum(root).e;
    }

    //返回以node为根的二分搜索树最小值所在节点
    private Node maxmum(Node node) {
        if(node.right == null) {
            return node;
        }
        return maxmum(node.right);
    }

    //从二分搜索树中删除最小值所在的节点
    public E removeMin() {
        E ret = minmum();//将最小值存储起来
        root = removeMin(root);//返回的根节点给root赋值
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if(node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    //从二分搜索树中删除最小值所在的节点
    public E removeMax() {
        E ret = maxmum();//将最大值存起来
        root = removeMax(root);//调用函数进行删除
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最大节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node ) {
        if(node.right == null) {
            Node leftNode = node.left;//将左子树存储
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    //从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    //删除以node为根的二分搜索树中值为e的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if(node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e) > 0) {//删除的元素e比node.e大
            node.right = remove(node.right,e);
            return node;
        }else  {    //(e.compareTo(node.e) == 0)说明删除的e就是节点e
            if(node.left == null) {//待删除节点的左子树为null
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if (node.right == null) {//待删除节点的右子树为null
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //待删除节点的左右子树均不为null
            //找到比待删除节点大的最小节点，也就是待删除节点右子树中的最小节点
            //用这个节点顶替待删除节点的位置

            //1.找到那个比待删除节点大的最小节点
            Node successor = minmum(node.right);
            //2.设置successor的右子树，右子树需要删除node.right中最小节点
            successor.right = removeMin(node.right);
            //3.设置successor的左子树
            successor.left = node.left;

            node.left = node.right = null;

            return successor;

        }
    }



    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);//根节点，深度，sb
        return sb.toString();
    }

    //生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }

        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);

    }

    //定义描述深度的方法
    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth ; i++) {
            sb.append("--");
        }
        return sb.toString();
    }

}
