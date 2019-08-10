package com.company;

public class Main {

    public static void main(String[] args) {

        /*//动态初始化数组
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = i;
            System.out.println(arr[i]);
        }

        System.out.println("------------------------");

        //静态初始化数组
        int[] arr2 = new int[]{111,222,333};
        for (int i = 0; i <arr2.length; i ++) {
            System.out.println(arr2[i]);
        }

        System.out.println("------------------------");
        //对arr2中的第二个元素进行重新赋值
        arr2[1] = 444;

        //利用foreach的方式进行遍历arr2
        for (int i : arr2) {
            System.out.println(i);
        }*/

        //创建自定义的数组对象，测试里面添加的方法
        Array<Integer> arr = new Array<>(20);//指定数组容量为20
        for (int i = 0; i < 10; i++) {
           arr.addLast(i);
        }

        System.out.println(arr);//需要重写toString方法

        //测试之前设置的方法
        arr.add(5,20);
        System.out.println(arr);

        arr.addFirst(111);
        System.out.println(arr);

        //测试通过索引获取元素的方法
        int i = arr.get(5);
        System.out.println(i);

        //测试设置元素值的方法
        arr.set(5,12);
        System.out.println(arr);

        //测试查找元素的方法
        int index = arr.find(0);
        System.out.println(index);

        //测试contains方法
        boolean b = arr.contains(200);
        System.out.println(b);

        //测试删除指定元素的方法
        int ele1 = arr.remove(0);
        System.out.println(ele1);
        System.out.println(arr);

        arr.removeLast();
        System.out.println(arr);

        //测试删除指定元素的方法
        arr.removeElement(8);
        System.out.println(arr);

        //测试删除第一个元素的方法
        Integer first = arr.removeFirst();
        System.out.println(first);

    }
}
