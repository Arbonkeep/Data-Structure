/**
 * 通过数组实现堆
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;
    private int size;

    public MaxHeap(int size) {
        data = new Array<>(size);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    //定义构造方法实现heapify
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        //实现siftDown的交换逻辑(找到最后一个非叶子节点，然后向前遍历)
        for (int i = parentIndex(arr.length - 1) ; i >= 0 ; i--) {
            siftDown(i);//对每个i元素都进行siftDown
        }
    }

    //返回堆中元素个数
    public int getSize() {
        return data.getSize();
    }
    //判断堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }
    //返回完全二叉树的数组中一个索引所表示的元素的父亲节点的索引
    public int parentIndex(int index) {
        if(index == 0) {
            throw new IllegalArgumentException("该索引没有父亲节点");
        }
        return (index - 1) / 2;
    }

    //返回完全二叉树中一个索引所表示的元素的左子节点的索引
    public int leftIndex(int index) {
        return (index * 2) + 1;
    }

    //返回完全二叉树中一个索引所表示的元素的右子节点的索引
    public int rightIndex(int index) {
        return (index * 2) + 2;
    }

    //向堆中添加一个元素
    public void add(E e) {
        //1.在数组末尾添加元素（这是因为通过数组实现的完全二叉树）
        data.addLast(e);
        //2.实现比较的方法SiftUp，比较父节点与添加元素的大小
        siftUp(data.getSize() -1);//将添加元素的索引传入
    }

    //定义方法SiftUp
    private void siftUp(int index) {
        //由于添加的元素需要与多层的父节点比较，使用循环来完成(同时传入的索引不能为根节点)
        while(index > 0 && data.get(parentIndex(index)).compareTo(data.get(index)) < 0) {
            //如果添加元素的值大于该值的父节点对应的值，就需要交换位置
            data.swap(parentIndex(index),index);//在数组类中来实现交换的方法,将两者的索引传入比较
            index = parentIndex(index);//交换后父节点的元素就变成了添加元素的值
        }
    }

    //看堆中的最大元素
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("堆中没有元素");
        }
        return data.get(0);//最大值就是最顶层的元素（即数组第一个元素）
    }

    //取出堆中最大元素，执行删除操作
    public E extractMax() {
        //1.定一个变量记录最大值
        E ret = findMax();
        //2.将最大值与最后一个元素进行交换
        data.swap(0,data.getSize() - 1);
        //3.将最后一个元素删除（此时最后一个元素为找到的最大值）
        data.removeLast();
        //4.删除之后，堆结构可能发生改变，需要进行下沉操作
        siftDown(0);
        //5.将删除的元素返回
        return ret;
    }


    //定义方siftDown
    private void siftDown(int k) {
        //1. 通过循环来完成操作

        while(leftIndex(k) < data.getSize()) {
            int x = leftIndex(k);
            if(x + 1 < data.getSize() && data.get(x + 1).compareTo(data.get(x)) > 0) {
                x = rightIndex(k);
            }
            //此时x记录的一定是左右中较大的那个

            if(data.get(k).compareTo(data.get(x)) >= 0) {
                break;
            }
            data.swap(k, x);
            k = x;
        }
    }


    //取出堆中最大的元素，并替换为元素e

    public E replace(E e) {
        //1.将返回的元素记录一下
        E ret = findMax();
        //2.将最大元素设置为e
        data.set(0, e);
        //3.执行siftdown操作
        siftDown(0);
        return ret;
    }





}
