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
