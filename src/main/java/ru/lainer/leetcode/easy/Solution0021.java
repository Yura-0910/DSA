package ru.lainer.leetcode.easy;

public class Solution0021 {

  //Definition for singly-linked list.
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

    @Override
    public String toString() {
      return "ListNode{" +
          "val=" + val +
          ", next=" + next +
          '}';
    }
  }

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    //Используем подход "dummy node":: фиктивный "header Node"
    ListNode result = new ListNode(-1, null); // Фиктивный Node
    ListNode header = result;

    while (list1 != null && list2 != null){
      if (list1.val <= list2.val){
        /*
          В коде:: header.val = list1.val;
                   header.next = new ListNode(-2, null);
          В этом коде проблема:: в конце ListNode остается ListNode(-2, null),
          по этому используем:: header.next = list1\list2;
         */
        header.next = list1;
        list1 = list1.next;
      }
      else {
        header.next = list2;
        list2 = list2.next;
      }
      header = header.next;
    }

    if (list1 == null && list2 != null){
      header.next = list2;
    }

    if (list2 == null && list1 != null){
      header.next = list1;
    }

    return result.next; // Возвращается реальный головной Node
  }

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(4, null);
    ListNode listNode2 = new ListNode(2, listNode1);
    ListNode listNode3 = new ListNode(1, listNode2);

    ListNode listNode11 = new ListNode(4,null);
    ListNode listNode22 = new ListNode(3, listNode11);
    ListNode listNode33 = new ListNode(1, listNode22);

    Solution0021 solution0021 = new Solution0021();
    ListNode result = solution0021.mergeTwoLists(listNode3, listNode33);
    System.out.println("Результат:: " + result);
  }
}
