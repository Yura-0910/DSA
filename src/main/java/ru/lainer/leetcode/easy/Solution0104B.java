package ru.lainer.leetcode.easy;

import java.util.Stack;

public class Solution0104B {

  //Definition for a binary tree node.
  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  /*
   * DFS(Depth-first search) -> вариант "Симметричный обход (In-order traversal)" -> Вариант "Стек"
   * Порядок: Левый потомок → Корень → Правый потомок
   *           0
         /          \
      2               4
     / \           /     \
    1  22        3       -1
   / \         /  \     /   \
  5   9       null  6  null  8
     / \
    99 999
   * Результат перебора:: 5 1 99 9 999 2 22 0 null 3 6 4 null -1 8
   */
  public void inOrderStack(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;

    while (current != null || !stack.isEmpty()) {
      while (current != null) {
        stack.push(current);
        current = current.left;
      }

      current = stack.pop();
      System.out.print(current.val + " ");
      current = current.right;
    }
  }

  /*
   * DFS(Depth-first search) -> вариант "Симметричный обход (In-order traversal)" -> Вариант
   * "Рекурсия". Порядок: Левый потомок → Корень → Правый потомок
   */
  public void inOrderRecursion(TreeNode root) {
    if (root == null) {
      return;
    }
    inOrderRecursion(root.left);
    System.out.print(root.val + " ");
    inOrderRecursion(root.right);
  }

  public static void main(String[] args) {
    //[0,2,4,1,null,3,-1,5,1,null,6,null,8]
    TreeNode treeNode32 = new TreeNode(9, new TreeNode(99), new TreeNode(999));//было 1
    TreeNode treeNode31 = new TreeNode(5);
    TreeNode treeNode03 = new TreeNode(1, treeNode31, treeNode32);

    TreeNode treeNode44 = new TreeNode(8);
    TreeNode treeNode43 = new TreeNode(6);

    TreeNode treeNode42 = new TreeNode(-1, null, treeNode44);
    TreeNode treeNode41 = new TreeNode(3, null, treeNode43);
    TreeNode treeNode04 = new TreeNode(4, treeNode41, treeNode42);

    TreeNode treeNode05 = new TreeNode(2, treeNode03, new TreeNode(22));
    TreeNode treeNode06 = new TreeNode(0, treeNode05, treeNode04);

    Solution0104B solution = new Solution0104B();
    System.out.println(solution.getClass().getName() + " ");
    solution.inOrderStack(treeNode06);
    System.out.println("");
    solution.inOrderRecursion(treeNode06);
  }
}
