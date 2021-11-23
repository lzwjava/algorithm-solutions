package nowcoder.nc45;

/**
 * Created by lzw on 2021/11/23 10:32 AM.
 */
public class Test {
    public static void run() {
        TreeNode n1 = new TreeNode();
        n1.val = 1;
        TreeNode n2 = new TreeNode();
        n2.val = 2;
        TreeNode n3 = new TreeNode();
        n3.val = 3;
        n1.left = n2;
        n1.right = n3;
        int[][] ints = new Solution().threeOrders(null);
        System.out.println(ints);
    }
}
