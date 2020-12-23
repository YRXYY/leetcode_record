package swordtooffer;

import annotation.Level;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Offer59 {


    /**
     * 滑动窗口的最大值-1
     * 明明是单调栈简单变换一下，变成单调队列，却不会简单变换
     * 说明还不熟练，需要练习
     * 语言的运用也不够熟练
     */
    @Level(value = 3)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);

        }
        res[0] = deque.peekFirst();
        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }


}

@Level(value = 3)
/**
 * 栈 也是对应的 单调栈来解决问题
 */
class MaxQueue {

    Queue<Integer> queue;
    Deque<Integer> deque;

    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekLast();
    }

    public void push_back(int value) {
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.removeLast();
        }
        deque.addLast(value);
        queue.add(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        } else {
            if (deque.peekFirst().equals(queue.peek())) {
                deque.removeFirst();
            }
            return queue.poll();
        }
    }
}
