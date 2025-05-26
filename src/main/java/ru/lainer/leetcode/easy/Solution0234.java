package ru.lainer.leetcode.easy;

import java.util.Stack;

public class Solution0234 {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public boolean isPalindrome(ListNode head) {
    Stack<Integer> stack = new Stack<>();
    int countElement = 0;   //Количество ListNode
    ListNode ListNodeForCount = head;
    int middle = 0;        //Номер середины в ListNode

    //Считаем общее количество ListNode, чтоб найти середину
    while (ListNodeForCount != null) {
      countElement++;
      ListNodeForCount = ListNodeForCount.next;
    }

    //Середина
    if (countElement % 2 == 0) {
      middle = countElement / 2;
    } else {
      middle = countElement / 2 + 1;
    }

    //Добавляем в Stack все элементы до середины
    while (middle > 0) {
      stack.push(head.val);
      head = head.next;
      middle--;
    }

    //1 2 3 3 3 2 1, середина на 3:: ее удаляем
    if (countElement % 2 != 0) {
      stack.pop();
    }

     while (head != null) {
      if (stack.search(head.val) == 1) {
        stack.pop();
        head = head.next;
      } else {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    int[] source = {8, 0, 7, 1, 7, 7, 9, 7, 5, 2,
        9, 1, 7, 3, 7, 0, 6, 5, 1, 7,
        7, 9, 3, 8, 1, 5, 7, 7, 8, 4,
        0, 9, 3, 7, 3, 4, 5, 7, 4, 8,
        8, 5, 8, //43
        9,       //Середина-43,5
        8, 5, 8,
        8, 4, 7,
        5, 4, 3, 7, 3, 9, 0, 4, 8, 7,
        7, 5, 1, 8, 3, 9, 7, 7, 1, 5,
        6, 0, 7, 3, 7, 1, 9, 2, 5, 7,
        9, 7, 7, 1, 7, 0, 8};

    ListNode head = null;

    for (int i = source.length - 1; i >= 0; i--) {
      head = new ListNode(source[i], head);
    }

    Solution0234 solution0234 = new Solution0234();
    boolean result = solution0234.isPalindrome(head);
    System.out.println("ListNode is palindrome ?:: " + result);
  }
}
