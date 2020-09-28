package exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class wanmei {
    //天际线
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //建筑物个数
        int n = Integer.parseInt(sc.nextLine());
        int[][] buildings = new int[n][3];
        int right = 0;
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            for (int j = 0; j < arr.length; j++) {
                if(j==1)
                    right = Math.max(right,Integer.valueOf(arr[j]));
                buildings[i][j] = Integer.parseInt(arr[j]);
            }
        }

        //获取天际线，待实现
        List<List<Integer>> skyline = getSkyline(buildings,right);

        //输出skyline到标准输出
        for (List<Integer> point : skyline) {
            int size = point.size();
            for (int i = 0; i < size; i++) {
                System.out.print(point.get(i));
                if (i < size-1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 5
     * 2 9 10
     * 3 7 15
     * 5 12 12
     * 15 20 10
     * 19 24 8
     */

    public static List<List<Integer>> getSkyline(int[][] buildings,int r) {
        //记录可能的高度
        int[] height = new int[r+1];
        //记录点的状态
        //0：未标记  1：左高覆盖  2： 右低覆盖  3： 中间覆盖   4:右高覆盖
        //打印的点： 1    2    4
        int[] state = new int[r+1];

        for(int i=0;i<buildings.length;i++){
            int left = buildings[i][0];
            int right = buildings[i][1];
            int tall = buildings[i][2];
            //处理左点
            if(tall>height[left]){
                height[left] = tall;
                state[left] = 1;
            }
            //处理右点
            if(tall>height[right]){
                if(height[right]==0){
                    state[right] = 2;
                    height[right] = tall;
                }else{
                    state[right] =  4;
                }

            }
            //处理中间的点
            for(int j=left+1;j<right;j++){
                if(state[j]==2){
                    if(tall<height[j]){
                        state[j] = 4;
                        height[j] = tall;
                        continue;
                    }
                }else{
                    if(height[j]<=tall){
                        state[j] = 3;
                    }
                }

                height[j] = Math.max(height[j],tall);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<state.length;i++){
            if(state[i]==1||state[i]==4){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(height[i]);
                res.add(list);
            }
            if(state[i]==2){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(0);
                res.add(list);
            }
        }
        return res;
    }
}
