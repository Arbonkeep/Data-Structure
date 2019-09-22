/**
 * 实现并查集的第二种方式（快合并）
 */
public class UnionFind2 implements UF{
    //声明成员变量
    private int[] parent;

    //声明构造方法
    public UnionFind2(int size) {
        parent = new int[size];

        for (int i = 0; i < parent.length ; i++) {
           parent[i] = i;
        }
    }

    //获取size的方法
    @Override
    public int getSize() {
        return parent.length;
    }

    //查找元素p所对应的集合编号
    //O(h)的复杂度，h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 索引越界");
        }
        //这里应该是索引与类型的映射，也就是查看元素对应的根节点是什么
        while( p != parent[p]) {
            p = parent[p];
        }
        return p;

        /*
        此方法就是从当前节点开始向根节点查找，直到当前节点与当前节点的根节点相等（说明该节点为根节点）
        如果不是根节点的话就让该节点一直向根节点去查找
         */
    }

    @Override
    //查看pq对应的集合类型是否一致（查看元素P与元素q是否属于一个集合）
    //O(h)的复杂度，h为树的高度
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    //合并p元素与q元素对应的集合
    public void unionElements(int p, int q) {
        int pRoot = parent[p];//获取p的根节点
        int qRoot = parent[q];//获取q的根节点

        if (pRoot != qRoot) {   //如果p的根节点与q的根节点不一致，索说明需要进行合并
            parent[pRoot] = qRoot;//相当于让p所在元素的根节点指向了q元素所在的根节点
        }else {
            return;
        }
    }



}
