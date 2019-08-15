public class Solution3 {
    //通过递归算法将链表中指定的所有元素删除
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {//说明链表为空
            return null;
        }

        head.next = removeElements(head.next,val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,6,3,4,5,6};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        ListNode res = (new Solution3()).removeElements(head,6);
        System.out.println(res);
    }


}
