package ru.lainer.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

public class Solution0226 {

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

    @Override
    public String toString() {
      return "TreeNode{" +
          "val=" + val +
          ", left=" + left +
          ", right=" + right +
          '}';
    }
  }

  /*
   * Есть следующие виды обхода бинарного дерева:
   * 1. Прямой обход (Pre-order traversal)
   * 2. Симметричный обход (In-order traversal)
   * 3. Обратный обход (Post-order traversal)
   * 4. Обход в ширину (Level-order traversal)
   *
   * Для решения данной задачи подходить только алгоритм "Обход в ширину (Level-order traversal)"
   * Этот алгоритм также известен как BFS (Breadth-First Search) для деревьев
   */
  public TreeNode invertTree(TreeNode root) {
    TreeNode tmp;
    if (root == null) return null;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();

      if (current.left != null) {
        queue.add(current.left);
      }
      if (current.right != null) {
        queue.add(current.right);
      }
      tmp = current.left;
      current.left = current.right;
      current.right = tmp;
    }
    return root;
  }

  public static void main(String[] args) {
    TreeNode treeNode01 = new TreeNode(1);
    TreeNode treeNode02 = new TreeNode(3);
    TreeNode treeNode03 = new TreeNode(6);
    TreeNode treeNode04 = new TreeNode(9);
    TreeNode treeNode05 = new TreeNode(2, treeNode01, treeNode02);
    TreeNode treeNode06 = new TreeNode(7, treeNode03, treeNode04);
    TreeNode treeNode07 = new TreeNode(4, treeNode05, treeNode06);

    TreeNode treeNode08 = new TreeNode(2);
    TreeNode treeNode09 = new TreeNode(1, treeNode08, null);

    Solution0226 solution0226 = new Solution0226();
    TreeNode result = solution0226.invertTree(treeNode09);
    System.out.println("Result = " + result);
  }
}
