
/**
 * leetcode307问题使用线段树解决
 */
public class NumArray4 {
    private SegmentTree<Integer> segmentTree;

    public NumArray4(int[] nums) {
        if (nums.length > 0) {
            Integer[] arr = new Integer[nums.length];
            for (int i = 0; i < arr.length; i ++) {
                arr[i] = nums[i];
            }

            segmentTree = new SegmentTree<>(arr, (a, b) -> a + b);
        }
    }

    public void set(int index, int e) {
        segmentTree.set(index, e);
    }

    public int SumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("segmentTree is null");
        }

        return segmentTree.Query(i, j);
    }

}
