package com.company;

/**
 *定义一个栈的接口，用于实现ArrayStack
 */
public interface Stack<E> {
    public void push(E e);

    public E pop();

    public E peek();

    public int getSize();

    public boolean isEmpty();
}
