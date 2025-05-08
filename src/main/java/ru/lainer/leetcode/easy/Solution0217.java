package ru.lainer.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class Solution0217 {

  public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int current : nums) {
      if (set.contains(current)) {
        return true;
      } else {
        set.add(current);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[] intArr = {1, 2, 3, 1};
    Solution0217 solution0217 = new Solution0217();
    boolean result = solution0217.containsDuplicate(intArr);
    System.out.println("Are there any duplicates: " + result);
  }
}
