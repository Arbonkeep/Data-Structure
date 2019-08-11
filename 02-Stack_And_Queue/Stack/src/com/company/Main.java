package com.company;

public class Main {

    public static void main(String[] args) {
	    //测试ArrayStack
        ArrayStack<Integer> stack = new ArrayStack<>();

        for (int i = 0 ; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);

        Integer pop = stack.pop();//获取一个元素

        System.out.println(pop);

        System.out.println(stack);

        Integer peek = stack.peek();//查看栈顶
        System.out.println(peek);

    }
}
