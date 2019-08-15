public class Solution2 {
    public ListNode removeElement(ListNode head ,int val) {
        //1.创建虚拟头结点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;//相当于将dummyHead与链表建立联系，同时也说明所有元素都有头结点了

        ListNode prev = dummyHead;
        while(prev.next != null) {
            if (prev.next.val == val) {//说明需要删除元素
                prev = prev.next.next;
            }else {//不需要删除元素
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }



}
