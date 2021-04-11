package datastructure;

import annotation.Level;

import java.util.*;

public class PriorityQueueDemo {

    @Level(value = 4,message = "leetcode 239 滑动窗口最大值" +
            "优先队列")
    public int[] maxSlidingWindowAbandon(int[] nums, int k) {
        //TODO
        return null;
    }


    @Level(value = 4,message = "leetcode 239 滑动窗口最大值" +
            "单调队列deque" +
            "满足单调性的双端队列")
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        int[] res = new int[nums.length - k + 1];
        res[0] = deque.peekFirst();
        for (int i = k; i < nums.length; i++) {
            if (nums[i-k] == deque.peekFirst()) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i-k+1] = deque.peekFirst();
        }
        return res;
    }


    @Level(value = 4,message = "分块+预处理")
    public int[] maxSlidingWindow3(int[] nums, int k) {
        //TODO
        return null;
    }




    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int start = nums[0];
        int end = start;
        List<String> res = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 == end) {
                end++;
            } else {
                if (start == end) {
                    res.add(String.valueOf(start));
                } else {
                    res.add(start + "->" + end);
                }
                start = nums[i];
                end  = start;
            }
        }
        if (start == end) {
            res.add(String.valueOf(start));
        } else {
            res.add(start + "->" + end);
        }
        return res;
    }

    /**
     * 丑数2：给你一个整数 n ，请你找出并返回第 n 个 丑数 。
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
     */
    public int nthUglyNumber1(int n) {
        int[] args = new int[]{2, 3, 5};
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        long cur = 0L;
        for (int i = 0; i < n; i++) {
            cur = queue.poll();
            set.add(cur);
            for (int j = 0; j < 3; j++) {
                long next = cur * args[j];
                if (set.add(next)) {
                    queue.add(next);
                }
            }
        }
        return (int)cur;
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(num2, Math.min(num3, num5));
            if (num2 == dp[i]) {
                p2++;
            }
            if (num3 == dp[i]) {
                p3++;
            }
            if (num5 == dp[i]) {
                p5++;
            }
        }
        return dp[n];
    }




    public static void main(String[] args) {
        // 默认从小到大
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        System.out.println(queue.poll());
        // 1
        System.out.println(queue.poll());
        // 2
        System.out.println(queue.poll());
        System.out.println();


        Deque<Integer> deque = new LinkedList<>();
        deque.add(1);
        deque.add(2);
        deque.add(4);
        deque.add(3);
        deque.add(7);

        System.out.println();


    }

}
