package aatest;

import util.ListNode;
import util.TreeNode;

import java.util.*;
import java.util.function.BiConsumer;

public class Test1 {

    public static void main(String[] args) {
//        String str = String.valueOf(123);
//        System.out.println((str.charAt(1)-48));

//'a','b','c','c','b','a'

//        List<Integer> list = new ArrayList<>();
//
//
//        Queue<Integer> queue = new ArrayDeque<>();
//
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.pop();
//        stack.peek();
//        System.out.println();

        System.out.println(new Test1().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));


    }








    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        int state = 0, count = 0, x = 0, y = 0;
        ArrayList<Integer> list = new ArrayList<>();
        boolean[][] tag = new boolean[matrix.length][matrix[0].length];
        while (count < matrix.length * matrix[0].length) {
            list.add(matrix[x][y]);
            tag[x][y] = true;
            switch (state) {
                case 0:
                    if (x == matrix.length || tag[x + 1][y]) {
                        state = 1;
                        y++;
                    } else {
                        x++;
                    }
                    break;
                case 1:
                    if (y == matrix[0].length || tag[x][y + 1]) {
                        state = 2;
                        x--;
                    } else {
                        y++;
                    }
                    break;
                case 2:
                    if (x == 0 || tag[x - 1][y]) {
                        state = 3;
                        y--;
                    } else {
                        x--;
                    }
                    break;
                case 3:
                    if (y == 0 || tag[x][y - 1]) {
                        state = 0;
                        x++;
                    } else {
                        y--;
                    }
                    break;
                default:
                    break;
            }
            count++;
        }
        return list;
    }


    //  "terrortimelimi"
    public static void rotateString(char[] str, int offset) {
        if (offset == 0 || str.length == 0 || "".equals(str)) {
            return;
        }
        offset %= str.length;
        char[] temp = new char[offset];
        for (int i = offset - 1; i >= 0; i--) {
            temp[i] = str[str.length - offset + i];
        }
        for (int i = str.length - 1; i > offset - 1; i--) {
            str[i] = str[i - offset];
        }
        for (int i = 0; i < offset; i++) {
            str[i] = temp[i];
        }
    }

    /**
     * 水仙花
     */
    public List<Integer> getNarcissisticNumbers(int n) {
        int num = (int) Math.pow(10, n - 1);
        List<Integer> res = new ArrayList<>();
        for (int i = num + 1; i < Math.pow(10, n); i++) {
            int temp = 0;
            String str = String.valueOf(i);
            for (int m = 0; m < str.length(); m++) {
                temp += Math.pow(str.charAt(m) - 48, n);
                if (temp > i) {
                    break;
                }
            }
            if (temp == i) {
                res.add(i);
            }
        }
        return res;
    }


}
