package com.company;

/**
 * 创建一个链表类
 */
public class LinkedList<E> {
    //定义一个节点内部类
    private class Node {
        private E e;//元素
        private Node next;//指向下一个元素的引用

        //有两个参构造方法
        public Node(E e,Node next) {
            this.e = e;
            this.next = next;
        }

        //有一个参构造
        public Node(E e) {
            this(e,null);
        }

        //无参构造
        public Node() {
            this(null,null);
        }

        //重写toString方法
        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;//设置虚拟头结点
    private int size;//size

    //构造方法
    public LinkedList() {
        dummyHead = new Node(null,null);
        size = 0;
    }

    //判断链表中的元素个数
    public int getSize() {
        return size;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    //在索引index位置添加元素
    public void add(int index,E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("非法索引");
        }
        /*
        if (index == 0) {//在链表头添加元素
            addFirst(e);
        }else {
            Node prev = head;
            for (int i = 0; i < index -1 ; i ++) {
                prev = prev.next;
            }
            Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;
            //prev.next = new Node(e,prev.next);
             size++;
        }*/
        //在设置虚拟有节点后不需要对在head上添加元素进行处理了
        Node prev = dummyHead;
        for (int i = 0; i < index; i ++) {
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);
        size++;
    }

    //向链表头添加元素
    public void addFirst(E e) {
       /* Node node = new Node(e);
        node.next = dummyHead;
        dummyHead = node;//让head等于node

        //head = new Node(e,head);

        size++;*/
       //复用add实现
        add(0,e);
    }

    //向链表的末尾添加一个元素
    public void addLast(E e) {
        add(size,e);
    }

    //获取第index位置的元素
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引非法");
        }
        //遍历链表
        Node cur = dummyHead.next;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    //获取链表第一个元素
    public E getFirst() {
        return get(0);
    }

    //获取链表最后一个元素
    public E getLast() {
        return get(size -1 );
    }

    //修改链表中第index位置的元素
    public void set(int index,E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引非法");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    //查找元素中是否存在元素e
    public boolean comtains(E e) {
        Node cur = dummyHead.next;
        while(cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }else {
                cur = cur.next;
            }
        }
        return false;
    }

    //在链表中删除元素
    public E remove(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("索引非法");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index ; i ++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;//这里prev.next直接跨过了retNode，指向retNode.next
        retNode.next = null;
        size--;

        return retNode.e;
    }

    //删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    //删除最后一个元素
    public E removeLast() {
        return remove(size -1);
    }


    //重写toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null) {
            sb.append(cur + "->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();

    }


}
