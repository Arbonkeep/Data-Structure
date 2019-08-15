public class Sum {
    //定义一个数组求和的方法(提供给用户使用)
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    //数组求和(l为数组中左边界元素)
    private static int sum(int[] arr,int l) {
        if(l == arr.length) {
            return 0;
        }

        return arr[l] + sum(arr,l + 1);
    }


    //定义一个main函数来测试sum方法
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int sum = sum(arr);
        System.out.println(sum);
    }
}
