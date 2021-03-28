package swordtooffer;

import annotation.Level;
import annotation.Solved;
import util.ListNode;
import util.TreeNode;

import java.util.*;

/**
 * 简单题都在这里
 */
public class OfferCommon {

    /**
     * 返回二叉搜索树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * 寻找二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    public String replaceSpace(String s) {
        char[] arr1 = new char[s.length() * 3];
        char[] arr2 = s.toCharArray();
        int tag = 0;
        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] != ' ') {
                arr1[tag++] = arr2[i];
            } else {
                arr1[tag++] = '%';
                arr1[tag++] = '2';
                arr1[tag++] = '0';
            }
        }
        return new String(arr1, 0, tag);
    }


    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int tag = 0;
        int[] ans = new int[stack.size()];
        while (stack.size() > 0) {
            ans[tag++] = stack.pop();
        }
        return ans;
    }


    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int tag = 0;
        while (list.size() > 1) {
            tag = (tag + m - 1) % list.size();
            list.remove(tag);
        }
        return list.get(0);
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        int time = 0;
        while (nodeA != nodeB) {
            nodeA = nodeA.next;
            nodeB = nodeB.next;
            if (nodeA == null) {
                nodeA = headB;
                time++;
            }
            if (nodeB == null) {
                nodeB = headA;
            }
            if (time == 2) {
                return null;
            }
        }
        return nodeA;
    }


    /**
     * 二分
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        int tmp = 0;
        while (left <= right) {
            tmp = (left + right) / 2;
            if (nums[tmp] < target) {
                left = tmp + 1;
            } else if (nums[tmp] > target) {
                right = tmp - 1;
            } else {
                break;
            }
        }
        if (nums[tmp] != target) {
            return 0;
        }
        int res = 1;
        for (int i = 0; ; i++) {
            if (tmp + i < nums.length && nums[tmp + i] == target) {
                res++;
            } else {
                break;
            }
        }
        for (int i = 0; ; i++) {
            if (tmp - i >= 0 && nums[tmp + i] == target) {
                res++;
            } else {
                break;
            }
        }

        return res;
    }


    /**
     * 0 ~ n-1中缺失的数字
     * 二分法，思路转换了下
     */
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int tmp = (l + r) / 2;
            if (nums[tmp] == tmp) {
                l = tmp + 1;
            } else {
                r = tmp - 1;
            }
        }
        return l;
    }


    List<Integer> list = new ArrayList<>();

    public int kthLargest(TreeNode root, int k) {
        fillList(root);
        return list.get(list.size() - k);
    }

    public void fillList(TreeNode root) {
        if (root.left != null) {
            fillList(root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            fillList(root.right);
        }
    }


    public String reverseWords(String s) {
        s = s.trim();
        if ("".equals(s)) {
            return "";
        }
        String[] arr = s.split("");
        StringBuilder stringBuilder = new StringBuilder(arr[arr.length - 1]);
        for (int i = 1; i < arr.length; i++) {
            stringBuilder.append(" ").append(arr[arr.length - 1 - i]);
        }
        return stringBuilder.toString();
    }


    @Level(1)
    public boolean isStraight(int[] nums) {
        boolean[] arr = new boolean[14];
        for (int i = 0; i < nums.length; i++) {
            if (arr[nums[i]] && nums[i] != 0) {
                return false;
            } else {
                arr[nums[i]] = true;
            }
        }
        int left = 0, right = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]) {
                left = i;
                break;
            }
        }
        for (int j = arr.length - 1; j >= 1; j--) {
            if (arr[j]) {
                right = j;
                break;
            }
        }

        return right - left + 1 < 6;

    }


    @Level(3)
    @Solved(isSolved = false)
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        return numbers[left];

    }


    @Level(2)
    public int hammingWeight1(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    @Level(2)
    public int hammingWeight2(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= (n - 1);
        }
        return res;
    }


    @Level(1)
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head, next = pre.next;
        while (next != null) {
            if (next.val == val) {
                pre.next = next.next;
                break;
            }
            pre = next;
            next = next.next;
        }
        return head;

    }


    @Level(value = 2, message = "数组写法的越界注意")
    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (nums[left] % 2 != 0 && left < nums.length - 1) {
                left++;
            }
            while (nums[right] % 2 == 0 && right > 0) {
                right--;
            }
            if (left >= right) {
                break;
            }
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
        return nums;
    }


    @Level(value = 3, message = "想了一会儿")
    public int add(int a, int b) {
        while (b != 0) {
            //进位和
            int c = (a & b) << 1;
            //非进位和
            a ^= b;
            b = c;
        }
        return a;
    }


    @Level(value = 3, message = "LinkedHashMap的应用，entryset变为有序")
    public char firstUniqChar(String s) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            map.put(c, !map.containsKey(c));
        }
        //有序entry的应用
        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }
        return ' ';
    }


    @Level(1)
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            tail = tail.next;
        }
        ListNode result = head;
        while (tail != null) {
            tail = tail.next;
            result = result.next;
        }
        return result;
    }


    @Level(value = 2, message = "略微常见")
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode node = res;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int v2 = l2 == null ? Integer.MAX_VALUE : l2.val;
            if (v1 < v2) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
            node.next = new ListNode(Math.min(v1, v2));
            node = node.next;
        }
        return res.next;

    }


    @Level(value = 2, message = "略常见too")
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSym(root, root);
    }

    public boolean isSym(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val &&
                isSym(root1.left, root2.right) &&
                isSym(root1.right, root2.left);
    }


    /**
     * 1 2 3
     * 4 5 6
     * 7 8 9
     */
    @Level(value = 3, message = "提交时仍错误2次，越界判断+返回空数组（不是null）" +
            "利用四个变量来标注数组的打印位置")
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }
        int x1 = 0, y1 = matrix.length, x2 = 0, y2 = matrix[0].length;
        int count = 0;
        int[] res = new int[matrix.length * matrix[0].length];
        while (count < matrix.length * matrix[0].length) {
            for (int i = x2; i < y2; i++) {
                res[count++] = matrix[x1][i];
            }
            if (count >= matrix.length * matrix[0].length) {
                break;
            }
            x1++;
            for (int i = x1; i < y1; i++) {
                res[count++] = matrix[i][y2 - 1];
            }
            if (count >= matrix.length * matrix[0].length) {
                break;
            }
            y2--;
            for (int i = y2 - 1; i >= x2; i--) {
                res[count++] = matrix[y1 - 1][i];
            }
            if (count >= matrix.length * matrix[0].length) {
                break;
            }
            y1--;
            for (int i = y1 - 1; i >= x1; i--) {
                res[count++] = matrix[i][x2];
            }
            x2++;
        }
        return res;

    }


    @Level(value = 2, message = "31-1 用队列 数组存储就可")
    public int[] levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    @Level(value = 2, message = "思路转变一下，用两个队列存储即可")
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        while (!queue1.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            Queue<TreeNode> queue2 = new LinkedList<>();
            while (!queue1.isEmpty()) {
                TreeNode node = queue1.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    queue2.add(node.left);
                }
                if (node.right != null) {
                    queue2.add(node.right);
                }
            }
            queue1 = queue2;
            res.add(tmp);
        }
        return res;
    }


    @Level(value = 2, message = "之字形打印，稍微调整下即可")
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        int count = 0;
        while (!queue1.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            Deque<TreeNode> queue2 = new LinkedList<>();
            while (!queue1.isEmpty()) {
                if (count % 2 == 0) {
                    TreeNode node = queue1.pollFirst();
                    tmp.add(node.val);
                    if (node.left != null) {
                        queue2.add(node.left);
                    }
                    if (node.right != null) {
                        queue2.add(node.right);
                    }
                } else {
                    TreeNode node = queue1.pollLast();
                    tmp.add(node.val);
                    if (node.right != null) {
                        queue2.add(node.right);
                    }
                    if (node.left != null) {
                        queue2.add(node.left);
                    }
                }

            }
            queue1 = queue2;
            res.add(tmp);
            count++;
        }
        return res;
    }


    @Level(1)
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    @Level(value = 2, message = "参照答案的投票法，机智哦")
    public int majorityElement(int[] nums) {
        int res = nums[0], sum = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == res) {
                sum++;
            } else {
                if (sum == 0) {
                    res = nums[i];
                } else {
                    sum--;
                }
            }
        }
        return res;
    }


    @Level(value = 3,message = "优先队列，最小堆的使用，以及自己实现comparator")
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        if (k == 0) {
            return res;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                }
        );
        for (int i = 0; i < k; i++){
            queue.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.add(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }










    public static void main(String[] args) {

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        map.put('q', 1);
        map.put('w', 2);
        map.put('e', 3);

        PriorityQueue<Integer> queue = new PriorityQueue<>(5,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        queue.add(1);
        queue.add(7);
        queue.add(2);
        queue.add(4);
        queue.add(3);
        queue.poll();


        System.out.println();

    }


}
