package ru.lainer.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

public class Solution0104D {

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
   * BFS(Breadth-First Search) используя "Queue".
   * Порядок:: обход дерева уровень за уровнем, слева направо.
   *          0
         /          \
      2               4
     / \           /     \
    1  22        3       -1
   / \         /  \     /   \
  5   9       null  6  null  8
     / \
    99 999
   * Результат перебора:: 0 2 4 1 22 3 -1 5 9 6 8 99 999
   */
  public void bfs(TreeNode root) {
    if (root == null) {
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    //Вставляем элемент в "Queue"
    queue.offer(root);

    while (!queue.isEmpty()) {
      //Получаем и удаляем "head" у "Queue"
      TreeNode current = queue.poll();
      System.out.print(current.val + " ");

      if (current.left != null) {
        queue.offer(current.left);
      }

      if (current.right != null) {
        queue.offer(current.right);
      }
    }
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

    Solution0104D solution0104D = new Solution0104D();
    solution0104D.bfs(treeNode06);
  }
}
