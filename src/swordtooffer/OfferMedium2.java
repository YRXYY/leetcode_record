package swordtooffer;

import annotation.Level;
import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}


public class OfferMedium2 {

    @Level(value = 3,message = "Offer36 二叉搜索树与双向链表" +
            "维护一个全局变量，携程倒下的题目，很赞（糟）哦！")
    Node head, pre;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        pre.right = head;
        head.left = pre;
        return head;
    }

    public void dfs(Node root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfs(root.left);
        }
        if (head == null) {
            head = new Node(root.val);
            pre = head;
        } else {
            Node node = new Node(root.val);
            pre.right = node;
            node.left = pre;
            pre = node;
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }

}


@Level(value = 4,message = "Offer37 序列化二叉树" +
        "繁琐，且自己实现的效率低，未通过最后一个用例" +
        "仍待优化")
class Codec1 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int nodeLeft = 1;
        queue.add(root);
        while (nodeLeft > 0) {
            TreeNode tmp = queue.poll();
            res.add(tmp.val);
            if (tmp.val != Integer.MAX_VALUE) {
                nodeLeft--;
            }
            queue.add(tmp.left == null ? new TreeNode(Integer.MAX_VALUE) : tmp.left);
            if (tmp.left != null) {
                nodeLeft++;
            }
            queue.add(tmp.right == null ? new TreeNode(Integer.MAX_VALUE) : tmp.right);
            if (tmp.right != null) {
                nodeLeft++;
            }
        }
        // turn to String
        String result = "[";
        for (int i = 0; i <res.size() ; i++) {
            if (res.get(i) == Integer.MAX_VALUE) {
                result += "null";
            } else {
                result += res.get(i).toString();
            }
            if (i != res.size() - 1) {
                result += ",";
            }
        }
        result += "]";
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        data = data.substring(1,data.length() - 1);
        String[] nums = data.split(",");
        if (nums.length == 0) {
            return null;
        }
        TreeNode res = new TreeNode(Integer.valueOf(nums[0]));
        List<TreeNode> list = new ArrayList<>();
        list.add(res);
        for (int i = 0; i < nums.length - 1; i++) {
            if (!nums[i+1].equals("null")) {
                if (i % 2 == 0) {
                    list.get(i/2).left = new TreeNode(Integer.valueOf(nums[i+1]));
                    list.add(list.get(i/2).left);
                } else {
                    list.get(i/2).right = new TreeNode(Integer.valueOf(nums[i+1]));
                    list.add(list.get(i / 2).right);
                }
            } else {
                // 填充 无用
                list.add(new TreeNode(0));
            }
        }
        return res;
    }


}



class MedianFinder {

    int size;
    int count1, count2;
    double num1, num2;

    public MedianFinder() {
        size = 0;
        count1 = 0; count2 = 0;
        num1 = 0; num2 = 0;
    }

    public void addNum(int num) {
        if (size < 2) {
            if (size == 0) {
                num1 = num;
            } else {
                if (num > num1) {
                    num2 = num;
                } else {
                    num2 = num1;
                    num1 = num;
                }
            }
        } else if (count1 == count2){
            if (num <= num1) {
                count1++;
            } else if (num >= num2) {
                count2++;
            } else {
                // num1 < num < num2
                num1 = num;
                count1++;
            }
        } else if (count1 < count2) {
            if (num <= num1) {

            } else if (num >= num2) {
                num1 = num2;
                num2 = num;
            } else {
                // num1 < num < num2
                num1 = num;
            }
            count1++;
        } else {
            // count1 > count2
            if (num <= num1) {
                num2 = num1;
                num1 = num;
            } else if (num >= num2) {

            } else {
                // num1 < num < num2
                num2 = num;
            }
            count2++;
        }
        size++;
    }

    public double findMedian() {
        if (size == 1) {
            return num1;
        }
        if (size % 2 != 0) {
            return count1 > count2 ? num1 : num2;
        } else {
            return (num1 + num2) / 2;
        }
    }
}