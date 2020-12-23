package swordtooffer;

import util.TreeNode;

public class Offer68 {



    /**
     * 二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root.val>=Math.min(p.val,q.val)&&root.val <= Math.max(p.val,q.val))
            return root;
        if(root.val<p.val&&root.val < q.val)
            return lowestCommonAncestor(root.right,p,q);
        else
            return lowestCommonAncestor(root.left,p,q);
    }
    /**
     * 参考版
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q){
        if(root.val<p.val&&root.val < q.val)
            return lowestCommonAncestor1(root.right,p,q);
        else if(root.val>p.val&&root.val>q.val)
            return lowestCommonAncestor1(root.left,p,q);
        return root;
    }


    /**
     * 二叉树的最近公共祖先
     * 后序遍历DFS 背
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q)return root;
        TreeNode left = lowestCommonAncestor2(root.left,p,q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if(left==null) return right;
        if(right == null) return right;
        return root;
    }

}
