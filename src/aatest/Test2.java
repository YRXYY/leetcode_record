package aatest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test2 {

    /**
     * Integer[] ans = list.toArray(new Integer[list.size()]);
     * <p>
     * list 转数组
     */


//    public long maxWater1 (int[] arr) {
//        // write code here
//        if (arr==null || arr.length==0) {
//            return 0L;
//        }
//        long ans = 0;
//        int l=0, r=arr.length-1;
//        int lmax = arr[0], rmax=arr[arr.length-1];
//        while(l<=r) {
//            lmax = Math.max(arr[l], lmax);
//            rmax = Math.max(arr[r], rmax);
//            if(lmax < rmax) {
//                ans += lmax - arr[l];
//                l++;
//            } else {
//                ans += rmax - arr[r];
//                r--;
//            }
//        }
//        return (long) ans;
//    }
    public long maxWater(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0L;
        }
        int l = 0, r = arr.length - 1;
        long lmax = arr[l], rmax = arr[r];
        long ans = 0;
        while (l < r) {
            lmax = Math.max(arr[l], lmax);
            rmax = Math.max(arr[r], rmax);
            if (lmax < rmax) {
                ans += lmax - arr[l];
                l++;
            } else {
                ans += rmax - arr[r];
                r--;
            }
        }
        return (long) ans;
    }


//    public ArrayList<Integer> spiralOrder1(int[][] matrix) {
//        ArrayList<Integer> res = new ArrayList<Integer>();
//        if (matrix.length == 0) {
//            return res;
//        }
//
//        int top = 0;
//        int bottom = matrix.length - 1;
//        int left = 0;
//        int right = matrix[0].length - 1;
//
//        while (true) {
//            //top
//            for (int i = left; i <= right; i++) {
//                res.add(matrix[top][i]);
//            }
//            top++;
//            if (left > right || top > bottom) {
//                break;
//            }
//
//            for (int i = top; i <= bottom; i++) {
//                res.add(matrix[i][right]);
//            }
//            right--;
//            if (left > right || top > bottom) {
//                break;
//            }
//
//            for (int i = right; i >= left; i--) {
//                res.add(matrix[bottom][i]);
//            }
//            bottom--;
//            if (left > right || top > bottom) {
//                break;
//            }
//
//            for (int i = bottom; i >= top; i--) {
//                res.add(matrix[i][left]);
//            }
//            left++;
//            if (left > right || top > bottom) {
//                break;
//            }
//        }
//
//        return res;
//    }

    /**
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (matrix == null) {
            return list;
        }
        int x1 = 0;
        int y1 = matrix.length - 1;
        int x2 = 0;
        int y2 = matrix[0].length - 1;
        while (true) {
            for (int i = x2; i <= y2; i++) {
                list.add(matrix[x1][i]);
            }
            x1++;
            if (x1 > y1 || x2 > y2) {
                break;
            }
            for (int i = x1; i <= y1; i++) {
                list.add(matrix[i][y2]);
            }
            y2--;
            if (x1 > y1 || x2 > y2) {
                break;
            }
            for (int i = y2; i >= x2; i--) {
                list.add(matrix[y1][i]);
            }
            y1--;
            if (x1 > y1 || x2 > y2) {
                break;
            }
            for (int i = y1; i >= x1; i--) {
                list.add(matrix[i][x2]);
            }
            x2++;
            if (x1 > y1 || x2 > y2) {
                break;
            }
        }
        return list;
    }


    public int[] getMinStack(int[][] op) {
        Stack<Integer> stackA = new Stack<Integer>();
        Stack<Integer> stackB = new Stack<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0 ; i < op.length ; i++){
            if (op[i][0] == 1){
                stackA.push(op[i][1]);
                if (stackB.isEmpty() || stackB.peek() >= op[i][1]){
                    stackB.push(op[i][1]);
                }
            }else if (op[i][0] == 2){
                int tmp = stackA.pop();
                if (tmp == stackB.peek()){
                    stackB.pop();
                }
            }else {
                res.add(stackB.peek());
            }
        }
        int[] answer = new int[res.size()];
        for (int i = 0 ; i < res.size() ; i++){
            answer[i] = res.get(i);
        }
        return answer;
    }


}
