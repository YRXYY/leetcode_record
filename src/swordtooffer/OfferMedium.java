package swordtooffer;

import annotation.Level;
import annotation.Solved;
import util.*;
import util.Node;

import java.util.*;

/**
 * 中等题集合
 */
public class OfferMedium {

    @Level(value = 3, message = "Offer07 重建二叉树" +
            "重点在于迭代思路，以及参数变化部分，画图就跟易懂一些")
    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd) {
        if (preBegin > preEnd) {
            return null;
        }
        int rootVal = preorder[preBegin];
        TreeNode root = new TreeNode(rootVal);
        if (preBegin == preEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inBegin;
            int rightNodes = inEnd - rootIndex;
            TreeNode leftSubTree = buildTree(preorder, preBegin + 1, preBegin + leftNodes, inorder, inBegin, rootIndex - 1);
            TreeNode rightSubTree = buildTree(preorder, preEnd - rightNodes + 1, preEnd, inorder, rootIndex + 1, inEnd);
            root.left = leftSubTree;
            root.right = rightSubTree;
            return root;
        }

    }


    @Level(value = 5, message = "Offer07 重建二叉树" +
            "迭代法，之后再看，先把题目扫了")
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return null;
    }


    @Level(value = 3, message = "Offer14 剪绳子" +
            "数学知识推导")
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        // n = 3a + b
        int a = n / 3;
        int b = n % 3;
        switch (b) {
            case 0:
                return (int) Math.pow(3, a);
            case 1:
                return (int) Math.pow(3, a - 1) * 2 * 2;
            case 2:
                return (int) Math.pow(3, a) * 2;
            default:
                return 0;
        }
    }

    @Level(value = 3, message = "Offer14-2 剪绳子" +
            "进阶版，涉及大数取余，之后再看")
    public int cuttingRope2(int n) {
        return 0;
    }

    @Level(value = 4, message = "Offer16 实现Math.pow" +
            "看的题解，学的推导和写代码过程，堪称优雅")
    public double myPow(double x, int n) {
        //==============前部分处理===============
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        // 因为int的范围是 -2147483648 ~ 2147483647，在变为相反数时 -2147483648会越界，先用long存储
        long b = n;
        double res = 1;
        if (n < 0) {
            x = 1 / x;
            b = -1 * b;
        }
        //==============处理部分=================
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }


    @Level(value = 4, message = "判断是不是数字" +
            "有限状态机解决，二轮再写")
    public boolean isNumber(String s) {
        return false;
    }

    @Level(value = 3, message = "Offer31 栈的压入弹出是否合理" +
            "自己实现的，效率较低 8%")
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        int indexo = 0, indexu = 0;
        while (indexo < popped.length) {
            // 判断有无pop
            if (!stack.isEmpty() && stack.peek() == popped[indexo]) {
                stack.pop();
                set.remove(popped[indexo]);
                indexo++;
                continue;
            } else if (set.contains(popped[indexo])) {
                return false;
            }
            //无pop则push
            if (indexu == pushed.length) {
                return false;
            }
            stack.push(pushed[indexu]);
            set.add(pushed[indexu]);
            indexu++;

        }
        return true;
    }


    @Level(value = 3, message = "经思路提醒，省去了无用的set集合，提升至94.9%")
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int indexu = 0, indexo = 0;
        while (indexo < popped.length) {
            if (!stack.isEmpty() && stack.peek() == popped[indexo]) {
                stack.pop();
                indexo++;
                continue;
            }
            if (indexu >= pushed.length) {
                return false;
            }
            stack.push(pushed[indexu]);
            indexu++;
        }
        return true;
    }


    @Level(value = 3, message = "效果相同，更优雅的写法")
    public boolean validateStackSequence3(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return index == popped.length;
    }


    @Level(value = 4, message = "Offer33 确认二叉搜索树的后序遍历" +
            "利用后续遍历的特点解决的")
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    public boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }


    @Level(value = 4, message = "Offer35 复杂链表的复制" +
            "map<Node,Node> 借鉴的写法，很优雅")
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        //复制节点
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        //复制连接
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    @Level(value = 4, message = "Offer35 复杂链表的复制" +
            "链表的 拼接 + 拆分")
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node tmp = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = tmp;
            cur = tmp;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            } else {
                cur.next.random = null;
            }
            cur = cur.next.next;
        }
        Node res = head.next;
        Node node1 = head, node2 = head.next;
        while (true) {
            node1.next = node2.next;
            node1 = node1.next;
            if (node1 == null) {
                node2.next = null;
                break;
            }
            node2.next = node1.next;
            node2 = node2.next;
        }
        return res;
    }

    @Level(value = 4, message = "Offer38 字符串的排列" +
            "集合+swap 避免了新建对象带来的开销问题 ===剪枝===" +
            "半抄的答案，淦")
    @Solved(isSolved = false)
    List<String> list = new ArrayList<>();
    char[] c;

    public String[] permutation(String s) {
        c = s.toCharArray();
        stringDfs(0);
        return list.toArray(new String[list.size()]);
    }

    public void stringDfs(int x) {
        if (x == c.length - 1) {
            list.add(String.valueOf(c));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            // 利用set剪枝
            if (set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            // 用填入数字代替剪枝
            swap(i, x);
            // 开启下一位的swap
            stringDfs(x + 1);
            // 恢复交换，换其他的到位置x
            swap(i, x);
        }
    }

    public void swap(int m, int n) {
        char tmp = c[m];
        c[m] = c[n];
        c[n] = tmp;
    }


    @Level(message = "烦躁，下次一定系列")
    public int findNthDigit1(int n) {
        if (n < 10) {
            return n;
        }
        n++;
        int len = 1;
        int numberNum = 10;
        int lenSum = 10;
        int addnum = 0;
        while (lenSum < n) {
            addnum = 9 * numberNum;
            numberNum *= 10;
            len++;
            lenSum += len * addnum;
        }
        lenSum -= len * addnum;
        int numplace = (n - lenSum) / len;
        int strplace = (n - len) % len;
        StringBuilder stringBuilder = new StringBuilder("1");
        for (int i = 1; i < len; i++) {
            stringBuilder.append("0");
        }
        int startNum = Integer.valueOf(stringBuilder.toString());
        startNum += numplace + 1;
        return String.valueOf(startNum).charAt(strplace) - 48;


    }


    @Level(value = 4, message = "Offe44 数字序列中的某一位数字" +
            "想出来了，但具体实现起来很糟心，大佬的代码就很舒心，包括思路都不一样" +
            "代码还需练，I can do it" +
            "同时肯定双考虑long")
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) {
            n -= count;
            digit++;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }


    @Level(value = 4, message = "Offer45 把数组拍成最小的数" +
            "本质上是个排序问题，自实现的快排未完成，二轮再写" +
            "利用内置快排函数，可实现Arrays.sort()，利用lambda函数传入Comparator")
    public String minNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, (x, y) -> ((x + y).compareTo(y + x)));
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : arr) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }


    @Level(value = 4, message = "Offer46 把数字翻成字符串" +
            "dp 滚动数组")
    public int translateNum(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int p = 1, q = 1, r = 1;
        for (int i = 1; i < arr.length; i++) {
            String tmp = arr[i - 1] + "" + arr[i] + "";
            if (Integer.valueOf(tmp) < 26 && Integer.valueOf(tmp) > 9) {
                r = p + q;
            } else {
                r = q;
            }
            p = q;
            q = r;
        }
        return r;
    }


    @Level(value = 3, message = "Offer47 礼物的最大值" +
            "动态数组，原地修改，牛皮哦")
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    @Level(value = 4, message = "Offer49 丑数" +
            "dp 重复子问题，问题的分析" +
            "需要二刷，真鸡儿烦")
    @Solved(isSolved = false)
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }


    @Level(value = 4, message = "Offer60 n个骰子的点数" +
            "重点是思路的理解把，代码永远可以是简单的，其次的？")
    public double[] dicesProbability(int n) {
        double[] pre = {1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d};
        for (int i = 2; i <= n; i++) {
            double[] cur = new double[5 * i + 1];
            for (int j = 0; j < cur.length; j++) {
                for (int m = 0; m <= j & m < pre.length; m++) {
                    if (j - m >= 0 && j - m <= 5) {
                        cur[j] += pre[m] * 1 / 6d;
                    }
                }
            }
            pre = cur;
        }
        return pre;
    }


    @Level(value = 4, message = "Offer66 构建乘积数组" +
            "本质还是对重复子问题的解决，二刷了。。。")
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return new int[]{};
        }
        if (a.length == 2) {
            return new int[]{a[1], a[0]};
        }
        int[] up = new int[a.length - 1];
        int[] down = new int[a.length - 1];
        up[a.length - 2] = a[a.length - 1];
        for (int i = a.length - 3; i >= 0; i--) {
            up[i] = up[i + 1] * a[i + 1];
        }
        down[0] = a[0];
        for (int i = 1; i < a.length - 1; i++) {
            down[i] = down[i - 1] * a[i];
        }
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                b[i] = up[i];
            } else if (i == a.length - 1) {
                b[i] = down[down.length - 1];
            } else {
                b[i] = up[i] * down[i - 1];
            }
        }
        return b;
    }


    @Level(value = 4, message = "Offer67" +
            "越晚开始写代码，越早完成 先考虑完成再写" +
            "long.intValue()")
    public int strToInt(String str) {
        str = str.trim().split(" ")[0];
        if (str == null || "".equals(str)) {
            return 0;
        }
        // 是否是负数
        boolean flag = false;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            if (str.charAt(0) == '-') {
                flag = true;
            }
            str = str.substring(1);
            if (str.equals("2147483648")) {
                return -2147483648;
            }
        }
        while (str.length() != 0 && str.charAt(0) == '0') {
            str = str.substring(1);
        }
        if (str == "") {
            return 0;
        }
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > '9' || array[i] < '0') {
                if (i == 0) {
                    return 0;
                } else {
                    array = str.substring(0, i).toCharArray();
                    break;
                }
            }
        }
        if (array.length > 10) {
            return flag ? -2147483648 : 2147483647;
        }
        Long res = 0L;
        for (int i = array.length - 1; i >= 0; i--) {
            res += (long) ((array[i] - '0') * Math.pow(10, array.length - 1 - i));
            if (res > 2147483647) {
                return flag ? -2147483648 : 2147483647;
            }
        }
        return flag ? -1 * res.intValue() : res.intValue();

    }


    boolean tag = false;

    public boolean isMatch(String s, String p) {
        return isValid(0, 0, s, p);
    }

    public boolean isValid(int sidx, int pidx, String s, String p) {
        if (tag) {
            return true;
        }
        // judge if succeed
        if (pidx >= p.length() && sidx >= s.length()) {
            tag = true;
            return tag;
        }
        if (pidx >= p.length()) {
            return false;
        }
        if (sidx >= s.length()) {
            // s over, but p = a*b*c*  length may= 0
            int validLen = 0;
            for (int i = pidx; i < p.length(); i++) {
                if (p.charAt(i) == '*') {
                    validLen--;
                } else {
                    validLen++;
                }
            }
            tag = validLen == 0;
            return validLen == 0;
        }
        // sidx pidx < length()
        if (pidx < p.length() - 1 && p.charAt(pidx + 1) != '*' ||
                pidx == p.length() - 1) {
            // pidx + 1 no *  regular regex
            if (p.charAt(pidx) == '.') {
                return isValid(sidx + 1, pidx + 1, s, p);
            }
            if (p.charAt(pidx) == s.charAt(sidx)) {
                return isValid(sidx + 1, pidx + 1, s, p);
            } else {
                return false;
            }
        } else if (pidx < p.length() - 1 && p.charAt(pidx + 1) == '*') {
            // .* combination
            if (p.charAt(pidx) == '.') {
                for (int i = sidx - 1; i < s.length(); i++) {
                    isValid(i + 1, pidx + 2, s, p);
                }
            } else {
                for (int i = sidx - 1; i < s.length(); i++) {
                    if (i > sidx - 1 && s.charAt(i) != p.charAt(pidx)) {
                        break;
                    }
                    isValid(i + 1, pidx + 2, s, p);
                }
            }

        }
        return tag;
    }


    @Level(message = "Offer43 1~n整数中1出现的次数")
    int[] nums;

    public int countDigitOne(int n) {
        nums = new int[String.valueOf(n).length()];
        // 完整的1~9
        nums[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = (int) Math.pow(10, i) + 10 * nums[i - 1];
        }
        int res = 0;
        String number = String.valueOf(n);
        for (int i = 0; i < number.length(); i++) {

        }

        return 0;
    }

    public int count(int n) {
        if (n < 10) {
            return nums[0];
        }
        int num = (int) Math.pow(10, String.valueOf(n).length() - 1);
        return num + (n / num) * count(num);
    }

    /**
     * @param A      int整型一维数组
     * @param target int整型
     * @return int整型
     * 给出一个转动过的有序数组，你事先不知道该数组转动了多少
     * 0 1 2 3 4 5 6 7    = >  4 5 6 7 0 1 2 3
     */
    public int search(int[] A, int target) {
        int num = 0;
        int left = 0, right = A.length - 1;
        while (left <= right) {
            num = (left + right) >> 1;
            if (A[num] == target) {
                return num;
            } else if (A[num] < target) {
                if ((A[num] >= A[0] && target >= A[0]) || (A[num] < A[0] && target < A[0])) {
                    left = num + 1;
                } else {
                    right = num - 1;
                }
            } else {
                if ((A[num] >= A[0] && target >= A[0]) || (A[num] < A[0] && target < A[0])) {
                    right = num - 1;
                } else {
                    left = num + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 给定数组arr，设长度为n，输出arr的最长递增子序列
     * retrun the longest increasing subsequence
     *
     * @param arr int整型一维数组 the array
     * @return int整型一维数组
     * [2,1,5,3,6,4,8,9,7]
     * [1,3,4,8,9]
     * [2,1,5,3,6,4,8,9,7,8,9]
     * [1,3,4,7,8,9]
     * 基本功能实现，还是有些问题
     */
    public int[] LIS(int[] arr) {
        if (arr == null) {
            return null;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] size = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && deque.peekLast() >= arr[i]) {
                deque.pollLast();
            }
            deque.addLast(arr[i]);
            size[i] = deque.size();
        }
        int num = 0;
        for (int i = 0; i < size.length; i++) {
            if (size[i] > num) {
                num = size[i];
            }
        }
        deque = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && deque.peekLast() >= arr[i]) {
                deque.pollLast();
            }
            deque.addLast(arr[i]);
            if (num == deque.size()) {
                break;
            }
        }
        int[] res = new int[deque.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = deque.pollFirst();
        }
        return res;
    }


    //普通方法，不可取
    public ListNode mergeKListsLow(ArrayList<ListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }
        for (int i = lists.size() - 1; i >= 0; i--) {
            if (lists.get(i) == null) {
                lists.remove(i);
            }
        }
        ListNode res = new ListNode(0);
        ListNode node = res;
        while (lists.size() > 0) {
            int tempvalue = -Integer.MAX_VALUE;
            int temp = -1;
            for (int i = 0; i < lists.size(); i++) {
                if (temp == -1 || lists.get(i).val < tempvalue) {
                    temp = i;
                    tempvalue = lists.get(i).val;
                }
            }
            if (temp == -Integer.MAX_VALUE) {
                break;
            }
            node.next = lists.get(temp);
            node = node.next;
            if (lists.get(temp).next == null) {
                lists.remove(temp);
            } else {
                lists.set(temp, lists.get(temp).next);
            }
        }


        return res.next;
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }
        return merge(lists, 0, lists.size() - 1);
    }

    public ListNode merge(ArrayList<ListNode> lists, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            ListNode l = merge(lists, left, mid);
            ListNode r = merge(lists, mid + 1, right);
            return mergeTwo(l, r);
        }
        return lists.get(left);
    }

    public ListNode mergeTwo(ListNode l, ListNode r) {
        if (l == null) {
            return r;
        }
        if (r == null) {
            return l;
        }
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l != null || r != null) {
            int num1 = l == null ? Integer.MAX_VALUE : l.val;
            int num2 = r == null ? Integer.MAX_VALUE : r.val;
            node.next = num1 < num2 ? l : r;
            node = node.next;
            if (num1 < num2) {
                l = l == null ? null : l.next;
            } else {
                r = r == null ? null : r.next;
            }
        }
        return head.next;
    }

    /**
     * 螺旋矩阵
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int n0 = -1, m0 = 0;
        int x = 0, y = 0;
        int count = 0;
        int status = 0;
        List<Integer> result = new ArrayList<>();
        while (count < matrix.length * matrix[0].length) {
            count++;
            result.add(matrix[x][y]);
            switch (status) {
                case 0:
                    y++;
                    if (y == n) {
                        n--;
                        y--;
                        status = 1;
                        x++;
                    }
                    break;
                case 1:
                    x++;
                    if (x == m) {
                        m--;
                        x--;
                        status = 2;
                        y--;
                    }
                    break;
                case 2:
                    y--;
                    if (y == n0) {
                        n0++;
                        y++;
                        status = 3;
                        x--;
                    }
                    break;
                case 3:
                    x--;
                    if (x ==  m0) {
                        m0++;
                        x++;
                        status = 0;
                        y++;
                    }
                    break;
            }
        }
        return result;
    }

    /**
     * 螺旋矩阵2
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int x = 0, y = 0;
        int m1 = n, n1 = n;
        int m0 = 0, n0 = -1;
        int count = 1;
        int status = 0;
        while (count <= n*n) {
            res[x][y] = count;
            count++;
            switch (status) {
                case 0 :
                    y++;
                    if (y == n1) {
                        y--;n1--;
                        status = 1;
                        x++;
                    }
                    break;
                case 1 :
                    x++;
                    if (x == m1) {
                        x--;m1--;
                        status = 2;
                        y--;
                    }
                    break;
                case 2:
                    y--;
                    if (y == n0) {
                        y++;n0++;
                        status = 3;
                        x--;
                    }
                    break;
                case 3:
                    x--;
                    if (x == m0) {
                        x++;m0++;
                        status = 0;
                        y++;
                    }
                    break;
            }
        }
        return res;
    }



    /**
     * 字符串相乘
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        // 是否进位
        int tag;
        List<Integer> list = new ArrayList<>();
        for (int i = arr1.length - 1; i >= 0; i--) {
            tag = 0;
            for (int j = arr2.length - 1; j >= 0; j--) {
                int tmp = (arr1[i] - 48) * (arr2[j] - 48) + tag;
                if (tmp >= 10) {
                    tag = tmp / 10;
                    tmp %= 10;
                } else {
                    tag = 0;
                }
                int target = arr1.length + arr2.length - 2 - i - j;
                if (list.size() > (target)) {
                    int tmp2 = list.get(target) + tmp;
                    if (tmp2 >= 10) {
                        tag++;
                        tmp2 -= 10;
                    }
                    list.remove(target);
                    list.add(target, tmp2);
                } else {
                    list.add(tmp);
                }
            }
            if (tag > 0) {
                list.add(tag);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {

        System.out.println(new OfferMedium().multiply("9", "9"));

    }
}
