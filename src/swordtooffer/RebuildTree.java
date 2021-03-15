package swordtooffer;

import util.TreeNode;

import java.util.Arrays;

/**
 * 重建二叉树
 */
public class RebuildTree {

    //      1
    //  2       3
    //pre :  1 2 3
    //in  :  2 1 3
    //post:  2 3 1
    //相同的数字的前面的一个数字就是根？

    //1 2 3 4 5 6 7
    //3 2 4 1 6 5 7

    //res: {1,2,5,3,4,6,7}

    public static void main(String[] args) {
        int[] pre = new int[]{};
        int[] in = new int[]{};
        TreeNode node = new RebuildTree().reConstructBinaryTree(pre, in);
        System.out.println();
    }


    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre.length == 0 || pre.length != in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        if (pre.length <= 1) {
            return root;
        }
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in, i+1, in.length));
            }
        }
        return root;
    }

    public void reCons(int[] pre, int[] in, int preBegin, int preEnd, int inBegin, int inEnd, TreeNode node) {
        if (inBegin > inEnd) {
            return;
        }
        if (inBegin == inEnd) {
            node = new TreeNode(pre[inBegin]);
            return;
        }
        int num = 0;
        for (int i = inBegin; i < inEnd; i++) {
            if (pre[preBegin] == in[i]) {
                num = i;
                node = new TreeNode(in[i]);
                break;
            }
        }
        reCons(pre, in, preBegin + 1, preBegin + num - inBegin, inBegin, num-1, node.left);
        reCons(pre, in, preBegin + num - inBegin + 1, preEnd, num+1, inEnd, node.right);
    }


}
