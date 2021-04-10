package algorithm;

import util.ListNode;

public class MergeSortDemos {

    /**
     * sort the LinkedList
     */
    public ListNode sortList(ListNode head) {
        return merge(head);
    }

    public ListNode merge(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 此处fast不能为head，否则下面的循环必走，slow会置为null，死循环
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode nextHalf = slow.next;
        slow.next = null;
        ListNode res1 = merge(head);
        ListNode res2 = merge(nextHalf);
        return sort(res1, res2);
    }

    public ListNode sort(ListNode head1, ListNode head2) {
        ListNode res = new ListNode(0);
        ListNode node = res;
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                node.next = head2;
                return res.next;
            }
            if (head2 == null) {
                node.next = head1;
                return res.next;
            }
            if (head1.val < head2.val) {
                node.next = head1;
                head1 = head1.next;
            } else {
                node.next = head2;
                head2 = head2.next;
            }
            node = node.next;
        }
        return res.next;
    }

}
