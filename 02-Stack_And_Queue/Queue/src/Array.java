public class Array<E> {
    private E[] data;//定义一个数组成员变量
    private int size;//定义数组的size实际长度


    //定义一个无参构造，默认数组容量为10
    public Array() {
        this(10);
    }

    //定义一个有参构造，传入数组容量capacity构造Array
    public Array(int capacity) {
        data =  (E[]) new Object[capacity];//此处不能够直接创建一个泛型为E的数组，可以通过将Object数组进行强转的方式实现
        size = 0;
    }

    //定义一个获取数组实际长度的方法,即获取数组中元素的个数
    public int getSize() {
        return size;
    }

    //定义一个获取数组容量的方法
    public int getCapacity() {
        return data.length;
    }

    //定义一个方法isEmpty判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //在数组的末尾添加一个元素
    public void addLast(E e) {
       /* if (size == data.length) {
            throw new ArrayIndexOutOfBoundsException("索引越界");
        }else {
            data[size] = e;
            size ++;//增加了一个元素所以size改变
        }*/

        //方法复用
        add(size,e);
    }

    //在指定位置添加一个元素
    public void add(int index, E e) {
        if(index < 0 || index > size) {//索引为负数或者索引大于真实元素个数（说明索引非法）
            throw new ArrayIndexOutOfBoundsException("数组索引越界");
        }

        if (size == data.length) {
           resize(2 * data.length);
        }

        for (int i = size - 1; i >= index ; i--) {//倒着遍历index之后的数组元素，将前后元素进行进行交换
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;

    }

    //定义一个扩容的方法.内部调用方法用private修饰。
    private void resize(int newCapacity) {
        //创建一个新数组
        E[] newData = (E[]) new Object[newCapacity];
        //遍历,将data中的元素转移到newData
        for (int i = 0; i < size ; i++) {
            newData[i] = data[i];
        }
        //将data的引用指向newData
        data = newData;
    }


    //定义一个在数组第一个索引添加元素的方法
    public void addFirst(E e) {
        add(0,e);
    }

    //重写toString方法
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array:size = %d , capacity = %d\n",size,data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if(i != size - 1) {
                sb.append(", ");
            }else {
                sb.append("]");
            }
        }
        return sb.toString();
    }

    //定义一个获取索引位置的元素的方法
    public E get(int index) {
        if (index < 0 || index >= size) {
           throw new ArrayIndexOutOfBoundsException("数组索引越界");
        }
        return data[index];
    }

    //定义一个获取第一个元素的方法
    public E getFirst() {
        return get(0);
    }

    //定义一个获取最后一个元素的方法
    public E getLast() {
        return get(getSize() - 1 );
    }

    //定义一个设置指定索引位置元素的方法
    public void set(int index ,E e) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("数组索引越界");
        }
        data[index] = e;

    }

    //添加判断是否包含指定元素的方法
    public boolean contains(E e) {
        boolean flag = true;
        for (int i = 0; i < size ; i++) {
            if (data[i] == e) {
                break;
            }else {
                flag = false;
            }
        }
        return flag;
    }

    //定义一个查找元素的方法，并返回对应的索引
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    //定义删除指定索引的元素的方法，并将该元素返回
    public E remove(int index) {
        if(index < 0 || index >= size) {//索引为负数或者索引大于真实元素个数（说明索引非法）
            throw new ArrayIndexOutOfBoundsException("数组索引越界");
        }

        E ele = data[index];
        for (int i = index + 1; i < size ; i++) {//从index之后的元素开始都让它向前移动一个位置
            data[i - 1] = data[i];
        }

        size--;
        data[size] = null;//将数组中的引用设置为null，这样能够被垃圾回收机制清理

        if(data.length == 4 * size) {//如果数组容量为2倍的元素的实际长度
             resize(data.length/2);
        }

        return ele;//最后将删除的元素返回
    }

    //定义删除第一个元素的方法
    public E removeFirst() {
        return remove(0);
    }

    //定义删除最后一个元素的方法
    public E removeLast() {
        return remove(size - 1);
    }

    //定义一个删除指定元素的方法
    public void removeElement(E e) {
        int index = find(e);//调用方法查看是否存在这个元素
        if(index != -1)
        remove(index);//调用之前定义的方法将该元素删除
    }




}
