import java.util.*;

public class Solution {
    public List<Integer> topFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //遍历数组
        for (int num : nums ) {
            if (map.containsKey(num)) {
                map.put(num , map.get(num) + 1);
            }else {
                map.put(num, 1);
            }
        }

        //创建优先队列对象
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        //遍历map
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            }else if(map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        //创建List集合，用于返回
        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.add(pq.remove());
        }

        return res;

    }

}
