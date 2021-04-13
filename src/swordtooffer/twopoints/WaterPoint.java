package swordtooffer.twopoints;

public class WaterPoint {

    /**
     * 盛了多少水
     */
    public int trapPoints(int[] height) {
        if (height.length < 1) {
            return 0;
        }
        int res = 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = height.length - 1;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += Math.min(leftMax, rightMax) - height[left];
                left++;
            } else {
                res += Math.min(leftMax, rightMax) - height[right];
                right--;
            }
        }
        return res;
    }

    /**
     * 盛最多水的容器
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            int water = (right - left) * Math.min(height[left], height[right]);
            if (water > res) {
                res = water;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

        }
        return res;

    }





}
