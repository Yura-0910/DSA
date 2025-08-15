package ru.lainer.leetcode.medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution0078 {

  public List<List<Integer>> subsets(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
    backTrack(result, tmp, nums, 0);
    return result;
  }

  //[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
  private void backTrack(List<List<Integer>> result, List<Integer> tmp, int[] nums, int start) {
    result.add(new ArrayList<>(tmp));
    for (int i = start; i < nums.length; i++) {
      tmp.add(nums[i]);
      backTrack(result, tmp, nums, i + 1);
      tmp.remove(tmp.size() - 1);
    }
  }

  public static void main(String[] args) {
    int[] nums = {11, 22, 33};
    Solution0078 solution0078 = new Solution0078();
    System.out.println(solution0078.subsets(nums));
  }
}
