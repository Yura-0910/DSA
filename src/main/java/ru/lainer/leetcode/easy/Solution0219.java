package ru.lainer.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class Solution0219 {

  //"Скользящее окно" не проходит по времени
  public boolean containsNearbyDuplicate2(int[] nums, int k) {

    //Проверяем входные данные
    if (nums.length < 2) {
      return false;
    }

    //Алгоритм "Скользящее окно"
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
          return true;
        }
      }
    }

    return false;
  }

  //Без скользящего окна
  public boolean containsNearbyDuplicate3(int[] nums, int k) {

    //Проверяем входные данные
    if (nums.length < 2) {
      return false;
    }

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        int prevIndex = map.get(nums[i]); //Предыдущий индекс
        if (Math.abs(i - prevIndex) <= k) {
          return true;
        }
      }

      //Если такой ключ уже есть, старое значение "value" перезапишется новым
      //Пара:: элемента массива и его индекс
      map.put(nums[i], i);
    }
    return false;
  }

  //"Скользящее окно" с применением HashMap (для быстродействия)
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      //Если ранее, в HashMap, уже есть такой элемент, то true и длина окна = k
      if(map.containsKey(nums[i])){
        return true;
      }

      map.put(nums[i], i);

      //Пропускаем первые k элементов, потом начинаем удалять
      if (i >= k) {
        //Удаляем элемент, который находиться вне "окна" длинной k
        map.remove(nums[i - k]);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Solution0219 solution0219 = new Solution0219();
    int[] nums = {1,2,3,1,2,3};
    int k = 2;
    boolean result = solution0219.containsNearbyDuplicate(nums, k);
    System.out.println("Result = " + result);
  }
}
