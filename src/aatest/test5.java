package aatest;

import util.ListNode;

import java.util.*;

public class test5 {


    public static void main(String[] args) {
        int[][] arr = new int[][]{{6, 0, 6, 0}, {0, 4, 3, 8}, {9, 6, 0, 3}, {7, 9, 1, 8}, {7, 7, 4, 7}, {3, 2, 0, 2}, {4, 3, 4, 8}, {3, 2, 3, 9}, {4, 2, 0, 4}};
        new test5().setZeroes(arr);
        Integer i = 2;
        List<ListNode> list = new ArrayList<>();
        if (i instanceof Integer) {

        }
        Deque<Integer> deque = new LinkedList<>();
        deque.peek();

    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        ListNode pre = new ListNode(Integer.MAX_VALUE);
        ListNode res = pre;
        pre.next = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                int tmpVal = node.val;
                while (node != null) {
                    if (node.val == tmpVal) {
                        node = node.next;
                    } else {
                        break;
                    }
                }
                pre.next = node;
            } else {
                pre = node;
                node = node.next;
            }
            if (node == null || node.next == null) {
                break;
            }
        }
        return res.next;
    }


    public boolean find132pattern1(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] < min) {
                min = nums[i];
                continue;
            } else {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[i] && nums[j] > min) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // [-1,3,2,0]
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int num3 = nums[nums.length - 1];
        int num2 = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > num3) {
                num2 = num3;
                num3 = nums[i];
            } else if (nums[i] < num2 && num3 > num2) {
                return true;
            }
        }
        return false;
    }


    public int hammingWeight(int n) {
        String str = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                count++;
            }
        }
//        while (n > 0) {
//            n &= (n - 1);
//            count++;
//        }
        return count;
    }


    public void setZeroes(int[][] matrix) {
        int state = -1;
        //0 : 00 = 0     1: 指纵坐标    2：指横坐标      3：指横纵坐标
        if (matrix[0][0] == 0) {
            state = 0;
        }
        if (state != 0) {
            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][0] == 0) {
                    matrix[0][0] = 0;
                    state = 1;
                }
            }
            for (int i = 1; i < matrix[0].length; i++) {
                if (matrix[0][i] == 0) {
                    if (state == 1) {
                        state = 3;
                        break;
                    } else {
                        state = 2;
                        matrix[0][0] = 0;
                    }
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        switch (state) {
            case 0:
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[0][i] = 0;
                }
                break;
            case 1:
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
                break;
            case 2:
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[0][i] = 0;
                }
                break;
            case 3:
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
                for (int i = 0; i < matrix[0].length; i++) {
                    matrix[0][i] = 0;
                }
                break;
            default:
                break;
        }
    }


    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    stack.push(comp(stack.pop(), stack.pop(), 1));
                    break;
                case "-":
                    stack.push(comp(stack.pop(), stack.pop(), 2));
                    break;
                case "*":
                    stack.push(comp(stack.pop(), stack.pop(), 3));
                    break;
                case "/":
                    stack.push(comp(stack.pop(), stack.pop(), 4));
                    break;
                default:
                    stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    /**
     * 1 +
     * 2 -
     * 3 *
     * 4 /
     */
    public int comp(Integer x1, Integer x2, int num) {
        int x = (int) x1;
        int y = (int) x2;
        switch (num) {
            case 1:
                return x2 + x1;
            case 2:
                return x2 - x1;
            case 3:
                return x2 * x1;
            case 4:
                return x2 / x1;
            default:
                return Integer.MAX_VALUE;
        }
    }


}
