/**
 * leetcode307问题（用sum数组预处理方案处理307问题出现超时，所以使用线段树）
 */
public class NumArray3 {
    private int[] sum;
    //sum[i]存储前i个元素和，sum[0] = 0（表示一个元素都没有时的和）
    //sum[i]存储nums[0...n-1]的和
    private int[] data;


    public NumArray3(int[] nums) {

        data = new int[nums.length];
        for (int i = 0 ; i < nums.length ; i ++) {
            data[i] = nums[i];
        }

        sum = new int[nums.length + 1];//由于0已经表示了，所以需要+1
        sum[0] = 0;
        for (int i = 1 ; i < sum.length ; i++) {
            sum[i] = sum[i - 1] + nums[i -1];
        }
    }

    //用sum数组预处理方案处理307问题出现超时
    public void update(int index , int value) {
        data[index] = value;

        for (int i = index + 1; i < sum.length; i++) {
            sum[i] = sum[i -1] + data[i -1];
        }
    }

    public int sumRange(int i , int j) {
        return sum[j + 1] - sum[i];//前者表示nums从0到j的和，sum[i]存储nums[0...n-1]的和
    }

}
