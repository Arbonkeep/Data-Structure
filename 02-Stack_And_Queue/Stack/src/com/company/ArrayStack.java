package com.company;

/**
 * 定义一个实现类的ArrayStack（这里通过java的Stack来实现）
 */
public class ArrayStack<E> implements Stack<E>{

    private Array<E> array;

    public ArrayStack() {
        array = new Array();
    }

    public ArrayStack(int capacity) {
        array = new Array(capacity);
    }


    //实现添加的方法
    @Override
    public void push(E e) {//添加一个元素根据栈的结构，是在栈的最后添加一个元素
        array.addLast(e);
    }

    @Override
    public E pop() {//获取一个元素，相当于将数组中最后一个元素删除并返回
        return array.removeLast();
    }

    @Override
    public E peek() {//查看栈顶元素，相当于获取栈中最后一个元素
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getCapacity();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    //重写toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Stack:"));
        sb.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize()- 1) {
                sb.append(", ");
            }else {
                sb.append("]top");
            }
        }

        return sb.toString();
    }
}
