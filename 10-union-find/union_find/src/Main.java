import java.util.Random;

/**
 * 在main方法中定义方法对UnionFind1与UnionFinde2进行测试
 */

public class Main {

    public static void main(String[] args) {
	//在主方法中进行测试
        int size = 100000;
        int m = 100000;//操作数
//
//        UnionFind1 uf1 = new UnionFind1(size);
//        double time1 = testUF(uf1, m);
//
//        System.out.println("UnionFind1的执行时间为：" + time1 + "s");
//
//        UnionFind2 uf2 = new UnionFind2(size);
//        double time2 = testUF(uf2, m);
//        System.out.println("UnionFind2的执行时间为：" + time2 + "s");

        /*
         在size与m比较小的情况下，UnionFind2比较快，但是在size与m较大的情况下，UnionFind1
         的执行速度比较快，这是因为
            <1> 系统本身的底层执行的原因
            <2> 在UnionFind2中，查找元素p所对应的集合编号的方法与查看元素P与元素q是否属于一个集合
                的方法都是O(h)的时间复杂读，而在UnionFind2元素q是否属于一个集合的方法是O(1)的时间
                复杂度
         */

        UnionFind3 uf3 = new UnionFind3(size);
        double time3 = testUF(uf3, m);
        System.out.println("UnionFind3的执行时间为：" + time3 + "s");

        //测试UnionFind4
        UnionFind4 uf4 = new UnionFind4(size);

        double time4 = testUF(uf4, m);

        System.out.println("UnionFind4的执行时间为：" + time4 + "s");

        //测试UnionFind5
        UnionFind5 uf5 = new UnionFind5(size);

        double time5 = testUF(uf5, m);

        System.out.println("UnionFind5的执行时间为" + time5 + "s");

        //测试UnionFind6
        UnionFind6 uf6= new UnionFind6(size);

        double time6 = testUF(uf6, m);

        System.out.println("UnionFind6的执行时间为" + time6 + "s");



    }


    //声明一个测试方法
    public static double testUF(UF uf, int m) {
        int size = uf.getSize();//获取传入uf的实现类的size
        Random random = new Random();//生成一个随机函数

        long start = System.currentTimeMillis();

        //定义执行程序(查看pq类型是否一致)
        for (int i = 0; i < m ; i++) {
            int a = random.nextInt(size);//随机生成0-size的随机整数
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        //定义执行程序(将pq的类型进行合并)
        for (int i = 0; i < m; i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        long end = System.currentTimeMillis();

        return (end - start) / 1000.0;
    }
}
