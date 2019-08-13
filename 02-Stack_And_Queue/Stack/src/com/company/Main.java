package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    /*//测试ArrayStack
        ArrayStack<Integer> stack = new ArrayStack<>();

        for (int i = 0 ; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);

        Integer pop = stack.pop();//获取一个元素

        System.out.println(pop);

        System.out.println(stack);

        Integer peek = stack.peek();//查看栈顶
        System.out.println(peek);*/

	    //测试链表栈与数组栈的效率
        int opCount = 1000000;

        LinkedListStack<Integer> stack1 = new LinkedListStack<>();
        double time = testStack(stack1, opCount);
        ArrayStack<Integer> stack2 = new ArrayStack<>();
        double time2 = testStack(stack2, opCount);
        System.out.println(time);
        System.out.println(time2);


    }

    //定义一个方法测试程序运行的时间
    private static double testStack(Stack<Integer> stack , int opCount) {
        //1.定义开始时间
        long start = System.currentTimeMillis();

        //2.定义需要执行的程序
        Random random = new Random();//创建随机对象
        for (int i = 0; i < opCount ; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount ; i++) {
            stack.pop();
        }

        //3.定义结束时间
        long end = System.currentTimeMillis();
        return (end - start) / 1000;
    }




}
