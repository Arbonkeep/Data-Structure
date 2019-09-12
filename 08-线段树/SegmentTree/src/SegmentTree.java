public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merge<E> merge;

    //定义构造函数
    public SegmentTree(E[] arr, Merge<E> merge) {
        this.merge = merge;

        data = (E[])new Object[arr.length];

        for (int i = 0; i < arr.length ; i++) {
            data[i] = arr[i];
        }

        tree = (E [])new Object[4 * arr.length];
        //根据分析我们通过满二叉树实现线段树，那么最少需要4倍的data.length即可存储所有

        createSegment(0, 0, data.length -1 );//创建线段树(线段树的起始节点为0)
    }

    //声明在treeIndex的位置创建表示区间[l,...,r]的线段树
    private void createSegment(int treeIndex, int l, int r) {
        if (l == r) {//说明区间中只有一个元素
            tree[treeIndex] = data[l];
            return;
        }else {//区间包含多个元素
            //获取左右子树索引
            int leftTreeIndex = left(treeIndex);
            int rightTreeIndex = right(treeIndex);
            //获取中间的索引
            int mid = l + (r - l) / 2;

            //递归调用
            createSegment(leftTreeIndex, l, mid);
            createSegment(rightTreeIndex, mid + 1, r);

            //我们利用多态，定义接口以实现多功能的线段树（融合线段树左右对应的值）
            tree[treeIndex] = merge.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }
    }

    //获取数组长度
    public int getSize() {
        return data.length;
    }

    //获取数组中元素
    public E get(int index) {
        if (index < 0 || index >= data.length ) {
            throw new IllegalArgumentException("索引非法");
        }

        return data[index];
    }

    //返回区间[QueryL,QueryR]的值
    public E Query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||queryR < 0
                || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("索引非法");
        }

        return query(0, 0, data.length - 1 , queryL, queryR);//从根开始查询
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //查找的区间刚好为线段树的区间
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        //获取中间索引
        int mid = l + (r - l) / 2;
        //获取左右子树的索引
        int leftTreeIndex = left(treeIndex);
        int rightTreeIndex = right(treeIndex);

        if (queryR <= mid) {//查找的区间刚好全在左子树区间(查询区间最右边的数小于中间值说明查询内容全在左边v)
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }else if (queryL >= mid + 1) {//查找的区间刚好在右子树区间
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }else {//查找的区间在左右子树的区间都有
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

            return merge.merge(leftResult, rightResult);
        }
    }


    //在以treeID为根的线段树中[l,r]的范围里，搜索区间[queryL,queryR]的值

    //获取线段树中左子树的索引(返回完全二叉树的数组表示中，一个索引所表示元素的左孩子节点的索引)
    public int left(int index) {
        return 2 * index + 1;
    }

    //获取线段树右子树的索引(返回完全二叉树的数组表示中，一个索引所表示元素的右孩子节点的索引)
    public int right(int index) {
        return 2 * index + 2;
    }

    //将index位置的值更新为e
    public void set(int index , E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("索引越界");
        }
        data[index] = e;

        set(0, 0, data.length -1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e ) {
        if (l == r) {
            tree[index] = e;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = left(treeIndex);
        int rightTreeIndex = right(treeIndex);

        if (index <= mid) {
            set(leftTreeIndex, l, mid, index, e);
        }else {
            set(rightTreeIndex, mid + 1, r, index, e);
        }

        //由于修改了Index的元素，所以我们需要多整体的结果进行维护
        tree[treeIndex] = merge.merge(tree[leftTreeIndex],tree[rightTreeIndex]);

    }



    //重写toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            }else {
                sb.append("null");
            }

            if (i != tree.length - 1 ) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
