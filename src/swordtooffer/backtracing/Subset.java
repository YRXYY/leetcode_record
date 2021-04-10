package swordtooffer.backtracing;

import java.util.ArrayList;
import java.util.List;

public class Subset {

    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();
        fillBlanks(nums, 0, tmpList);
        return res;
    }

    public void fillBlanks(int[] nums, int target, List<Integer> list) {
        if (target == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        fillBlanks(nums, target + 1, list);
        list.add(nums[target]);
        fillBlanks(nums, target + 1, list);
        list.remove(list.size() - 1);
    }


}
