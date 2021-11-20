package nowcoder.nc78;

public class SolutionPlus {

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new ListNode(head.val);
        } else {
            ListNode node = ReverseList(head.next);
            ListNode tail = node;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new ListNode(head.val);
            return node;
        }
    }

}