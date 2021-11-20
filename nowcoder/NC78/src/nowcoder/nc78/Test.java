package nowcoder.nc78;

/**
 * Created by lzw on 2021/11/20 11:17 AM.
 */
public class Test {
    public static void main() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        ListNode listNode = new Solution().ReverseList(n1);
        System.out.println(listNode);
    }
}
