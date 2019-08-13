
/**
 * 定义一个通过链表来实现队列的类，这里不使用自定义的类
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {


    //这里需要使用节点，定义一个内部类Node
    private class Node {
        private E e;
        private Node next;

        public Node(E e,Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node() {
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head,tail;
    private int size;

    //定义有参构造
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    //重写Queue方法
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head =tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw  new IllegalArgumentException("队列为空");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return head.e;
    }

    @Override
    public  String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue:front");

        Node cur = head;
        while(cur != null) {
            sb.append(cur + "->");
            cur = cur.next;
        }
        sb.append("NULL tail");
        return sb.toString();
    }


    //定义测试的主方法
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10 ; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }

}
