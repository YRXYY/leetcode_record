package knowledge.oneVital;

/**
 * ��������߽�Ķ������⣬���ʼ�ұ߽��ȡʱ��+1
 */
public class Kokobanana {

    /**��Ŀ
     * N���㽶����i����piles[i]�����������뿪��HСʱ�������
     * koko���㽶�ٶ�K��/Сʱ��ÿСʱѡһ�ѣ����гԵ�K����һ������һ��
     * ��koko�ܳ��������㽶����С�ٶ�K
     *
     * [3,6,7,11],H=8
     * 4
     *
     * [30,11,23,4,20],H=5
     * 30
     */

    /**
     * һ��˼·��forѭ�����������ռ������������
     * ������Ƕ��ֲ��ҿ��Է������õı�־��
     * ��Ҫ�������С�ٶȣ����Կ�����һ��**�������߽�Ķ��ֲ���**��������������
     * �������ֲ��ң���ʱ�临�ӶȽ��� NlogN
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
     * ��������
     * һ���������i����weights[i]
     * ÿ��װ�ص��������ó������������������,������밴˳��װ
     * ����D�����������а���������Ҫ�Ĵ��������������
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


