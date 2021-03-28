package knowledge.oneVital;

import java.util.Stack;

/**
 * �Ϸ�����
 */
public class kuohao {

    /**
     * ��һ���ţ�(())
     */
    boolean isValidA(String str) {
        int left = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                left++;
            } else {
                left--;
            }
            if (left < 0) {
                return false;
            }
        }
        return left == 0;
    }

    /**
     * �������ţ�����[(])
     * �޷��հ������˼·
     *
     */
    boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && (ret(c) == stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    char ret(char c) {
        if (c == ')') {
            return '(';
        } else if (c == ']') {
            return '[';
        } else {
            return '{';
        }
    }


    public static void main(String[] args) {
        System.out.println(new kuohao().isValid("([{}])"));
    }

}
