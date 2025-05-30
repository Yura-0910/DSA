package ru.lainer.leetcode.easy;

public class Solution0035 {

  public int searchInsert(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int mid = 0; //Середина
    int current = 0;

    //Если в массиве только один элемент
    if (left == right) {
      if (nums[0] >= target) {
        return 0;
      } else {
        return 1;
      }
    }

    //Если в массиве нет нужного числа и target идет после последнего элемента массива
    if (nums[nums.length - 1] < target) {
      int currentIndex = nums.length - 1;
      return ++currentIndex;
    }

    //Если в массиве нет нужного числа и target идет до первого элемента массива
    if (target < nums[0]) {
      return 0;
    }

    //Сам бинарный поиск
    while (left <= right) {
      mid = left + (right - left) / 2; //находим середину
      current = nums[mid];
      if (current == target) {
        return mid;
      } else if (current + 1 == target) {
        return mid + 1;
      } else if (current - 1 == target) {
        if (nums[mid - 1] == target){ //Есть элемент
          return mid - 1;
        }
        else {
          return mid;
        }
      } else if (current < target) {
        left = mid + 1; //Идем вправо
      } else {
        right = mid - 1;//Идем влево
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    int[] src = {2,3,4,7,8,9};
    int target = 11;
    Solution0035 solution0035 = new Solution0035();
    int result = solution0035.searchInsert(src, target);
    System.out.println("Индекс " + target + " равен " + result);
  }
}
