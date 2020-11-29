package datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 树状数组
 * 查询和修改复杂度均为logn
 * 用于数组的单点修改 和 区间求和
 */
class BIT{
    int[] tree;
    int n;
    public BIT(int n){
        this.n = n;
        this.tree = new int[n+1];
    }

    /**
     * 取出二进制表示中最低位置的1
     */
    public static int lowbit(int x){
        return x&(-x);
    }

    public void update(int x,int d){
        while(x<=n){
            tree[x]+=d;
            x+=lowbit(x);
        }
    }

    public int query(int x){
        int ans = 0;
        while(x!=0){
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }
}
public class TreeArray {

    public static void main(String[] args) {
        int a = BIT.lowbit(8);
        System.out.println(a);
    }

    /**
     * leetcode 493
     */
    public int reversePairs(int[] nums){
        Set<Long> allNumbers = new TreeSet<Long>();
        for(int x:nums){
            allNumbers.add((long)x);
            allNumbers.add((long)x*2);
        }
        //利用哈希表进行离散化
        Map<Long,Integer> values = new HashMap<Long,Integer>();
        int idx = 0;
        for(long x:allNumbers){
            values.put(x,idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for(int i=0;i<nums.length;i++){
            int left = values.get((long)nums[i]*2),right=values.size()-1;
           // ret += bit.query
        }


        return 0;
    }



}








