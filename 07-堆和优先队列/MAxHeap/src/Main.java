import java.util.Random;

public class Main {

    public static void main(String[] args) {
	  /*  int x = 10000;
	    //1. 创建一个堆对象
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        //创建一个随机对象
        Random random = new Random();
        //2. 随机生成x个数，并将其添加到堆中
        for (int i = 0; i < x ; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        //3.获取出maxHeap中的每一个元素，并定义一个数组接收(此时数组中元素应该是从大到小排列)
        int[] arr = new int[x];
        for (int i = 0; i < x ; i++) {
            arr[i] = maxHeap.extractMax();
        }
        //4.遍历数组进行检验
        for (int i = 1; i < arr.length ; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("ERROR");
            }
        }
        System.out.println("test is completed");*/


	    int x = 1000000;
	    //1.创建一个随机对象
        Random random = new Random();

        //2.定义一个数组
        Integer[] testData = new Integer[x];
        for (int i = 0 ; i < x ; i ++ ) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        //3.调用方法进行测试
        double time1 = test(testData, true);
        System.out.println("heapify的运行时间为：" + time1 + "s");

        double time2 = test(testData, false);
        System.out.println("直接调用MaxHeap添加元素的方法的时间：" + time2 + "s");


    }

    //定义测试heapify的方法，用于比较性能
    public static double test(Integer[] testData , boolean isHeapify) {
        //1. 定义起始时间
        long start = System.currentTimeMillis();

        //2.定义执行程序
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        }else {
            maxHeap = new MaxHeap<>();
            for (int i = 0; i < testData.length ; i ++) {
                maxHeap.add(testData[i]);
            }
        }

        //测试一下堆是否正确
        int[] arr = new int[testData.length];

        for (int i = 0; i < testData.length ; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < testData.length ; i++) {
            if(arr[i -1] < arr[i]) {
                throw new IllegalArgumentException("堆出错");
            }
        }
        System.out.println("测试成功");

        //3.定义结束时间
        long end = System.currentTimeMillis();

        return (end - start) / 1000;
    }


}
