
import java.util.ArrayList;
import java.util.TreeSet;

public class Solution {

   public int[] intersection(int[] nums1, int[] nums2 ) {
        //使用集合来完成，由于需要去除重复，我们通过hashSet来实现
        TreeSet<Integer> set = new TreeSet<>();
        //1.对nums1进行遍历,将num添加进set中
        for (int num : nums1) {
            set.add(num);
        }
        //2.创建一个ArrayList，用于存储交集
        ArrayList<Integer> list = new ArrayList<>();
        //2.1遍历数组nums2
        for (int num :nums2) {
            if(set.contains(num)) {//如果set中也有这个值，就说明是交集
                list.add(num);//将num添加进集合中
                set.remove(num);//由于nums2中可能有重复的元素，所以通过这一步将其删除
            }
        }
        int[] arr = new int[list.size()];//定义一个int数组用于存储list集合中的元素
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);//将list中的元素赋值给arr
        }
        return arr;
    }

    /*public int[] intersection(int[] nums1, int[] nums2 ) {
        //使用集合来完成，由于需要去除重复，我们通过hashSet来实现
        TreeSet<Integer> set = new TreeSet<>();
        //1.对nums1进行遍历,将num添加进set中
        for (int num : nums1) {
            set.add(num);
        }
        //2.定义集合set2存储数组2
        TreeSet<Integer> set2 = new TreeSet<>();
        for (int num : nums2) {
            set2.add(num);
        }

        System.out.println( set );
        System.out.println( set2 );

        //定义一个数组存储交集
        ArrayList<Integer> list = new ArrayList<>();

        //3.遍历set进行判断
        int curIndex = 0;
        for(int i : set) {
            if(set2.contains(i)) {
                list.add(i);
                curIndex ++;
            }
        }

        int[] arr = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;

    }*/


}
