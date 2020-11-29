package swordtooffer.data_structure;

import util.TreeNode;

public class ListNode {
    /**
     * 删除重复的结点
     */
    public util.ListNode deleteDuplicates (util.ListNode head) {
        util.ListNode ans = new util.ListNode();
        ans.next = head;
        util.ListNode pre = ans;
        util.ListNode node = head;
        while(node!=null){
            if(node.next!=null&&node.val==node.next.val){
                while(node.next!=null){
                    if(node.next.val!=node.val){
                        break;
                    }
                    util.ListNode tmp = node.next.next;
                    node.next.next = null;
                    node.next = tmp;
                }
                util.ListNode tmp = node.next;
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
    public void reorderList(util.ListNode head) {
        if(head==null||head.next==null)return;
        util.ListNode fast = head;
        util.ListNode slow = head;
        util.ListNode pre = null;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        util.ListNode prev = null;
        while(slow!=null){
            util.ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        util.ListNode end = prev;
        util.ListNode start = head.next;
        util.ListNode node = head;
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
