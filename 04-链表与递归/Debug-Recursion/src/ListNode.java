public class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) {
         val = x;
    }

    //定义构造方法（参数为数组）
    public ListNode(int[] arr) {
        if (arr == null && arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }

        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }


    //重写toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while(cur != null) {
            sb.append(cur.val + "->");
            cur = cur.next;
        }
         sb.append("NULL");

        return sb.toString();
    }

}
