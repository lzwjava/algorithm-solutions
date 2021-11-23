package nowcoder.nc45;


import java.util.ArrayList;

public class Solution {

    void preOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left != null) {
            preOrder(root.left, list);
        }
        if (root.right != null) {
            preOrder(root.right, list);
        }
    }

    void midOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            midOrder(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            midOrder(root.right, list);
        }
    }

    void postOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postOrder(root.left, list);
        }
        if (root.right != null) {
            postOrder(root.right, list);
        }
        list.add(root.val);
    }

    int[] toArr(ArrayList<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
    }

    public int[][] threeOrders(TreeNode root) {
        ArrayList<Integer> preList = new ArrayList<>();
        preOrder(root, preList);
        ArrayList<Integer> midList = new ArrayList<>();
        midOrder(root, midList);
        ArrayList<Integer> postList = new ArrayList<>();
        postOrder(root, postList);
        int n = preList.size();
        int[][] ans = new int[3][n];
        ans[0] = toArr(preList);
        ans[1] = toArr(midList);
        ans[2] = toArr(postList);
        return ans;
    }

}