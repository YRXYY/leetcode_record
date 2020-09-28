package SwordToOffer.data_structure;

import Util.TreeNode;

public class ListNode {
    /**
     * 删除重复的结点
     */
    public Util.ListNode deleteDuplicates (Util.ListNode head) {
        Util.ListNode ans = new Util.ListNode();
        ans.next = head;
        Util.ListNode pre = ans;
        Util.ListNode node = head;
        while(node!=null){
            if(node.next!=null&&node.val==node.next.val){
                while(node.next!=null){
                    if(node.next.val!=node.val){
                        break;
                    }
                    Util.ListNode tmp = node.next.next;
                    node.next.next = null;
                    node.next = tmp;
                }
                Util.ListNode tmp = node.next;
                node.next = null;
                pre.next = tmp;
                node = tmp;
            }else{
                pre = node;
                node = node.next;
            }
        }
        return ans.next;
    }

    /**
     * 重排链表
     * 利用快慢指针，找到中间结点，反转后半个，再重新组合
     */
    public void reorderList(Util.ListNode head) {
        if(head==null||head.next==null)return;
        Util.ListNode fast = head;
        Util.ListNode slow = head;
        Util.ListNode pre = null;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        Util.ListNode prev = null;
        while(slow!=null){
            Util.ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        Util.ListNode end = prev;
        Util.ListNode start = head.next;
        Util.ListNode node = head;
        while(start != null){
            node.next = end;
            end = end.next;
            node = node.next;
            node.next = start;
            start = start.next;
            node = node.next;
        }
        if(end != null){
            node.next = end;
        }

    }


    /**
     * 二叉树的最大路径和
     */
    int res = Integer.MIN_VALUE;
    public int maxPathSum (TreeNode root) {
        if(root == null)
            return 0;
        sum(root);
        return res;
        // write code here
    }


    public int sum(TreeNode root){
        if(root == null)return 0;
        int left = Math.max(sum(root.left),0);
        int right = Math.max(sum(root.right),0);
        res = Math.max(res,left+right+root.val);
        return root.val+Math.max(left,right);
    }
}
