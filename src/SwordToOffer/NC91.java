package SwordToOffer;

import java.util.Arrays;
import java.util.Stack;

public class NC91 {

    /**
     * 最长递增子序列个数
     *[1,3,5,4,7]       res:2
     * [1, 3, 4, 7] 和[1, 3, 5, 7]
     */
    public static void main(String[] args) {
        NC91 nc = new NC91();
        nc.LIS(new int[] {
                2,1,5,3,6,4,8,9,7
        });
    }

    public int findNumberOfLIS(int[] nums){
        int N = nums.length;
        if(N<=1) return N;
        int[] lengths = new int[N]; //length[i[ = length of longest ending in nums[i]
        int[] counts = new int[N];  //counts[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        int len = 0,res = 1;
        for(int i = 1; i < N; i++){
            for(int j = 0; j <i; j++)if(nums[j]<nums[i]){
                if(lengths[i]<=lengths[j]){
                    lengths[i] = lengths[j]+1;
                    counts[i] = counts[j];
                }else if(lengths[i]==lengths[j]+1){
                    counts[i]+=counts[j];
                }
            }
            if(lengths[i]>len){
                len = lengths[i];
                res = counts[i];
            }else if(lengths[i]==len){
                res += counts[i];
            }
        }
        return res;
    }


    /**
     * 打印最长子序列
     * 如果个数相同，打印字典序最小的
     */
    //超时
    public int[] LIS1 (int[] arr){
        int[] length = new int[arr.length];
        int[] pre = new int[arr.length];

        int max = 0,index = 0;
        for(int i = 1; i < arr.length; i++){
            for(int j=0;j<i;j++)if(arr[j]<arr[i]){
                if(length[i]<=length[j]){
                    length[i] = length[j]+1;
                    pre[i] = j;
                }else if(length[i] == length[j]+1&&arr[j]<arr[pre[i]]){
                    pre[i] = j;
                }
            }
            if(max<length[i]){
                max = length[i];
                index = i;
            }
        }
        Stack<Integer> stack = new Stack<Integer> ();
        while(pre[index]!=0){
            stack.push(arr[index]);
            index = pre[index];
        }
        stack.push(arr[index]);
        int[] res = new int[stack.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = stack.pop();
        }
        return res;
    }


    public int[] LIS (int[] arr) {
        int n = arr.length;
// 列表的最大子序列 下标从1开始
        int[] end = new int[n + 1];
// 存储每个元素的最大子序列个数
        int[] dp = new int[n];
        int len = 1;
//子序列的第一个元素默认为数组第一个元素
        end[1] = arr[0];
//第一个元素的最大子序列个数肯定是1
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            if (end[len] < arr[i]) {
//当arr[i] > end[len] 时 arr[i]添加到 end后面
                end[++len] = arr[i];
                dp[i] = len;
            } else {
// 当前元素小于end中的最后一个元素 利用二分法寻找第一个大于arr[i]的元素
// end[l] 替换为当前元素 dp[]
                int l = 0;
                int r = len;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (end[mid] >= arr[i]) {
                        r = mid - 1;
                    } else l = mid + 1;
                }
                end[l] = arr[i];
                dp[i] = l;
            }
        }

        int[] res = new int[len];
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == len) {
                res[--len] = arr[i];
            }
        }
        return res;
    }


}
