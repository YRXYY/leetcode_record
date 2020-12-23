package swordtooffer;

import annotation.Level;

public class Offer62 {

    /**
     * java解决约瑟夫环的问题 （数学问题）
     * 详解见
     * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
     */
    @Level(value = 4)
    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

}
