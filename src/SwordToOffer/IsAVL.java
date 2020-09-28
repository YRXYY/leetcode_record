package SwordToOffer;

/**
 * 判断是不是AVL树
 * 用-1作为信号量，为不符合
 */
public class IsAVL {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return getDepth(root)!=-1;
    }
    public int getDepth(TreeNode root){
        if(root == null)return 0;
        int left = getDepth(root.left);
        if(left==-1)
            return -1;
        int right = getDepth(root.right);
        if(right==-1)
            return -1;
        return Math.abs(left-right)<=1?1+Math.max(left,right):-1;
    }

}

