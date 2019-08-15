public class Solution {
    //没有使用虚拟头结点
    public ListNode removeElement(ListNode head,int val) {
        //1.头元素val等于val的情况
        while(head != null && head.val == val) {//后面元素的节点的val可能与val相等所以使用循环判断
           /* ListNode delNode = head;
            head = head.next;
            delNode.next = null;*/
            //简化
            head = head.next;

        }

        if (head == null) {//这种情况所有的head都与val匹配，那么到最后head就为null
            return null;
        }

        //2.删除的元素在链表中间的情况
        ListNode prev = head;
        while(prev.next != null) {//如果链表中有元素就执行循环，也就是说没有到最后一个节点
            if(prev.next.val == val) {//如果两者的值相等就说明需要执行删除操作
                /*ListNode delNode = prev.next;
                prev.next = delNode.next;//将需要删除的节点删除，断开节点间的联系
                delNode = null;*/
                //简化
                prev.next = prev.next.next;
            }else {                 //值不相等就不需要删除
                prev = prev.next;
            }
        }

        return head;
    }


    //定义main函数测试
    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElement(head, 6);
        System.out.println(res);
    }

}



