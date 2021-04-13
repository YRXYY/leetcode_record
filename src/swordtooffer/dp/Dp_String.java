package swordtooffer.dp;

import java.util.Arrays;

public class Dp_String {

    /**
     * 最长公共子序列
     * abcde    ace
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 最长上升子序列
     */
    public int[] LIS (int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for(int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for(int i = 1; i < dp.length; i++) {
            if (dp[i] > dp[res]) {
                res = i;
            }
        }
        int[] ans = new int[dp[res]];
        ans[ans.length-1] = arr[res];
        for (int i = ans.length - 2; i >= 0; i--) {
            for (int j = res - 1;j >= 0; j--) {
                if (arr[j] < arr[res] && dp[j] == dp[res] - 1) {
                    ans[i] = arr[j];
                    res = j;
                    break;
                }
            }
        }
        return ans;

    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,8,6,4};
        System.out.println(new Dp_String().LIS(arr));
    }


}
