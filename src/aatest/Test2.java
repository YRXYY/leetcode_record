package aatest;

import com.sun.deploy.util.IconEncoder;

import java.util.*;

public class Test2 {

    public static void main1(String[] args) {
        int[] dp = new int[6];
        // 0 0年
        // 1 1年
        dp[1] = 1;
        for (int i = 0; i < 15; i++) {
            // grow up
            int tmp = dp[1];
            dp[5] = dp[4];
            dp[1] = dp[4];
            dp[4] = dp[3];
            dp[3] = dp[2];
            dp[1] += dp[2];
            dp[2] = tmp;
        }
        int res = 0;
        for (int i = 1; i < 6; i++) {
            res += dp[i];
        }
        System.out.println(res);

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int left = 0, right = 1000000;
        while (left <  right) {
            int mid = left + (right - left) / 2;
            long nums1 = (long) mid * (mid + 1) / 2;
            long nums2 = (long) (mid + 1) * (mid + 2) / 2;
            if (n < nums1) {
                right = mid - 1;
            } else if (n > nums2) {
                left = mid + 1;
            } else if (n == nums2) {
                System.out.println(mid + 1);
                return;
            } else{
                System.out.println(mid);
                return;
            }
        }



    }




    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        List<Integer> numList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();
        List<Character> tempList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                continue;
            }
            if (arr[i] == '+' ||arr[i] == '-' ||arr[i] == '*' ||arr[i] == '/' ){
                charList.add(arr[i]);
                StringBuffer stringBuffer = new StringBuffer();
                for (int j = 0; j < tempList.size(); j++) {
                    stringBuffer.append(tempList.get(j));
                }
                numList.add(Integer.valueOf(stringBuffer.toString()));
                tempList = new ArrayList<>();
            } else {
                tempList.add(arr[i]);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < tempList.size(); j++) {
            stringBuffer.append(tempList.get(j));
        }
        numList.add(Integer.valueOf(stringBuffer.toString()));
        List<Integer> numRemove = new ArrayList<>();
        for (int i = 0; i < charList.size(); i++) {
            if (charList.get(i)=='*' || charList.get(i)=='/') {
                int num1 = numList.get(i);
                numRemove.add(i);
                int num2 = numList.get(i+1);
                if (charList.get(i) == '*') {
                    num2 *= num1;
                } else {
                    num2 = num1 / num2;
                }
                numList.remove(i+1);
                numList.add(i+1, num2);
            }
        }
        for (int i = numRemove.size() - 1; i >= 0; i--) {
            numList.remove((int)numRemove.get(i));
        }
        int num =  numList.get(0);
        int count = 1;
        for (int i = 0; i < charList.size(); i++) {
            if (charList.get(i) == '+' ) {
                num += numList.get(count);
                count++;
            } else if (charList.get(i) == '-' ) {
                num -= numList.get(count);
                count++;
            }
        }

        System.out.println(num);




    }








































    /**
     * 最大数
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     */
    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, (x, y) -> (Long.valueOf(y+x).compareTo(Long.valueOf(x+y))));
        if ("0".equals(arr[0])) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }




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
