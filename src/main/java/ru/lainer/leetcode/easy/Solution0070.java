package ru.lainer.leetcode.easy;

import java.util.Map;
import java.util.HashMap;

public class Solution0070 {

  //Ключ - количество шагов, значение - количество способов
  Map<Integer, Integer> memo = new HashMap<>();

  /* Комбинация способов - это динамическое программирование (чтоб не повторять)
     n = 1 ступенька:: 1 способ
     n = 2 ступеньки:: 2 способами
     n = 3 ступеньки:: 3 способа
     n = 4 ступеньки:: 5 способов
   * Это напоминает последовательность Фибоначчи, где каждое последующее число
     равно сумме двух предыдущих, для n ≥ 2
   * Формула Фибоначчи:: F(n) = F(n-1) + F(n-2), для n ≥ 2
   * Решение с мемоизацией.
   */
  public int climbStairs(int n) {
    /*
     * Чтоб выйти из рекурсивных вызовов метода climbStairs при n = 3
     */
    if (n == 1) { //n = 1 (это 1 ступенька):: 1 способ
      return 1;
    }
    if (n == 2) { //n = 2 (это 2 ступеньки):: 2 способа
      return 2;
    }

    if (!memo.containsKey(n)) {
      /*
       * Реализуя формулу Фибоначчи, считаем количество возможных способов,
       *которым можно достигнуть заданной ступеньки
       */
      int waysNumber = climbStairs(n - 1) + climbStairs(n - 2);

      memo.put(n, waysNumber);
    }

    return memo.get(n);
  }

  public static void main(String[] args) {
    int stupenki = 5;
    Solution0070 solution0070 = new Solution0070();
    int result = solution0070.climbStairs(stupenki);
    System.out.println("Кол-во ступенек = " + stupenki + " Result = " + result);
  }
}
