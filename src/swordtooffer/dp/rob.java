package swordtooffer.dp;

public class rob {

    public int rob1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int first = 0, pre = nums[0];
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(first + nums[i], pre);
            first = pre;
            pre = res;
        }
        return res;
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);

        }
        int first = 0, pre = nums[0];
        int res1 = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            res1 = Math.max(first + nums[i], pre);
            first = pre;
            pre = res1;
        }

        first = 0;
        pre = nums[1];
        int res2 = 0;
        for (int i = 2; i < nums.length; i++) {
            res2 = Math.max(first + nums[i], pre);
            first = pre;
            pre = res2;
        }

        return Math.max(res1, res2);
    }

}
