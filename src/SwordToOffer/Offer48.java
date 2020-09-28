package SwordToOffer;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长不含重复字符的子字符串
 */
public class Offer48 {

    //双指针+哈希表
    public static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int index = 0,res = 1;
        HashMap<Character,Integer> map = new HashMap<>();
        map.put(arr[0],0);
        for(int i=1;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],i);
            }else{
                if(map.get(arr[i])>=index){
                    index = map.get(arr[i])+1;
                }
                map.put(arr[i],i);
            }
            if(i-index+1>res)
                res = i-index+1;
        }
        return res;
    }

    //动态规划+哈希表
    public int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for(int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }



    public static void main(String[] args) {
        lengthOfLongestSubstring("abcabcbb");
    }
}
