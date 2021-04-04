package swordtooffer.meituan;

import java.util.*;

public class day1 {



    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < answers.length; i++) {
            if (!map.containsKey(answers[i])) {
                map.put(answers[i], answers[i] + 1);
            }
            map.put(answers[i], map.get(answers[i]) - 1);
            if (map.get(answers[i]) == 0) {
                count += answers[i] + 1;
                map.remove(answers[i]);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getKey() + 1;
        }
        return count;

    }

    public static void main(String[] args) {
        System.out.println(new day1().numRabbits(new int[]{1,1,2}));;
    }



//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int T = sc.nextInt();
//        int[] arr = new int[T];
//        int max = 0;
//        for (int i = 0; i < T; i++) {
//            arr[i] = sc.nextInt();
//            max = Math.max(max, arr[i]);
//        }
//        short[] res = new short[max + 1];
//        res[0] = 0;
//        res[1] = 1;
//        for (int i = 2; i < max + 1; i++) {
//            res[i] = (short) (res[i/2] + 1);
//        }
//        for (int i = 0; i < T; i++) {
//            System.out.println(res[arr[i]]);
//        }
//
//    }



}
