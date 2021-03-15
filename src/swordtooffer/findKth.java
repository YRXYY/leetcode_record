package swordtooffer;

/**
 * 寻找第K大的数
 * 有用到快排 分治思想
 *
 * 【1, 4, 5, 2, 2, 7】  6   3
 * res: 4
 *
 *
 */
public class findKth {

    public int findKth(int[] a, int n, int K) {
        //倒着数的 第K - 1，则正着数的 n - K + 1
        K = n - K + 1;
        int p = 0;
        int left = 0, right = n - 1;
        while (p != K - 1) {
            p = find(a, left, right);
            if (p > K - 1) {
                right = p - 1;
            } else {
                left = p + 1;
            }
        }
        return a[p];
    }

    public int find(int[] arr, int left, int right) {
        int i = left, j = right + 1;
        int v = arr[left];
        while (true) {
            while (arr[++i] < v) {
                if (i == right) {
                    break;
                }
            }
            while (arr[--j] > v) {
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

    public void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
