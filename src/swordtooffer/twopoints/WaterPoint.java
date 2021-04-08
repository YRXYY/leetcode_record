package swordtooffer.twopoints;

public class WaterPoint {

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

}
