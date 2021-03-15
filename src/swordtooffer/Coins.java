package swordtooffer;

import java.util.Arrays;

/**
 * 零钱兑换
 */
public class Coins {

    /**
     * dfs超时
     */
    private int res,amount;
    public int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        res = Integer.MAX_VALUE;
        this.amount = amount;
        dfsCoin(coins,0,0);
        if(res==Integer.MAX_VALUE) {
            return -1;
        }
        return res;
    }

    public void dfsCoin(int[] coins,long money,int num){
        if(money>amount||num>=res) {
            return;
        }
        if(amount==money){
            res = Math.min(res,num);
            return;
        }
        for(int i=coins.length-1;i>=0;i--){
            dfsCoin(coins,money+coins[i],num+1);
        }
    }

    /**
     * 动态规划
     */
    //dp 表示钱币n可以被换取的最少硬币数，不能换就为-1
    int[] dp;
    public int coinChangeUpToDown(int[] coins, int amount){
        if(coins.length==0)return -1;
        dp = new int[amount];
        return findWay(coins, amount);
    }
    //findWay表示
    public int findWay(int[] coins,int amount){
        if(amount<0)
            return -1;
        if(amount==0)
            return 0;
        if(dp[amount-1]!=0)
            return dp[amount-1];
        int min = Integer.MAX_VALUE;
        for(int i=0;i<coins.length; i++){
            int res = findWay(coins,amount-coins[i]);
            if(res>=0&&res<min){
                min = res+1;
            }
        }
        //在确定大的dp之前，小的先确定了
        dp[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[amount-1];
    }


    public int coinChangeDownToUp(int[] coins, int amount){
        if(coins.length == 0)return -1;
        int[] dp = new int[amount+1];

        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for(int j=0;j<coins.length; j++){
                if(i-coins[j]>=0&&dp[i-coins[j]]<min){
                    min = dp[i-coins[j]] + 1;
                }
            }
            dp[i] = min;
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

}
