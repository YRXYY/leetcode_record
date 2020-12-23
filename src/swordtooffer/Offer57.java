package swordtooffer;

import annotation.Level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Offer57 {

    public int[] twoSum1(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(target - nums[i])) {
                return new int[]{nums[i], target - nums[i]};
            } else {
                set.add(nums[i]);
            }
        }
        return null;
    }

    /**
     * 双指针更快，排成矩阵就更好理解
     * 结合题解的图，往下是+，往左是-
     * 有点并查集的意思，不是一个一个遍历，而是跳着遍历，更有效率
     */
    @Level(value = 3)
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                return new int[]{nums[l], nums[r]};
            }
        }
        return null;
    }


    /**
     * 滑动窗口 or 双指针？
     * 减少了重复计算
     */
    @Level(value = 3)
    public int[][] findContinuousSequence(int target) {
        //TODO
        List<int[]> res = new ArrayList<>();

        for (int l = 1, r = 2, sum = 0; l < r & r < target; ) {
            sum = (l + r) * (r - l + 1) / 2;
            if (sum > target) {
                l++;
            } else if (sum < target) {
                r++;
            } else {
                int[] arr = new int[r - l + 1];
                for (int x = l; x <= r; x++) {
                    arr[x - l] = x;
                }
                res.add(arr);
                l++;
                r++;
            }
        }
        int[][] ans = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        new Offer57().findContinuousSequence(9);
    }


}
