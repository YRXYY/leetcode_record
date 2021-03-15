package aatest;

import java.util.ArrayList;
import java.util.List;

class Boy {
    int count = 2;
}

class Girl{
    int count = 0;
}
public class test3 {


    public static void main(String[] args) {

        int count0 = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
        int times = 10000000;
        for (int i = 0; i < times/10; i++) {
            List<Boy> boys = new ArrayList<Boy>();
            for (int j = 0; j < 5; j++) {
                boys.add(new Boy());
            }
            List<Girl> girls = new ArrayList<Girl>();
            for (int j = 0; j < 8; j++) {
                girls.add(new Girl());
            }
            //投票
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 8; k++) {
                    if (boys.get(j).count > 0 && Math.random() > 0.5) {
                        boys.get(j).count--;
                        girls.get(k).count++;
                    } else if (boys.get(j).count == 0) {
                        break;
                    }
                }
            }
            //唱票
            for (int j = 0; j < 8; j++) {
                switch (girls.get(j).count) {
                    case 0:
                        count0++;
                        break;
                    case 1:
                        count1++;
                        break;
                    case 2:
                        count2++;
                        break;
                    case 3:
                        count3++;
                        break;
                    case 4:
                        count4++;
                        break;
                    case 5:
                        count5++;
                        break;
                        default :break;
                }
            }
        }
        System.out.println(count0+" "+count1+" "+count2 + " "+count3 + " "+count4 + " "+count5);
        System.out.println((double) count0/times+" "+(double) count1/times+" "+(double) count2/times
                + " "+(double) count3/times + " "+(double) count4/times+ " "+(double) count5/times);

    }

//      不得票       得1票概率   2           3        4         5
//赛道1：0.292946 0.2609754 0.2191723 0.1516312 0.0637129 0.0115622
//赛道2：0.3043219 0.2061497 0.1477838 0.095344 0.0392764 0.0071242
}
