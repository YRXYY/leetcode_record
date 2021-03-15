package knowledge.oneVital;

/**
 * 搜索最左边界的二分问题，其初始右边界获取时需+1
 */
public class Kokobanana {

    /**题目
     * N堆香蕉，第i堆有piles[i]根，警卫已离开，H小时后回来。
     * koko吃香蕉速度K根/小时，每小时选一堆，从中吃掉K根，一次最多吃一堆
     * 求koko能吃完所有香蕉的最小速度K
     *
     * [3,6,7,11],H=8
     * 4
     *
     * [30,11,23,4,20],H=5
     * 30
     */

    /**
     * 一般思路是for循环，在连续空间的线性搜索。
     * 而这就是二分查找可以发挥作用的标志，
     * 而要求的是最小速度，所以可以用一个**搜索左侧边界的二分查找**来代替线性搜索
     * 借助二分查找，将时间复杂度降到 NlogN
     */

    public int minSpeed(int[] banana, int H) {
        int left = 0, right = getMax(banana) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(banana, H, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int getMax(int[] arr) {
        int max = 0;
        for (int n: arr) {
            max = Math.max(max, n);
        }
        return max;
    }

    public boolean canFinish(int[] banana, int H, int K) {
        int time = 0;
        for (int n : banana) {
            time += timeOf(n, K);
        }
        return time <= H;
    }

    public int timeOf(int bananas, int speed) {
        return bananas/speed + (bananas % speed == 0 ? 0 : 1);
    }


    public static void main(String[] args) {
        int[] bananas = new int[]{30,11,23,4,20};
//        System.out.println(new Kokobanana().minSpeed(bananas, 5));
        int[] weights = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(new Kokobanana().minShip(weights, 5));

    }


    /**
     * 运输问题
     * 一组包裹，第i个重weights[i]
     * 每次装载的重量不得超过船的最大运载重量,货物必须按顺序装
     * 返回D天内运完所有包裹，所需要的船的最低运载能力
     */
    public int minShip(int[] weights, int D) {
        int left = getMax(weights);
        int right = getSum(weights) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int getSum(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum;
    }

    public boolean canShip(int[] weights, int D, int ship) {
        int days = 0;
        int count = 0, shipTemp = 0;
        while (count < weights.length) {
            if (shipTemp + weights[count] <= ship) {
                shipTemp += weights[count];
            } else {
                days++;
                shipTemp = 0;
            }
            count++;
        }
        return days <= D;
    }

    boolean canFinishShip(int[] weights, int D, int cap) {
        int i = 0;
        for (int day = 0; day < D; day++) {
            int maxCap = cap;
            while ((maxCap -= weights[i]) >= 0) {
                i++;
                if (i == weights.length) {
                    return true;
                }
            }
        }
        return false;
    }


}


