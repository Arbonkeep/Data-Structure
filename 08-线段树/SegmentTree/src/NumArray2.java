/**
 * 解决303号问题
 */
public class NumArray2 {
    private int[] sum;
    //sum[i]存储前i个元素和，sum[0] = 0（表示一个元素都没有时的和）
    //sum[i]存储nums[0...n-1]的和

    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];//由于0已经表示了，所以需要+1
        sum[0] = 0;
        for (int i = 1 ; i < sum.length ; i++) {
            sum[i] = sum[i - 1] + nums[i -1];
        }
    }

    public int sumRange(int i , int j) {
        return sum[j + 1] - sum[i];//前者表示nums从0到j的和，sum[i]存储nums[0...n-1]的和
    }

}
