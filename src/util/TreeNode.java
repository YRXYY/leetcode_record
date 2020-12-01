package util;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}