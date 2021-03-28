package swordtooffer.leetcodedaily;

import util.ListNode;

import java.util.*;
import java.util.stream.IntStream;

public class March {


    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int count = 1;
        ListNode node = head;
        while (node.next != null) {
            count++;
            node = node.next;
        }
        k %= count;
        node.next = head;
        node = head;
        for (int i = 1; i < count - k; i++) {
            node = node.next;
        }
        ListNode res = node.next;
        node.next = null;
        return res;
    }

    public String reverseWords(String s) {
        if (s == null) {
            return s;
        }
        String[] arr = s.split(" ");
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = reverseString(arr[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(res[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * 除自身之外数组的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int[] little2large = new int[nums.length - 1];
        int[] large2little = new int[nums.length - 1];
        little2large[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            little2large[i] = little2large[i - 1] * nums[i];
        }
        large2little[0] = nums[nums.length - 1];
        for (int i = nums.length - 2; i > 0; i--) {
            large2little[nums.length - 1 - i] = large2little[nums.length - 2 - i] * nums[i];
        }
        int[] res = new int[nums.length];
        res[0] = large2little[nums.length - 2];
        res[nums.length - 1] = little2large[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            res[i] = little2large[i - 1] * large2little[nums.length - 2 - i];
        }
        return res;
    }

    public String reverseString(String str) {
        char[] tmp = str.toCharArray();
        char[] res = new char[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            res[tmp.length - 1 - i] = tmp[i];
        }
        return String.valueOf(res);
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int start = nums.length - 1, end = nums.length - 1;
        for (int goal = start - 1; goal >= 0; goal--) {
            while (goal > 0 && nums[goal] == nums[goal - 1]) {
                goal--;
            }
            int dist = start - goal - 1;
            for (int i = start; i <= end; i++) {
                nums[i - dist] = nums[i];
            }
            start = goal;
            end = start + dist;
        }
        return end - start + 1;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 4, 3};
        System.out.println(IntStream.range(arr[0], arr[2]).sum());

        List<Integer> list = new LinkedList<>();
        list.add(1);


        Deque<Integer> deque = new LinkedList<>();
        deque.pollLast();
        Stack<Integer> s = new Stack<>();
        s.peek();
    }


}
