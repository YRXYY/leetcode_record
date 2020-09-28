package SwordToOffer;

/**
 * 连续子数组的最大和
 */
public class Offer42 {

    /**
     * 动态规划  O(N)  O(1)
     * dp[i] 表示以元素nums[i]为结尾的连续子数组的最大和
     * 背！！
     */
    public int maxSubArray(int[] nums) {
        if(nums==null||nums.length == 0)return 0;
        int res = nums[0],pre = nums[0];
        for(int i=1;i<nums.length;i++){
            pre = pre>0?pre+nums[i]:nums[i];
            res = Math.max(res,pre);
        }
        return res;
    }

}
