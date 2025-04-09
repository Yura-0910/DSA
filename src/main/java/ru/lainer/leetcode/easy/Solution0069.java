package ru.lainer.leetcode.easy;

public class Solution0069 {

  public int mySqrt(int x) {
    if (x == 1) {
      return 1;
    }
    if (x == 0) {
      return 0;
    }
    double x0 = (double) x / 2;
    double x1 = 0;
    while (x0 - x1 > 0.1) {
      if (x1 > 0) {
        x0 = x1;
      }
      x1 = (0.5) * (x0 + (x / x0));
    }
    return (int) x1;
  }

  public static void main(String[] args) {
    Solution0069 solution = new Solution0069();
    int result = solution.mySqrt(8);
    System.out.println("result = " + result);
  }
}