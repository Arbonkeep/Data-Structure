import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 针对437号问题的解决方案(通过自定义PriorityQueue实现)
 */
public class Solution {

    //定义内部类freq
    private class Freq implements Comparable<Freq> {
        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq anther) {
            if(this.freq < anther.freq) {
                return 1;
            }else if(this.freq > anther.freq) {
                return -1;
            }else {
                return  0;
            }
        }
    }

    public List<Integer> topFrequent(int[] nums , int k) {
        //1.创建映射
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //2.遍历数组，添加进映射
        for (int num : nums) {
            if(map.containsKey(num)) {
                map.put(num , map.get(num) + 1);
            }else {
                map.put(num,1);
            }
        }
        //3.创建优先队列,用于存储频次
        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for(int key : map.keySet()) {
            if(pq.getSize() < key) {
                pq.enQueue(new Freq(key, map.get(key)));
            }else if(map.get(key) > pq.getFront().freq) {//如果遍历到的key的频次比优先队列中优先级最高的频次大
                pq.deQueue();//那么执行一次出队操作
                pq.enQueue(new Freq(key, map.get(key)));//将key和相应的频次入队
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.add(pq.deQueue().e);
        }
        return  res;

    }
}
