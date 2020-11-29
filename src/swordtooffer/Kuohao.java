package swordtooffer;

import java.util.Stack;

/**
 * leetcode 1044!!!
 * "(()"        2
 * ")()())"     4
 */
public class Kuohao {

    //动态规划，利用找出以i结尾的字串与前面的关系 背？
    public int longestValidParentheses1(String s) {
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < dp.length; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else if (i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i>=dp[i-1]+2?dp[i-dp[i-1]-2]:0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //栈
    //太强了，不是计数，直接减
    public int longestValidParentheses2(String s) {
        int res = 0;int cur = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            }else{
                //  ')'
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    res = Math.max(res,i-stack.peek());
                }
            }
        }
        return res;
    }

    //left,right 计数器，观察特征  算了
    public int longestValidParentheses3(String s){
        return 0;
    }

}





