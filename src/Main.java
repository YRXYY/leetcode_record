import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        // main.test11();
        int a = 15 & 1;
        int b = 9 & 2;
        int c = 9 & 1;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }


    public int[] constructArr(int[] a) {
        if(a.length==0) return new int[0];
        int[] b = new int[a.length];



    }















    /**
     * 异或操作，多看看
     */
    public int[] singleNumbers(int[] nums) {
        int k = 0;
        for (int num : nums) {
            k ^= num;
        }
        //找到mask分组
        int num = 1;
        while ((k & num) == 0) {
            num <<= 1;
        }
        int k1 = 0, k2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & num) == 0)
                k1 ^= nums[i];
            else
                k2 ^= nums[i];
        }

        return new int[]{k1, k2};
    }


    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < count.length; i++) {
                count[i] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0;
        int m = 3;
        for (int i = 0; i < nums.length; i++) {
            if(count[i]%m==1){
                res+=Math.pow(2,i);
            }
        }
        return res;

    }


    List<Integer> res = new ArrayList<Integer>();
    boolean[][] visited;
    int len = 0;
    int tag = 0;

    public void test1111() {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        char[][] arr = new char[Integer.valueOf(s[0])][Integer.valueOf(s[1])];
        visited = new boolean[arr.length][arr[0].length];
        len = arr.length * arr[0].length;
        for (int i = 0; i < arr.length; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'X') {
                    tag = 0; //无        1  有一样的路径   2 更短
                    dfs(arr, i, j, 0, i, j);
                    if (tag == 1) {
                        res.add(i);
                        res.add(j);
                    } else if (tag == 2) {
                        res = new ArrayList<>();
                        res.add(i);
                        res.add(j);
                    }
                }

            }
        }
        if (len == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(len);
            for (int i = 0; i < res.size(); i++) {
                System.out.print(res.get(i) + " ");
            }
        }
    }

    public void dfs(char[][] arr, int x, int y, int steps, int starti, int startj) {
        if (x < 0 || y < 0 || x >= arr.length || y >= arr[0].length || steps > len || visited[x][y] || arr[x][y] == '1')
            return;
        if (arr[x][y] == 'T') {
            if (steps == len) {
                if (tag != 2) {
                    tag = 1;
                    return;
                }
            } else {
                len = steps;
                tag = 2;
                return;
            }
            return;
        }
        visited[x][y] = true;
        dfs(arr, x + 1, y, steps + 1, starti, startj);
        dfs(arr, x - 1, y, steps + 1, starti, startj);
        dfs(arr, x, y + 1, steps + 1, starti, startj);
        dfs(arr, x, y - 1, steps + 1, starti, startj);

        visited[x][y] = false;
    }


    public void test111() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int Q = 0; Q < T; Q++) {
            int N = sc.nextInt();
            int num = N / 10;
            int count = 0;
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                    if (i % num == 0 && j % num == 0 && arr[i][j] == 1)
                        count++;
                }
            }
            System.out.println(readNum(arr, count, N));
        }
    }

    /**
     * 0:       28                  *
     * 1:       17  *
     * 2:       24          *
     * 3:       22      *
     * 4:       24          *
     * 5:       25              *
     * 6:       25              *
     * 7:       22      *
     * 8:       28                  *
     * 9:       25              *
     */
    //i,j---> i*N/10  j*N/10
    public int readNum(int[][] arr, int count, int N) {
        switch (count) {
            case 17:
                return 1;
            case 22:
                if (arr[N / 2][N / 2] == 1)
                    return 7;
                else
                    return 3;
            case 24:
                if (arr[N / 10][3 * N / 10] == 1)
                    return 2;
                else
                    return 4;
            case 25://5,9
                if (arr[N / 2][2 * N / 10] == 1)
                    return 6;
                else if (arr[2 * N / 10][N / 2] == 1)
                    return 5;
                else
                    return 9;
            case 28:
                if (arr[4 * N / 10][N / 2] == 1)
                    return 8;
                else
                    return 0;
            default:
                return 0;
        }
    }


    public int getLongestPalindrome(String A, int n) {
        char[] arr = A.toCharArray();
        int pre = 1;
        int res = 1;
        for (int i = 1; i < arr.length; i++) {
            if (i - pre - 1 >= 0 && arr[i - pre - 1] == arr[i]) {
                pre = pre + 2;
            }
        }


        return res;
    }


    public int longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                count += 2;
                set.remove(arr[i]);
            } else {
                set.add(arr[i]);
            }
        }
        return count < arr.length ? count + 1 : count;

    }


    public int[][] rotateMatrix(int[][] mat, int n) {
        int time = n / 2;
        //层数由外到内
        for (int i = 0; i < time; i++) {
            for (int j = i + 1; j < n - i; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[n - 1 - j][i];
                mat[n - 1 - j][i] = mat[n - 1 - i][n - 1 - j];
                mat[n - 1 - i][n - 1 - j] = mat[j][n - 1 - i];
                mat[j][n - 1 - i] = temp;
            }
        }
        return mat;
    }

    boolean isSearch = true, isAllTree = true;
    List<Integer> list = new ArrayList<Integer>();

    public boolean[] judgeIt(TreeNode root) {
        testIsSearch(root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                isSearch = false;
                break;
            }

        }
        testIsAllTree(root);

        return new boolean[]{isSearch, isAllTree};
    }

    public void testIsSearch(TreeNode root) {
        if (root == null) return;
        testIsSearch(root.left);
        list.add(root.val);
        testIsSearch(root.right);
    }

    public void testIsAllTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isAll = true;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (node.left == null) {
                    isAll = false;
                } else {
                    if (!isAll) {
                        isAllTree = false;
                        return;
                    }
                    queue.add(node.left);
                }
                if (node.right == null) {
                    isAll = false;
                } else {
                    if (!isAll) {
                        isAllTree = false;
                        return;
                    }
                    queue.add(node.right);
                }
            }
        }
    }


    public int findNumberOfLIS(int[] arr) {
        int[] dp = new int[arr.length];
        int max = 0, count = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > max) {
                count = 1;
                max = dp[i];
            } else if (dp[i] == max) {
                count++;
            }
        }
        return count;
    }


    public String notReCuPreOrder(TreeNode root) {
        if (root == null) return "";
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        List<Integer> list = new ArrayList<Integer>();
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            list.add(temp.val);
            /**
             * 先是right！！！
             */
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        String res = String.valueOf(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            res = res + "," + list.get(i);
        }
        return res;
    }


    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }


    public int maxLength(int[] arr) {
        int res = 0;
        int tmp = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int j = map.getOrDefault(arr[i], -1);
            map.put(arr[i], i);
            tmp = tmp + 1 < i - j ? tmp + 1 : i - j;
            res = Math.max(res, tmp);
        }
        return res;
    }

    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.val == o1 || root.val == o2) return root.val;
        int left = lowestCommonAncestor(root.left, o1, o2);
        int right = lowestCommonAncestor(root.right, o1, o2);
        if (left == Integer.MAX_VALUE) return right;
        if (right == Integer.MAX_VALUE) return left;
        return root.val;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int x = maxDepth(root.left);
        int y = maxDepth(root.right);
        return x > y ? 1 + x : 1 + y;
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] arr = numbers.clone();
        Arrays.sort(arr);
        int index1 = 0, index2 = numbers.length - 1;
        while (true) {
            int temp = arr[index1] + arr[index2];
            if (temp == target)
                break;
            if (temp < target)
                index1++;
            else
                index2--;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == arr[index1] || numbers[i] == arr[index2]) {
                list.add(i);
            }
        }
        return new int[]{Math.min(list.get(0), list.get(1)), Math.max(list.get(0), list.get(1))};
    }

    public void merge(int A[], int m, int B[], int n) {
        int index1 = m - 1, index2 = n - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (index1 == -1) {
                A[i] = B[index2];
                index2--;
                continue;
            }
            if (index2 == -1) {
                A[i] = A[index1];
                index1--;
                continue;
            }
            if (A[index1] > B[index2]) {
                A[i] = A[index1];
                index1--;
            } else {
                A[i] = B[index2];
                index2--;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        queue.add(root);
        int count = 1;
        while (count > 0) {
            int temp = count;
            count = 0;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < temp; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    count++;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    count++;
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> levelOrder1(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        queue.add(root);

        while (queue.size() > 0) {
            int count = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    public void test1() {
        Scanner sc = new Scanner(System.in);


    }

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        if (num == null || num.length == 0) return new ArrayList<>();
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] == 0)
                count++;
        }
        if (count >= 3) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            res.add(list);
        }
        int index = 0;
        while (true) {
            if (num[index] >= 0) break;
            int index1 = index + 1, index2 = num.length - 1;
            while (index1 < index2) {
                int temp = num[index] + num[index1] + num[index2];
                if (temp > 0) {
                    index2--;
                } else if (temp < 0) {
                    index1++;
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(num[index]);
                    list.add(num[index1]);
                    list.add(num[index2]);
                    while (index2 > index1 && num[index2 - 1] == num[index2]) {
                        index2--;
                    }
                    while (index2 > index1 && num[index1 + 1] == num[index1]) {
                        index1++;
                    }
                    res.add(list);
                }
            }
            while (index < num.length - 1 && num[index + 1] == num[index]) {
                index++;
            }
            index++;
        }
        return res;
    }

    public String solve(String str) {
        char[] arr = str.toCharArray();
        char[] res = new char[str.length()];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[arr.length - 1 - i];
        }
        return String.valueOf(res);
    }


    public void addOfThree() {
        Scanner sc = new Scanner(System.in);
        String[] arrStr = sc.nextLine().split(" ");
        int[] arr = new int[arrStr.length];
        int count = 0;//0的个数
        for (int i = 0; i < arrStr.length; i++) {
            arr[i] = Integer.valueOf(arrStr[i]);
            if (arr[i] == 0) count++;
        }
        Arrays.sort(arr);
        int first = 0;
        while (true) {
            if (arr[first] >= 0) {
                if (count >= 3) System.out.println("0 0 0");
                break;
            }
            int left = first + 1, right = arr.length - 1;
            while (left < right) {
                int tempRes = arr[first] + arr[left] + arr[right];
                if (tempRes > 0) {
                    while (left < right && arr[right] == arr[right - 1]) {
                        right--;
                    }
                    right--;
                } else if (tempRes < 0) {
                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }
                    left++;
                } else {
                    System.out.println(arr[first] + " " + arr[left] + " " + arr[right]);
                    while (left < right && arr[right] == arr[right - 1]) {
                        right--;
                    }
                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }
                    left++;
                    right--;
                }
            }
            while (first < right && arr[first] == arr[first + 1]) {
                first++;
            }
            first++;
        }


    }


    //长方体堆叠
    public void test100() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            int[][] arr = new int[N * 3][3];
            int res = 0;
            for (int i = 0; i < N; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                arr[i][0] = Math.max(a, b);
                arr[i][1] = Math.min(a, b);
                arr[i][2] = c;
                arr[i + N][0] = Math.max(a, c);
                arr[i + N][1] = Math.min(a, c);
                arr[i + N][2] = b;
                arr[i + 2 * N][0] = Math.max(b, c);
                arr[i + 2 * N][1] = Math.min(b, c);
                arr[i + 2 * N][2] = a;
            }
            height = Integer.MIN_VALUE;
            //arr里存放了所有可以使用的组合
            for (int i = 0; i < 3 * N; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                dfs(arr, list);
            }
            System.out.println(height);

        }
    }

    static int height = Integer.MIN_VALUE;

    //count 是指到了第几层，到了最后一层，且合理，则计算高度
    //2表示高度
    public void dfs(int[][] arr, List<Integer> list) {
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            res += arr[list.get(i)][2];
        }
        if (res > height) height = res;
        for (int i = 0; i < arr.length; i++) {
            if (list.contains(i)) continue;
            int last = list.get(list.size() - 1);//最后一个是最高的
            if (arr[i][0] > arr[last][0] && arr[i][1] > arr[last][1]) {
                //可以叠加
                list.add(i);
                dfs(arr, list);
                list.remove((Object) i);
            }
        }
    }


}





