package swordtooffer.stacks;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class SrackDemo {

    public int trapStack(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int water = Math.min(height[left], height[i]);
                res += (i - left - 1) * (water - height[top]);
            }
            stack.push(i);
        }
        return res;
    }

}
