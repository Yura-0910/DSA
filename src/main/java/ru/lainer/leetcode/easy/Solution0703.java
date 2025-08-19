package ru.lainer.leetcode.easy;

import java.util.PriorityQueue;

/**
 * Your KthLargest object will be instantiated and called as such: KthLargest obj = new
 * KthLargest(k, nums); int param_1 = obj.add(val);
 */

//https://leetcode.com/problems/kth-largest-element-in-a-stream/solutions/5624295/easy-and-clean-code-c-java/

public class Solution0703 {

  private int k;
  private PriorityQueue<Integer> pq;

  public Solution0703(int k, int[] nums) {
    this.k = k;
    pq = new PriorityQueue<>();
    for (int i : nums) {
      pq.offer(i);
      if (pq.size() > k) {
        pq.poll(); //Удаляем header, т.е самый маленький элемент
      }
    }
  }

  public int add(int val) {
    pq.offer(val);
    if (pq.size() > k) {
      pq.poll();
    }
    return pq.peek();
  }

  public static void main(String[] args) {
    int[] nums = {4, 5, 8, 2};
    int k = 3;
    Solution0703 solution = new Solution0703(k, nums);
    System.out.println("kth largest element = " + solution.add(3));
    System.out.println("kth largest element = " + solution.add(5));
  }
}
