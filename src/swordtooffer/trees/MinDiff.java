package swordtooffer.trees;

import util.TreeNode;

public class MinDiff {

    int lastNum = Integer.MAX_VALUE/2;
    int res = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return res;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorder(root.left);
        }
        if (Math.abs(root.val - lastNum) < res) {
            res = Math.abs(root.val - lastNum);
        }
        lastNum = root.val;
        if (root.right != null) {
            inorder(root.right);
        }
    }

}
