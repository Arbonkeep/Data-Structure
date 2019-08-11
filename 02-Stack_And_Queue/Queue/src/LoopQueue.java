/**
 * 循环队列的实现类
 *      通过自己类来实现，不用定义的动态数组类
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;//定义一个数组成员变量
    private int front;
    private int tail;
    private int size;

    //定义有参构造方法
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];//容量加1是因为在队列中会有一个空位
        front = 0;
        tail = 0;
        size = 0;
    }

    //定义无参构造,默认长度为10个容量
    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        //1.对数组进行判断是否存满，
        if ((tail + 1) % data.length == front ) {//表示存满了
            resize(getCapacity() * 2);//扩容原来的两倍
        }
        data[tail] = e;//添加的元素为data[tail]
        tail = (tail + 1) % data.length;
        size++;
    }

    //定义扩容的方法resize,定义私有方法不让外界来访问
    private void resize(int newCapacity) {
        //1.创建一个新的数组
        E[] newData = (E[]) new Object[newCapacity + 1];
        //2.遍历原来的数组，将元素添加到新的数组
        for (int i = 0; i < size ; i++) {
            newData[i] = data[(i + front) % data.length];//%用于循环
        }
        data = newData; //将data引用指向newData
        front = 0;
        tail = size;

    }

    @Override
    public E dequeue() {
        //1.判断队列是否为空
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        //2.将队首元素保存一下，删除元素时删除队首元素并返回
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        //3.当size小于capacity/4时，需要进行缩容到原来的一半
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        //1.判断队列是否为空
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        //2.返回队首元素
        return data[front];
    }

    //重写toString方法
    @Override
    public String toString() {
        //1.创建StringBuilder对象
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("loopQueue:size = %d, capacity = %d\n", size, getCapacity()));
        sb.append("front[");
        //2.循环遍历队列(循环条件本来是i++,但是因为循环所以应该如下写法)
        for (int i = front; i != tail ; i = (i + 1) % data.length) {//循环队列中第一个元素为front，最后一个元素为tail - 1
            sb.append(data[i]);//将元素添加到sb
            if ((i + 1) % data.length != tail) {//如果不是最后一个元素
                sb.append(", ");
            }else {
                sb.append("]tail");
            }
        }


        return sb.toString();
    }

    //定义一个测试函数
    public static void main(String[] args) {
        //1.创建循环队列对象
        LoopQueue<Integer> queue = new LoopQueue<>();
        //2.遍历循环
        for(int i = 0; i < 10 ; i ++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
