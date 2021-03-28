package swordtooffer.dp;

public class Dp_stock {


    public static void main(String[] args) {
    }

    /**
     * 股票买卖一次
     * dp[i] = max(dp[i-1],prices[i]-min(prices[0:n-1])
     */

    public int maxProfit1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    //��Ʊ�������
    //��ȷ�
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int profit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length - 1; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] > min && prices[i + 1] < prices[i]) {
                profit += (prices[i] - min);
                min = prices[i + 1];
            }
        }
        if (prices[prices.length - 1] != min) {
            profit += (prices[prices.length - 1] - min);
        }
        return profit;
    }

    public int maxProfit22(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {

            }
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }


    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        return dfs3(prices, 0, 0, 0);
    }

    public int dfs3(int[] prices, int index, int status, int k) {
        if (k == 2 || index == prices.length) {
            return 0;
        }
        int a = 0, b = 0, c = 0;
        a = dfs3(prices, index + 1, status, k);
        if (status == 1) {
            b = dfs3(prices, index + 1, 0, k + 1) + prices[index];
        } else {
            c = dfs3(prices, index + 1, 1, k) - prices[index];
        }

        return Math.max(Math.max(a, b), c);
    }


}
