package SwordToOffer.data_structure;

import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.*;

public class TreeRecord {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    //判断树是否是自身的镜像  重要！
    public boolean isSymmetric (TreeNode root) {
        return isSymmetricTree1(root,root);
    }
    //递归
    public boolean isSymmetricTree1(TreeNode node1,TreeNode node2){
        if(node1==null&&node2==null)
            return true;
        if(node1==null||node2==null)
            return false;
        if(node1.val!=node2.val)
            return false;
        return isSymmetricTree1(node1.left,node2.right)&&
                isSymmetricTree1(node1.right,node2.left);
    }
    //迭代
    //就是用队列存储、比较
    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }


    //二叉树中和为某一值的路径      Offer 34
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root,sum);
        return res;
    }
    //wrong version
    //实现之前，不要进行过早的剪枝，优化
    //过早的剪枝很可能因为思考不周出现错误
    public void dfs(TreeNode node,int sum){
        if(node == null) return;
        path.add(node.val);
        sum -= node.val;
        if(sum<0) {
            path.removeLast();
            return;
        }
        if(0==sum){
            if(node.left==null&&node.right==null){
                res.add(new LinkedList<> (path));
            }
            path.removeLast();
            return;
        }
        dfs(node.left,sum);
        dfs(node.right,sum);
        path.removeLast();
    }
    //right version
    public void dfsRight(TreeNode node,int sum){
        if(node == null) return;
        path.add(node.val);
        sum -= node.val;
        if(0==sum&&node.left==null&&node.right==null){
            res.add(new LinkedList<> (path));
        }
        dfs(node.left,sum);
        dfs(node.right,sum);
        path.removeLast();
    }


    //二叉树的直径
    int maxLen;
    public int diameterOfBinaryTree(TreeNode root) {
        maxLen = 1;
        dfs(root);
        return maxLen;
    }
    public int dfs(TreeNode root){
        if(root==null)return 0; // 访问到空节点了，返回0
        int left = dfs(root.left);// 左儿子为根的子树的深度
        int right = dfs(root.right);// 右儿子为根的子树的深度
        maxLen = Math.max(maxLen,left+right+1);
        return Math.max(left,right)+1;
    }


    //重建二叉树     要看、回顾
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length == 0)
            return null;
        //用map存储中序遍历的每个元素和对应下标
        Map<Integer,Integer> map = new HashMap<>();
        int length = preorder.length;
        for(int i = 0; i < length; i++){
            map.put(inorder[i],i);
        }
        TreeNode root = buildTree(preorder,0,length-1,inorder,0,length-1,map);
        return root;
    }
    public TreeNode buildTree(int[] preorder,int pStart,int pEnd,int[]inorder,int iStart,int iEnd,Map<Integer,Integer> map){
        if(pStart>pEnd)return null;
        int rootVal = preorder[pStart];
        TreeNode root = new TreeNode();
        root.val = rootVal;
        if(pStart==pEnd){
            return root;
        }else {
            int rootIndex = map.get(rootVal);
            int leftNodes = rootIndex - iStart,rightNodes = iEnd - rootIndex;
            //TreeNode

        }
        //TODO
        return null;
    }


    /**
     * Offer 26
     * 树的子结构，判断B是不是A的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null||B==null)
            return false;
        return testSub(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right, B);
    }

    public boolean testSub(TreeNode A,TreeNode B){
        if(B==null)return true;
        if(A==null)return false;
        if(A.val!=B.val)
            return false;
        return testSub(A.left,B.left)&&testSub(A.right,B.right);
    }


    /**
     * Offer27  二叉树的镜像
     */
    public TreeNode mirrorTree(TreeNode root) {
        dfsMirror(root);
        return root;
    }

    public void dfsMirror(TreeNode root){
        if(root==null)return;
        mirror(root);
        dfsMirror(root.left);
        dfs(root.right);
    }

    public void mirror(TreeNode root){
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
    }







}






