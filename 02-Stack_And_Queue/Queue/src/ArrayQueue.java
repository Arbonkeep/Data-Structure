/**
 * 数组队列实现类
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E>{
    private Array<E> array;

    //定义有参构造函数
    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    //定义无参构造函数
    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {//添加一个元素
        array.addLast(e);
    }

    @Override
    public E dequeue() {//删除一个元素
        return array.removeFirst();
    }

    @Override
    public E getFront() {//查看队首元素
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue:");//表示队列
        sb.append("front [");
        for (int i = 0; i < array.getSize() ; i++) {
            sb.append(array.get(i));//将每一元素添加到sb
            if (i != array.getSize() - 1 ) {
                sb.append(", ");
            }else {
                sb.append("]tail");
            }
        }
        return sb.toString();
    }


    //创建有一main函数测试
    public static void main(String[] args) {
        //创建数组对列对象
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        //通过循环向队列中添加元素
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
