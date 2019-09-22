public class UnionFind1 implements UF{

    //声明成员变量
    private int[] id;

    //声明构造方法
    public UnionFind1(int size) {
        id = new int[size];//初始化一个长度为size的数组

        for (int i = 0; i < id.length; i ++ ) {//这里我们设置成i种不同的类型
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    //查找元素p所对应的集合编号
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p 索引越界了");
        }

        return id[p];
    }

    //查看pq对应的集合类型是否一致
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //对p，q两个元素对应的集合进行合并
    @Override
    public void unionElements(int p, int q) {
        //获取到元素pq所对应的集合编号
        int pID = find(p);
        int qID = find(q);

        //判断是否已经是同一类
        if (id[p] == id[q]) {
            return;
        }

        //如果不是同一类，那么就进行合并操作
        for (int i = 0; i < id.length ; i++) {
            if (id[i] == pID) {//如果是pID，那么就将其转换为qID-
                id[i] = qID;
            }
        }


    }


}
