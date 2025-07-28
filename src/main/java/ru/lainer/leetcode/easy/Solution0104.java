package ru.lainer.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solution0104 {

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
   * Есть следующие обходы бинарного дерева. Все три алгоритма — частные случаи DFS::
   * 1. Прямой обход (Pre-order traversal)
   * 2. Симметричный обход (In-order traversal)
   * 3. Обратный обход (Post-order traversal)
   *
   * Есть еще обход в ширину (Level-order traversal), он же BFS (Breadth-First Search)
   *
   * Для решения данной задачи больше подходит "Обратный обход (Post-order traversal)"
   */

  public int maxDepth(TreeNode root) {
    int result = 0;
    int depth = 1;        //Текущая глубина ветки
    int pairFromStack = 0;

    //Храним перебираемые узлы дерева
    Stack<TreeNode> stack = new Stack<>();

    //Сюда добавляем глубину дерева, кода есть левый и правый лист
    Stack<Integer> pairDepth = new Stack<>();

    //Максимальная глубина ветки (дошли до самого последнего листа)
    List<Integer> maxBranchDepth = new ArrayList<>(100);

    if(root == null){
      return result;
    }

    stack.push(root);

    while(!stack.empty()) {
      TreeNode current = stack.pop();
      System.out.println(current.val);

      if(current.left != null){
        stack.push(current.left);
      }
      if(current.right != null){
        stack.push(current.right);
      }

      //Когда есть левый ИЛИ правый узел у корня
      if(current.left != null || current.right != null){
        depth++;
      }

      //Когда есть левый И правый узел у корня
      if(current.left != null && current.right != null){
        pairDepth.push(depth);
      }

      //Когда у узла нет листьев (дошли до самого низа)
      if (current.left == null && current.right == null){
        maxBranchDepth.add(depth);

        if (!pairDepth.isEmpty()){
          pairFromStack = pairDepth.pop();
          depth = pairFromStack;
        }
      }
    }

    result = Collections.max(maxBranchDepth);
    return result;
  }

  public static void main(String[] args) {
    //[0,2,4,1,null,3,-1,5,1,null,6,null,8]
    TreeNode treeNode32 = new TreeNode(1);
    TreeNode treeNode31 = new TreeNode(5);
    TreeNode treeNode03 = new TreeNode(1, treeNode31, treeNode32);

    TreeNode treeNode44 = new TreeNode(8);
    TreeNode treeNode43 = new TreeNode(6);

    TreeNode treeNode42 = new TreeNode(-1, null, treeNode44);
    TreeNode treeNode41 = new TreeNode(3, null, treeNode43);
    TreeNode treeNode04 = new TreeNode(4, treeNode41, treeNode42);

    TreeNode treeNode05 = new TreeNode(2, treeNode03, null);
    TreeNode treeNode06 = new TreeNode(0, treeNode05, treeNode04);

    Solution0104 solution = new Solution0104();
    System.out.println("Result = " + solution.maxDepth(treeNode06));
  }
}
