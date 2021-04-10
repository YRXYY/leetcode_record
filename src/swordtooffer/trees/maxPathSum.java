package swordtooffer.trees;

import util.TreeNode;

public class maxPathSum {


    /**
     * 输入：root = [-10,9,20,null,null,15,7]
     * 输出：42
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = maxPath(root);
        return Math.max(res, maxSum);
    }

    // 不能盲目使用-Integer.MAX_VALUE ，因为涉及到了加，就会越界
    int maxSum = -1000;
    public int maxPath(TreeNode root) {
        int left = -1000, right = -1000;
        if (root.left != null) {
            left = maxPath(root.left);
        }
        if (root.right != null) {
            right = maxPath(root.right);
        }
        maxSum = Math.max(maxSum, Math.max(left, right));
        maxSum = Math.max(maxSum, left + right + root.val);
        return Math.max(Math.max(left, right), 0) + root.val;
    }

}
