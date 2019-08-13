package com.company;

/**
 * 利用链表实现栈
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    //定义构造方法
    public LinkedListStack() {
        list = new LinkedList<>();
    }

    //重写方法
    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack:top");
        sb.append(list);
        return sb.toString();

    }

    //测试链表实现栈的类

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 5 ; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }


}
