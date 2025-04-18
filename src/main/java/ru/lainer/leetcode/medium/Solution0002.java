package ru.lainer.leetcode.medium;

public class Solution0002 {

  //Односвязный "linked list"
  public class ListNode {

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

  //Скорость алгоритма:: 3 миллисекунды
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    StringBuilder sbFirst = new StringBuilder(); //Первое число из ListNode l1
    StringBuilder sbSecond = new StringBuilder();//Второе число из ListNode l2

    //формируем число\строку из односвязного ListNode
    while (l1 != null) {
      sbFirst.append(l1.val);
      l1 = l1.next;
    }
    while (l2 != null) {
      sbSecond.append(l2.val);
      l2 = l2.next;
    }
    sbFirst.reverse();
    sbSecond.reverse();

    //сумма двух чисел
    StringBuilder sum = new StringBuilder();
    int inMemory = 0;
    int i = sbFirst.length() - 1;  //Индекс последнего символа
    int j = sbSecond.length() - 1; //Индекс последнего символа
    int tmpFirst = 0;  //Число из sbFirst
    int tmpSecond = 0; //Число из sbSecond
    int tmpSum = 0;    //Промежуточная сумма

    // Пока есть цифры в любом из чисел или есть что-то в inMemory
    while (i >= 0 || j >= 0 || inMemory > 0) {
      if (i >= 0) {
        //Получаем нужный символ из строки и преобразуем символ в число ("- '0'")
        tmpFirst = sbFirst.charAt(i) - '0';
        i--;
      } else {
        tmpFirst = 0;
      }
      if (j >= 0) {
        tmpSecond = sbSecond.charAt(j) - '0';
        j--;
      } else {
        tmpSecond = 0;
      }

      //Суммируем
      tmpSum = tmpFirst + tmpSecond + inMemory;
      inMemory = tmpSum / 10;  //целая часть от деления на 10
      sum.append(tmpSum % 10); //дробная часть от деления на 10
    }

    char[] sumArray = sum.reverse().toString().toCharArray();

    //Заполняем массив ListNode значениями ListNode
    ListNode[] listNodeArray = new ListNode[sumArray.length];
    for (int k = 0; k < sumArray.length; k++) {
      listNodeArray[k] = new ListNode(sumArray[k] - '0'); //преобразуем char в int
    }

    //Связываем listNodes
    for (int k = 1; k < listNodeArray.length; k++) {
      listNodeArray[k].next = listNodeArray[k - 1];
    }

    return listNodeArray[listNodeArray.length - 1];
  }

  //Скорость алгоритма:: 1 миллисекунда (Beats 100%), memory (Beats 93.09%)
  public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    ListNode result = new ListNode(12345); //фиктивный ListNode для хранения результата
    ListNode tmpResult = result;               //Для временного хранения ListNode
    int inMemory = 0;
    int firstNumb = 0;  //Первое число из l1
    int secondNumb = 0; //Второе число из l2
    int tmpSum = 0;     //Промежуточная сумма

    //Пока не перебрали все ListNode(l1 и l2) или пока есть что-то в inMemory
    while (l1 != null || l2 != null || inMemory > 0) {
      if (l1 != null) {
        firstNumb = l1.val;
      } else {
        firstNumb = 0;
      }

      if (l2 != null) {
        secondNumb = l2.val;
      } else {
        secondNumb = 0;
      }

      tmpSum = firstNumb + secondNumb + inMemory;
      inMemory = tmpSum / 10;                          //целая часть от деления на 10

      tmpResult.next = new ListNode(tmpSum % 10); //дробная часть от деления на 10
      tmpResult = tmpResult.next;

      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null){
        l2 = l2.next;
      }
    }
    return result.next;
  }

  public static void main(String[] args) {
    Solution0002 solution0002 = new Solution0002();
    Solution0002.ListNode listNode03 = solution0002.new ListNode(9);
    Solution0002.ListNode listNode02 = solution0002.new ListNode(9, listNode03);
    Solution0002.ListNode listNode01 = solution0002.new ListNode(9, listNode02);
    Solution0002.ListNode listNode07 = solution0002.new ListNode(9, listNode01);
    Solution0002.ListNode listNode08 = solution0002.new ListNode(9, listNode07);
    Solution0002.ListNode listNode09 = solution0002.new ListNode(9, listNode08);
    Solution0002.ListNode listNode10 = solution0002.new ListNode(9, listNode09);

    Solution0002.ListNode listNode06 = solution0002.new ListNode(9);
    Solution0002.ListNode listNode05 = solution0002.new ListNode(9, listNode06);
    Solution0002.ListNode listNode04 = solution0002.new ListNode(9, listNode05);
    Solution0002.ListNode listNode11 = solution0002.new ListNode(9, listNode04);

    ListNode listNodeResult = solution0002.addTwoNumbers2(listNode10, listNode11);
    System.out.println(listNodeResult);
  }
}
