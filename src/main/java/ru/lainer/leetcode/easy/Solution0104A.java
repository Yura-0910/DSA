package ru.lainer.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solution0104A {

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
   * 0. Обход в ширину (Level-order traversal) он же BFS(Breadth-First Search)
   *
   * Все три алгоритма ниже — частные случаи DFS(Depth-first search - это поиск в глубину)::
   * 1. Прямой обход (Pre-order traversal)
   *    Порядок: Корень → Левый потомок → Правый потомок
   * 2. Симметричный обход (In-order traversal)
   *    Порядок: Левый потомок → Корень → Правый потомок
   * 3. Обратный обход (Post-order traversal)
   *    Порядок: Левый потомок → Правый потомок → Корень
   */

  /*
   * DFS(Depth-first search) -> вариант "Прямой обход (Pre-order traversal)" -> Вариант "Стек"
   * Порядок: Корень → Левый потомок → Правый потомок
   */
  public void preOrderStack(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.empty()) {
      TreeNode current = stack.pop();
      System.out.print(current.val + " ");

      /*
       * Сначала добавляем ПРАВОГО, потом ЛЕВОГО, так как стек работает по принципу LIFO
       * (Last-In-First-Out) и если бы сначала добавляли левый, а потом правый потомок, то
       * правый потомок бы извлекался раньше левого потомка и был бы не такой порядок,
       * как при использовании рекурсии в методе preOrderRecursion.
       */
      if (current.right != null) {
        stack.push(current.right);
      }
      if (current.left != null) {
        stack.push(current.left);
      }
    }
  }

  /*
   * DFS(Depth-first search) -> вариант "Прямой обход (Pre-order traversal)" -> Вариант "Рекурсия"
   * Порядок: Корень → Левый потомок → Правый потомок
   */
  public void preOrderRecursion(TreeNode root) {
    if (root == null) {
      return;
    }
    System.out.print(root.val + " ");
    preOrderRecursion(root.left);
    preOrderRecursion(root.right);
  }

  /*
   * DFS(Depth-first search) -> вариант "Прямой обход (Pre-order traversal)" -> Вариант "Стек"
   * Здесь решение задачи 104.
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

    if (root == null) {
      return result;
    }

    stack.push(root);

    while (!stack.empty()) {
      TreeNode current = stack.pop();
      System.out.print(current.val + " ");

      if (current.left != null) {
        stack.push(current.left);
      }
      if (current.right != null) {
        stack.push(current.right);
      }

      //Когда есть левый ИЛИ правый узел у корня
      if (current.left != null || current.right != null) {
        depth++;
      }

      //Когда есть левый И правый узел у корня
      if (current.left != null && current.right != null) {
        pairDepth.push(depth);
      }

      //Когда у узла нет листьев (дошли до самого низа)
      if (current.left == null && current.right == null) {
        maxBranchDepth.add(depth);

        if (!pairDepth.isEmpty()) {
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

    Solution0104A solution = new Solution0104A();
    System.out.println(solution.getClass().getName() + " ");
    System.out.println("Result = " + solution.maxDepth(treeNode06));
    solution.preOrderStack(treeNode06);
    System.out.println("");
    solution.preOrderRecursion(treeNode06);
  }
}
