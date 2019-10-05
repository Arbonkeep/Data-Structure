package hashtable;

import sun.awt.geom.AreaOp;

import java.util.TreeMap;

/**
 * 在前面，我们实现了一个2*素数的hashtable，这里我们改进一下，使用全素数来实现hashtable
 */
public class HashTable<K, V> {
    //声明成员变量
    public TreeMap<K, V>[] hashTable;
    public int M;
    public int size;

    //声明一个数组（存储所有素数）
    public final int[] capacity =
            {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
             49157, 98317, 196613, 392241, 786443, 1572869, 3145739, 6291469,
             12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457,
             1610612741};

    //声明上下界限，以及初始容量
    public static final int upperTol = 10;
    public static final int lowerTol = 2;
    public int capacityIndex = 0 ;//设置初始的容量

    //构造方法(只需要提供无参构造)
    public HashTable() {
        this.M = capacity[capacityIndex];//初始容量为53
        size = 0;
        hashTable = new TreeMap[M];//创建初始容量为53的哈希表
        for (int i = 0; i < size ; i++) {
            //为每一个数组中的元素进行赋值
            hashTable[i] = new TreeMap<>();
        }
    }

   //声明一个辅助函数hash
   public int hash(K key) {
        return (key.hashCode() & 0x7fffffff) / M;//这就相当于将32位二进制中的符号位去除
   }

   public int getSize() {
        return size;
   }

   //add
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];//获取到对应key所在哈希表中元素（TreeMap）

        if (map.containsKey(key)) {//如果包含key就直接修改对应的value
            map.put(key, value);
        }else {//如果不包含，那么就直接添加，添加之后需要维护一下size
            map.put(key, value);
            size++;
        }
        //进行扩容
        //由于一直执行capacityIndex一直进行++，可能会导致索引越界，所以进行capacityIndex + 1
        // < capacity.length判断
        if (size > upperTol * M && capacityIndex + 1 < capacity.length){
            //如果满足上面条件进行扩容，后面是为了防止索引越界
            capacityIndex++;//扩容就是将对应存储素数表的索引向后移动一位
            resize(capacity[capacityIndex]);
        }

    }

    //delete
    public V delete(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;//设置一个局部变量用于返回

        if (map.containsKey(key)){
            ret = map.remove(key);//删除值将返回的结果赋值给ret
            size--;

            //缩容(与扩容同理)
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }

        return ret;
    }

    //set
    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "不存在");
        }else {
            map.put(key, value);
        }
    }

    //contains
    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    //get
    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    private void resize(int newM) {
        //1.创建一个新的hashTable
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        //2.初始化新的hashTable中的每一个元素
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        //3.记录一下容量
        int oldM = M;
        this.M = newM;

        //4.遍历，将hashTable中的元素添加到newHashTable
        for (int i = 0; i < oldM; i++) {
            //4.1暂存哈希表中的元素
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet()) {//遍历获取map中每一个key
                newHashTable[hash(key)].put(key,map.get(key)); //然后将原来每一个K，V添加到新的哈希表中
            }
            this.hashTable = newHashTable;//将原来hashTable的引用指向newHashTable
        }
    }


}
