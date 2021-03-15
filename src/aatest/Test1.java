package aatest;

import annotation.Level;
import util.ListNode;
import util.TreeNode;

import java.util.*;
import java.util.function.BiConsumer;

public class Test1 {

    public static void main(String[] args) {

        System.out.println(new Test1().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));


    }


    @Level(value = 2, message = "细节引起的错误 逻辑上的")
    public int removeElement(int[] nums, int val) {
        Arrays.sort(nums);
        int replace = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                nums[i] = nums[replace--];
            }
        }
        return nums.length - (nums.length - 1 - replace);
    }


    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        int state = 0, count = 0, x = 0, y = 0;
        ArrayList<Integer> list = new ArrayList<>();
        boolean[][] tag = new boolean[matrix.length][matrix[0].length];
        while (count < matrix.length * matrix[0].length) {
            list.add(matrix[x][y]);
            tag[x][y] = true;
            switch (state) {
                case 0:
                    if (x == matrix.length || tag[x + 1][y]) {
                        state = 1;
                        y++;
                    } else {
                        x++;
                    }
                    break;
                case 1:
                    if (y == matrix[0].length || tag[x][y + 1]) {
                        state = 2;
                        x--;
                    } else {
                        y++;
                    }
                    break;
                case 2:
                    if (x == 0 || tag[x - 1][y]) {
                        state = 3;
                        y--;
                    } else {
                        x--;
                    }
                    break;
                case 3:
                    if (y == 0 || tag[x][y - 1]) {
                        state = 0;
                        x++;
                    } else {
                        y--;
                    }
                    break;
                default:
                    break;
            }
            count++;
        }
        return list;
    }


    //  "terrortimelimi"
    public static void rotateString(char[] str, int offset) {
        if (offset == 0 || str.length == 0) {
            return;
        }
        offset %= str.length;
        char[] temp = new char[offset];
        for (int i = offset - 1; i >= 0; i--) {
            temp[i] = str[str.length - offset + i];
        }
        for (int i = str.length - 1; i > offset - 1; i--) {
            str[i] = str[i - offset];
        }
        for (int i = 0; i < offset; i++) {
            str[i] = temp[i];
        }
    }

    /**
     * 水仙花
     */
    public List<Integer> getNarcissisticNumbers(int n) {
        int num = (int) Math.pow(10, n - 1);
        List<Integer> res = new ArrayList<>();
        for (int i = num + 1; i < Math.pow(10, n); i++) {
            int temp = 0;
            String str = String.valueOf(i);
            for (int m = 0; m < str.length(); m++) {
                temp += Math.pow(str.charAt(m) - 48, n);
                if (temp > i) {
                    break;
                }
            }
            if (temp == i) {
                res.add(i);
            }
        }
        return res;
    }


    public int search(int[] nums, int target) {
        int res;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            res = (left + right) / 2;
            if (nums[res] == target) {
                if (res == 0 || nums[0] == target) {
                    return 0;
                }
                while (nums[--res] == target) {
                }
                return res + 1;
            } else if (nums[res] > target) {
                right = res - 1;
            } else {
                left = res + 1;
            }
        }
        return -1;
    }

    //第k大，排序后，从小到大，第n-k+1
    //1,4,3,5,6,7  3 正数第4   【3】
    public int findKthi(int[] arr, int n, int K) {
        K = n - K + 1;
        int p = 0;
        int left = 0, right = n - 1;
        while (p != K - 1) {
            p = sort(arr, left, right);
            if (p < K - 1) {
                left = p + 1;
            } else if (p > K - 1) {
                right = p - 1;
            } else {
                return arr[p];
            }
        }
        return arr[p];
    }

    public int sort(int[] arr, int left, int right) {
        int i = left, j = right + 1;
        while (i < j) {
            while (arr[++i] < arr[left]) {
                if (i == right) {
                    break;
                }
            }
            while (arr[--j] > arr[left]) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, left, j);
        return j;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS (String str1, String str2) {
        String result = "";
        int start = 0, end = 1;
        while (end < str2.length()) {
            String tmp = str2.substring(start, end);
            if (str1.contains(tmp)) {
                result = tmp;
            } else {
                start++;
            }
            end++;
        }
        return result;
    }


    public int[] MySort (int[] arr) {
        quickSort(arr, 0, arr.length-1);
        return arr;
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int j = partition(arr, low, high);
            quickSort(arr, low, j - 1);
            quickSort(arr, j + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int i = low, j = high + 1;
        int v = arr[low];
        while (true) {
            while (arr[++i] < v) {
                if (i == high) {
                    break;
                }
            }
            while (arr[--j] > v) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, j, low);
        return j;
    }

}










