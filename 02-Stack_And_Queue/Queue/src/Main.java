import java.util.Random;

/**
 * 定义比较数组队列与循环队列的比较的方法
 */
public class Main {

    public static void main(String[] args) {
        //1.定义一个变量
        int opCount = 100000;
        //2.分别创建queue的两个子类对象调用方法进行测试
        ArrayQueue<Integer> queue1 = new ArrayQueue<>();
        double time1 = testQueue(queue1, opCount);
        System.out.println(time1);

        LoopQueue<Integer> queue2 = new LoopQueue<>();
        double time2 = testQueue(queue2, opCount);
        System.out.println(time2);

        LinkedListQueue<Integer> queue3 = new LinkedListQueue<>();
        double time3 = testQueue(queue3, opCount);
        System.out.println(time3);
    }

    //定义一个测试两种运行时间的方法
    private static double  testQueue(Queue<Integer> q,int opCount) {
        //1.定义一个起始时间
        long start =  System.currentTimeMillis();

        //2.定义需要测试的程序内容
        //2.1创建random对象，随机生成元素添加到队列中
        Random random = new Random();
        //2.2随机生成0到最大的Integer类型的数,添加到队列中
        for (int i = 0; i < opCount ; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        //2.3将队列中的元素移出队列
        for (int i = 0; i <opCount ; i++) {
            q.dequeue();
        }

        //3.定义一个结束时间
        long end = System.currentTimeMillis();
        return (double) ((end - start) / 1000.0);
    }
}
