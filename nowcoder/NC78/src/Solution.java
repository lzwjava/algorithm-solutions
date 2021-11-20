public class Solution {

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode it = head;
        ListNode n1 = new ListNode(head.val);
        while (it.next != null) {
            ListNode next = new ListNode(it.next.val);
            next.next = n1;
            n1 = next;

            it = it.next;
        }
        return n1;
    }
    
}