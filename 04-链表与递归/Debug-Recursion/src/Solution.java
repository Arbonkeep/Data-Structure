public class Solution {
    public ListNode removeElements(ListNode head , int val ,int depth) {

        String  depthString  = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("call:remove" + val + "in" + head );

        if (head == null) {
            System.out.print(depthString);
            System.out.println("return:" + head);
            return head;//head = null
        }

        ListNode res= removeElements(head.next, val ,depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ":" + res);

        ListNode ret;
        if (head.val == val) {
            ret = res;
        }else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("return: " + ret);
        return ret;
    }

    //定义描述链表深度的方法
    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth ; i++) {
            sb.append("--");
        }
        return sb.toString();
    }


    //定义一个主方法用于测试
    public static void main(String[] args) {
        int[] arr = {1,2,6,3,4,5,6};
        ListNode head = new ListNode(arr);//构造生成链表
        System.out.println(head);
        ListNode res = (new Solution()).removeElements(head, 6 ,0);
        System.out.println(res);
    }

}
