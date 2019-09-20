import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.WStringSeqHelper;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 定义一个二分搜索树类BST
 */
public class BST<E extends Comparable<E>> {

    /**
     * 声明一个节点类，用于表示二分搜索树的节点
     */
    private class Node {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    //声明成员变量
    private Node root;
    private int size;

    //声明二分搜索树的构造方法
    public BST() {
        this.root = null;
        size = 0;
    }

    //声明获取二分搜索树长度的方法
    public int getSize() {
        return size;
    }

    //声明判断二分搜索树是否为空的方法
    public boolean isEmpty() {
        return size == 0;
    }

    //声明二分搜索树添加元素的方法
    public void add(E e) {
        add(root, e);
    }

    //声明一个私有的向以node为根的二分搜索树中添加元素的方法，并将插入新节点后二分搜索树的根返回
    private Node add(Node node, E e) {
        if (node == null) {//如果二分搜索树为空，直接添加一个元素
            size++;//添加元素后维护size
            return new Node(e);//直接将根节点返回
        }

        if (e.compareTo(node.e) < 0) {//小于0的情况向左边添加元素
            node.left = add(node.left, e);
        }else if(e.compareTo(node.e) > 0){//大于0的情况就向右边添加元素
            node.right = add(node.right, e);
        }

        return node;//最后将添加元素后的根节点返回
    }

    //声明一个二分搜索树中查询的方法contains，查询是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //声明私有方法contains底层实现查询
    private boolean contains(Node node, E e) {
        if (node == null) {//如果节点==null,直接返回false即可
            return false;
        }

        if (e.compareTo(node.e) == 0) {//等于0，说明找到了，直接返回true
            return true;
        }else if (e.compareTo(node.e) < 0) {//如果小于0的情况，就向左边去查找
            return contains(node.left, e );
        }else{      // e.compareTo(node.e) > 0 如果大于0的情况，就往右边去查找
            return contains(node.right, e);
        }
    }

    //声明二分搜索树的前序遍历的方法
    public void preOrder() {
        preOrder(root);
    }

    //声明私有的前序遍历方法（递归实现）
    private void preOrder(Node node) {
        if (node == null) {//如果node == null，说明没有元素不需要遍历(遍历到底的情况)
            return;
        }

        System.out.println(node.e);//直接打印元素
        preOrder(node.left);
        preOrder(node.right);
    }

    //声明二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    //声明私有的中序遍历方法（递归实现）
    private void inOrder(Node node) {
        if (node == null) {//遍历到底的情况，直接return即可
            return ;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //声明二分搜索树中后续遍历方法
    public void postOrder() {
        postOrder(root);

    }
    //声明私有的二分搜索树的后续遍历的方法（递归实现）
    private void postOrder(Node node) {
        if (node == null) {//遍历到底的情况
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //声明层序遍历的方法
    public void levelOrder() {
        //1.利用队列实现
        Queue<Node> queue = new LinkedList<>();
        //2.将根节点添加进队列
        queue.add(root);
        //3.循环判断
        while(!queue.isEmpty()) {//如果队列不为空的时
            Node cur = queue.remove();//将队列中的元素删除并取出
            System.out.println(cur.e);//直接进行打印遍历

            //注意在这里需要从左到右的顺序进行入队
            if (cur.left != null) {//如果当前节点的左节点不为null
                queue.add(cur.left);//就键该左节点添加到队列中
            }

            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    //定义二分搜索树中寻找最小元素的方法
    public E minmum() {
        if (size == 0) {
            throw new IllegalArgumentException("二分搜索树中没有元素");
        }

        return minmum(root).e;
    }

    //声明私有的实现寻找最小元素的方法（返回以node为根的二分搜索树中最小值所在的节点）
    private Node minmum(Node node) {
        if (node.left == null) {//终止条件
            return node;
        }
        return minmum(node.left);
    }
    
    //定义二分搜索树中寻找最大元素的方法
    public E maxmum() {
        if (size == 0) {
            throw new IllegalArgumentException("二分搜索树中没有元素");
        }
        return maxmum(root).e;
    }

    //返回以node为根的二分搜索树中最小值所在的节点
    private Node maxmum(Node node) {
        if (node == null) {
            return node;
        }
        return maxmum(node.right);

    }

    //从二分搜索树中删除最小值所在的节点(并将最小值返回)
    public E removeMin() {
        //1.将最小值存储起来用于返回
        E ret = minmum();
        //2.删除以root为根的最小值，并将返回后的根节点给root赋值
        root = removeMin(root);
        //3.将最小值返回
        return ret;
    }

    //声明私有的删除二分搜索树中最小值的方法，并将其根节点返回(递归实现)
    private Node removeMin(Node node) {
        if (node.left == null) {//不能够直接找到最小值的情况
            Node rightNode = node.right;//将原来节点的右子树存储起来
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);//递归，并给node.left赋值
        return node;
    }

    //从二分搜索树中删除最大值所在的节点（并将最大值返回）
    public E removeMax() {
        //1.将最大值存储起来
        E ret = maxmum();
        //2.调用函数删除最大值,并赋值给root
        root = removeMax(root);
        //3.将最大值返回
        return ret;
    }
    //声明私有的删除二分搜索树中最大值的方法，并将其根节点返回(递归实现)
    private Node removeMax(Node node) {
        if (node.right == null) {//递归到底的情况
            Node leftNode = node.left;//将左子树存储起来
            node.left = null;//删除之后左子树赋为null，与node脱离关系
            size--;//维护size
            return leftNode;
        }

        node.right = removeMax(node.right);

        return node;
    }

    //从二分搜索树中删除元素为e的的节点
    public void remove(E e) {
        root = remove(root, e);
    }
    //声明私有的删除元素为e的节点，并将删除节点后的根返回
    private Node remove(Node node, E e) {
        if (node == null) {//如果node为空直接返回null，递归到底的情况
            return null;
        }

        if (e.compareTo(node.e) < 0) {//如果要删除元素在左子树
            node.left = remove(node.left,e);//递归删除
            return node;
        }else if(e.compareTo(node.e) > 0) {//需要删除的元素在右子树
            node.right = remove(node.right,e);//递归删除
            return node;
        }else {//需要删除的元素e刚好为节点e，

            if (node.left == null) {//删除节点e中的左子树为null的情况
                Node rightNode = node.right;//将右子树存储起来
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {//删除节点的右子树为null的情况
                Node leftNode = node.left;//将左子树存储起来
                node.left = null;
                size--;
                return leftNode;
            }

            //删除节点的左右子树都不为null的情况

            //1.寻找到比要删除节点要大的最小节点
            Node successor = minmum(node.right);//找到右子树中最小节点

            //2.设置successor的右子树，右子树需要删除node.right中的最小节点
            successor.right = removeMin(node.right);
            //size++;//与后面size--di抵消，这里++是由于在removeMin中--了

            //3.设置successor的左子树
            successor.left = node.left;

            node.left = node.right = null;//将左右子树脱离联系
            //size--;

            return successor;
        }
    }


    //重写toString方法
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }

        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);

    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth ; i++) {
            sb.append("--");
        }
        return sb.toString();
    }
}
