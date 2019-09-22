/**
 * 声明一个实现并查集的接口
 *      注意：这里我们不使用泛型，直接指定为int类型，这是因为我们通过使用数组的索引与元素间的
 *           映射关系来实现的
 */
public interface UF {
    //声明功能的主要方法
    boolean isConnected(int p, int q);

    void unionElements(int p, int q);

    int getSize();
}
