package Util;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtil {

    public static ListNode constructListNode(String str){
        String[] arr = str.split(",");
        ListNode res = new ListNode();
        ListNode node = res;
        for(int i = 0; i < arr.length; i++){
            ListNode temp = new ListNode();
            temp.val = Integer.valueOf(arr[i]);
            node.next = temp;
            node = node.next;
        }
        return res.next;
    }

//    public static TreeNode constructTreeNode(String str){
//        String[] arr = str.split(",");
//        TreeNode head = new TreeNode();
//        head.val = Integer.valueOf(arr[0]);
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(head);
//        for(int i = 1; i < arr.length; i++){
//            TreeNode node = queue.poll();
//            if(arr[i].equals("#")){
//                i++;
//            }else{
//                TreeNode left = new TreeNode();
//                left.val = Integer.valueOf(arr[i]);
//                node.left = left;
//            }
//        }
//    }
}
