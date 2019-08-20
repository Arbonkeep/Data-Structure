import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 获取数组中的交集，包含重复元素
 */
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        //使用映射完成(第一个Integer代表元素，第二个代表其出现的次数)
        TreeMap<Integer,Integer> map = new TreeMap<>();
        //1.遍历数组nums1,并将nums1添加到map中
        for(int num : nums1) {
            if(map.containsKey(num)) {
                map.put(num,map.get(num) + 1);
            }else {
                map.put(num,1);
            }
        }

        //2.定义一个集合用于存储交集
        ArrayList<Integer> list = new ArrayList<>();
        //3.遍历nums2
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0 ) {//如果map中有num
                list.add(num);
                map.put(num,map.get(num) -1);
            }
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size() ; i++) {
            arr[i] = list.get(i);
        }
        return arr;

    }


}
