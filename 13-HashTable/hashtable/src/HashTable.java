import java.util.TreeMap;

/**
 * 实现一个自定义的HashTable
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> {
    //声明成员变量（哈希数组、存储的容量、存储的个数）
    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    private static final int upperTol = 10;//设置上界限为10，用于扩容缩容
    private static final int lowerTol = 2;//设置下界限为2
    private static final int initcapacity = 7; //将初始的容量设置为7

    //声明构造方法
    public HashTable(int M) {
        this.M = M;//初始化容量
        size = 0;
        hashTable = new TreeMap[M];//创建存储M个TreeMap的数组
        for (int i = 0; i < M ; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    //声明一个无参构造
    public HashTable() {
        this(initcapacity);//指定容量为97
    }

    //声明一个辅助函数hash
    public int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;//经过这样处理，那么就将二进制32位最高位符号位去除
    }

    public int getSize() {
        return size;
    }

    //add,向哈希表中添加k,v值
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];//获取到对应key的在数组中具体的TreeMap，并暂存起来

        if (map.containsKey(key)) {//如果数组对应的map已经有对应的key
            map.put(key, value);//直接修改值即可
        }else {//如果在数组对应的map中没有对应的key
            map.put(key, value);//直接添加，添加了之后就需要维护size
            size++;

            //满足条件就进行扩容
            if (size >= upperTol * M ) {//如果满足实际元素个数大于这个
                resize(2 * M);//扩容
            }
        }
    }

    //remove,在哈希表中删除一个K，并将对应的V返回
    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;//暂存一下V，用于返回
        if (map.containsKey(key)) {//如果包含这个key
            ret = map.remove(key);//进行删除操作，之后维护size
            size--;
            if (size < lowerTol && M/2 >= initcapacity) {
                resize(M/2);
            }
        }
        return ret;
    }

    //set,将哈希表中某个K，对应的V修改成value
    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "不存在");
        }else {
            map.put(key, value);
        }
    }

    //contains,判断是否包含key
    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);//直接判断数组中对应的key是否有传入的key
    }

    //get,通过K 获取V
    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    //声明扩容的方法
    private void resize(int newM) {
        //1.创建一个新的数组
        TreeMap<K, V> [] newHashTable = new TreeMap[newM];
        //2.初始化数组中的元素
        for (int i = 0; i < newM ; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        //3.记录M
        int oldM = M;
        this.M = newM;

        //4.遍历将hashTabl中的元素移到newHashTable中
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];//将元素暂存到map中，然后遍历赋值给新的哈希表
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
                //这里hash(key)中需要用到M，所以在前面需要将M指定为newM
            }

            this.hashTable = newHashTable;
        }
    }
}
