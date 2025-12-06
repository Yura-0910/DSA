package ru.lainer.leetcode.easy;

import java.util.Stack;

public class Solution0104C {

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
   * DFS(Depth-first search) -> вариант "Обратный обход (Post-order traversal)" -> Вариант "Стек"
   * Порядок: Левый потомок → Правый потомок → Корень
   *           0
         /          \
      2               4
     / \           /     \
    1  22        3       -1
   / \         /  \     /   \
  5   9       null  6  null  8
     / \
    99 999
   * Результат перебора, stack1, Pre-order traversal :: 0 4 -1 8 3 6 2 22 1 9 999 99 5
   * Результат перебора, stack2, Post-order traversal:: 5 99 999 9 1 22 2 6 3 8 -1 4 0
   * Один стек                                       :: 5 99 999 9 1 22 2 6 3 8 -1 4 0
   */
  //Вариант № 1:: два стека
  public void postOrderStack1(TreeNode root) {
    if (root == null) {
      return;
    }

    /*
     * Первый стек для обхода в порядке: корень -> правый потомок -> левый потомок,
     * как в случае с "Прямой обход (Pre-order traversal)"
     */
    Stack<TreeNode> stack1 = new Stack<>();

    //Второй стек дает обратный порядок: левый потомок -> правый потомок -> корень
    Stack<TreeNode> stack2 = new Stack<>();

    stack1.push(root);

    while (!stack1.isEmpty()) {
      TreeNode node = stack1.pop(); //Поучили корень
      System.out.print(node.val + " ");

      // Сохраняем в стек, что дает обратный порядок при извлечении
      stack2.push(node);

      if (node.left != null) {
        stack1.push(node.left);
      }

      if (node.right != null) {
        stack1.push(node.right);
      }
    }

    //Выводим данные в обратном порядке, так как стек:: левый потомок -> правый потомок -> корень
    System.out.println("");
    while (!stack2.isEmpty()) {
      System.out.print(stack2.pop().val + " ");
    }
  }

  //Вариант № 2:: Один стек
  public void postOrderStack2(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode lastVisited = null;
    while (root != null || !stack.empty()) {
      //Достигаем самого левого нижнего узла
      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      //Берем самый верхний элемент из стека, но не удаляем его
      TreeNode peekNode = stack.peek();

      //Если у узла из стека есть правый потомок и мы его еще не посещали
      if (peekNode.right != null && peekNode.right != lastVisited) {
        root = peekNode.right;
      }
      else {
        //Посещаем узел
        System.out.print(peekNode.val + " ");
        lastVisited = stack.pop();//Берем и удаляем
      }
    }
  }

  /*
   * DFS(Depth-first search) -> вариант "Обратный обход (Post-order traversal)" -> Вариант
   * "Рекурсия". Порядок: Левый потомок → Правый потомок → Корень
   */
  public void postOrderRecursion(TreeNode root) {
    if(root == null){
      return;
    }

    postOrderRecursion(root.left);
    postOrderRecursion(root.right);
    System.out.print(root.val + " ");
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

    Solution0104C solution0104C = new Solution0104C();
    solution0104C.postOrderStack1(treeNode06);
    System.out.println("\nОдин стек");
    solution0104C.postOrderStack2(treeNode06);
    System.out.println("\nРекурсия");
    solution0104C.postOrderRecursion(treeNode06);
  }
}
