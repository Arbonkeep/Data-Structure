/**
 * 本类基于rank对UnionFind进行优化
 */
public class UnionFind4 implements UF {
    private int[] parent;

    private int[] rank;//rank[i]表示以i为根的集合所表示的树的层次（深度）

    //声明构造方法
    public UnionFind4(int size) {
        parent = new int[size];

        rank = new int[size];

        for (int i = 0; i < size ;i ++) {
            parent[i] = i;
            rank[i] = 1;//初始情况树的高度为1
        }
    }

    //声明获取size的方法
    @Override
    public int getSize() {
        return parent.length;
    }

    //声明查找元素p对应的集合编号的方法
    public int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 越界");
        }

        while(p != parent[p]) {
            p = parent[p];
        }

        return p;
    }

    //声明查看pq对应的集合类型是否一致的方法
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //声明将两个不同集合类型pq的集合进行合并的方法
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);//找到p的根节点
        int qRoot = find(q);//找到q的根节点

        if (pRoot == qRoot) {
            return;
        }

        //根据两个元素所在树的高度来判断如何合并
        //高度低的树合并到高度高的树
        if (rank[pRoot] < rank[qRoot]) {//如果pRoot的深度小于qRoot
            parent[pRoot] = qRoot;      //就将pRoot的树合并到qRoot

        }else if (rank[pRoot] > rank[qRoot]) {//如果pRoot的的深度大于qRoot
            parent[qRoot] = pRoot;            //就将qRoot合并到pRoot

        }else {//如果pRoot与qRoot的深度相等
            parent[qRoot] = pRoot;//这里可以任意进行合并（因为两棵树的深度一样）
            rank[pRoot] += 1;     //维护一下相关树（合并到的那棵树）的深度，深度会加1
        }
    }
}
