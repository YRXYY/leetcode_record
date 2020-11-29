package swordtooffer.dp;

/**
 * 动态规划经典问题，背包问题
 */
public class PackagePro {

    /**
     * 经典动态规划问题：0-1背包问题
     * 一个载重W的背包和N个物品，第i个物体的重量为wt[i],价值为val[i]
     * 问这个背包最多能装的价值是多少？
     */
    /**
     * 标准套路：
     * 1. 明确两点：状态 + 选择
     *      状态：背包的容量 + 可选择的物品
     *      选择：装进背包 or 不装进背包
     * 2. 明确数组的定义
     *      dp数组就是描述问题局面的一个数组，换句话说，就是把上面的状态用dp数组表示出来
     *      dp[i][w] : 对于前i个物品，当前背包容量为w，这种情况下可以装的最大价值是dp[i][w]
     *      比如dp[3][5]=6，含义：对于前3个物品，当背包容量为5时，最多可以装下价值为6的物品
     *      因此，最终答案就是dp[N][W],base case是dp[0][i]=dp[i][0]=0
     * 3. 根据选择，思考状态转移的逻辑
     *      根据定义，如果未装入第i个物品，dp[i][W]=dp[i-1][W]
     *                      装入，         dp[i][W]=dp[i-1][W-wt[i]]+val[i-1]
     *                      则 dp[i][w] = max(dp[i-1][w],dp[i-1][w-wt[i]]+val[i])
     */
    public int knapscak(int W, int N, int[] wt,int[] val){
        int[][] dp = new int[N+1][W+1];
        for(int i=1;i<=N;i++){
            for(int w=1;w<=W;w++){
                if(w-wt[i]<0){
                    //当前背包容量装不下，不装
                    dp[i][w] = dp[i-1][w];
                }else{
                    //择优
                    dp[i][w] = Math.max(dp[i-1][w-wt[i-1]]+val[i-1],dp[i-1][w]);
                }
            }
        }
        return dp[N][W];
    }




    /**
     * 子集背包问题
     * leetcode 416
     * 输入一个集合(数组），返回是否能够分割成和相等的两个子集
     * 转化为背包问题：
     *      给定一个容量为sum/2的背包，和N个物品，每个物品的重量为num[i]
     *      存不存在一种装法，恰好将背包装满？
     * 解法分析：
     *      1. 明确状态和选择，同上，
     *      2. dp数组的定义
     *          dp[i][j] = true表示，对于前i个物品，当容量为j时，恰好 将背包装满，false则不可以
     *          如 dp[4][9] = true表示对于容量=9，只用前4个物品，可以有一种方法把背包装满
     *          所以最终答案就是要求 dp[N][sum/2]
     *          base:dp[i][0]=true   dp[0][i]=false
     *      3. 状态转移逻辑
     *          类似上面，若不把第i个放进背包，能否恰好装满背包取决于dp[i-1][j]
     *                    若放第i个，取决于dp[i-1][j-nums[i-1]]
     * 状态压缩：因为第i行都是根据i-1得到的，所以可以压缩成一维数组
     */
    public boolean  canPartition(int[] nums){
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        int n = nums.length;
        sum = sum/2;
        //定义dp，重要
        boolean[][] dp = new boolean[n+1][sum+1];
        for(int i=0;i<=n;i++){
            dp[i][0] = true;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=sum;j++){
                if(j-nums[i-1]<0){
                    // 背包容量不足，不能装入第 i 个物品
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][sum];
    }



    /**
     * 完全背包问题：即物品的容量是无限的，即硬币问题
     *
     */



    /**背
     * 动态规划设计：最大子数组
     * leetcode53 最大和的连续子数组
     * **怎么定义dp！：以num[i]为几位的最大子数组和为dp[i]
     * 然后发现dp[i]的状态仅和dp[i-1]有关，因此可以进一步降低空间复杂度，将dp[i-1]用prev表示
     */
    public int maxSubArray(int[] nums){
        int ans = nums[0];
        int prev = nums[0],cur = 0;
        for(int i=1;i<nums.length;i++){
            cur = Math.max(nums[i],nums[i]+prev);
            ans=Math.max(ans,cur);
            prev = cur;
        }
        return ans;
    }








}
