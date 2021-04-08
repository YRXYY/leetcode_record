package swordtooffer.binarysearch;

public class FindNum {

    /**
     * nums = [4,5,6,7,0,1,2], target = 0
     */
    public int search1(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] < nums[mid]) {
                if (target < nums[mid] && nums[0] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 输入：nums = [2,5,6,0,0,1,2], target = 0
     * 输出：true
     */
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public boolean binarySearch(int[] nums, int target, int left, int right) {
        if (left == right) {
            return nums[left] == target;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return true;
        }
        boolean leftSide = false, rightSide = false;
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                leftSide = binarySearch(nums, target, left, mid - 1);
            } else {
                leftSide = binarySearch(nums, target, mid + 1, right);
            }
        }
        if (nums[mid] <= nums[right]) {
            if (nums[mid] < target && target <= nums[right]) {
                rightSide = binarySearch(nums, target, mid + 1, right);
            } else {
                rightSide = binarySearch(nums, target, left, mid - 1);
            }
        }
        return leftSide || rightSide;
    }


}
