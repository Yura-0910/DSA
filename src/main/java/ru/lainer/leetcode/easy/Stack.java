package ru.lainer.leetcode.easy;

public class Stack {

  private int size;      //Размер Stack-а
  private int[] data;    //Тут храним данные
  private int head;      //Вершина

  public Stack(int size) {
    this.size = size;
    this.data = new int[size];
    this.head = -1;
  }

  public int get() {
    if (head < 0) {
      System.out.println("Stack пуст");
      return -1;
    } else {
      return data[head--];
    }
  }

  public void set(int d) {
    if (head == size - 1) {
      System.out.println("Stack полон");
    } else {
      data[++head] = d;
    }
  }
}
