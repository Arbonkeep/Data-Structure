import org.junit.Test;

/**
 * 解决leetcode303号问题（对指定数组中的区间进行求和）
 */
public class NumArray {
    private SegmentTree<Integer> segment;

    public NumArray(int[] nums) {
        if (nums.length < 0) {
            throw new IllegalArgumentException("error");
        }

        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = nums[i];
        }
        segment = new SegmentTree<>(arr, (a, b) -> a + b);
    }

    public int sumRange(int i, int j) {
        if (segment == null) {
            throw new IllegalArgumentException("segment is null");
        }
        return segment.Query(i, j);
    }
}