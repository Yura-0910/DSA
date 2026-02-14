package ru.lainer.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution0057 {

  public int[][] insert(int[][] intervals, int[] newInterval) {
    ArrayList<Integer[]> result = new ArrayList<>();
    int processedLines = intervals.length; //Обработано строк в массиве
    boolean flag = false; //Флаг, что отрезки объединили
    int leftSize = -1;

    if (intervals.length == 0) {
      return new int[][]{newInterval};
    }

    for (int i = 0; i < intervals.length; i++) {
      Integer[] tmpResult = new Integer[2];

      if (leftSize != -1) {
        tmpResult[0] = leftSize;
      }

      processedLines--;

      //Отрезки объединили, тогда все остальные отрезки добавляем в tmpResult
      if (flag) {
        tmpResult[0] = intervals[i][0];
        tmpResult[1] = intervals[i][1];
        result.add(tmpResult);
        continue;
      }

      //Левый край
      if (newInterval[0] <= intervals[i][0] && leftSize == -1) {
        tmpResult[0] = newInterval[0];
      }

      if (intervals[i][0] <= newInterval[0] && newInterval[0] <= intervals[i][1]) {
        tmpResult[0] = intervals[i][0];
      }

      if (newInterval[0] > intervals[i][0] && newInterval[0] > intervals[i][1]) {
        tmpResult[0] = intervals[i][0];
        tmpResult[1] = intervals[i][1];
        result.add(tmpResult);
        continue;
      }

      //Правый край
      if (newInterval[0] < intervals[i][0] && newInterval[1] < intervals[i][0] && leftSize == -1) {
        tmpResult[1] = newInterval[1];
        result.add(tmpResult);
        tmpResult = new Integer[2];
        tmpResult[0] = intervals[i][0];
        tmpResult[1] = intervals[i][1];
        result.add(tmpResult);
        flag = true;
        continue;
      }

      if (intervals[i][0] <= newInterval[1] && newInterval[1] <= intervals[i][1]) {
        tmpResult[1] = intervals[i][1];
        result.add(tmpResult);
        flag = true;
        continue;
      }

      //не последний массив
      if (newInterval[1] > intervals[i][0] && newInterval[1] > intervals[i][1]
          && processedLines > 0 && newInterval[1] < intervals[i + 1][0]) {
        if (leftSize != -1) {
          tmpResult[0] = leftSize;
        } else if (tmpResult[0] < intervals[i][0]) {
        } else {
          tmpResult[0] = intervals[i][0];
        }
        tmpResult[1] = newInterval[1];
        result.add(tmpResult);
        flag = true;
        continue;
      }

      //последний массив
      if (newInterval[1] > intervals[i][0] && newInterval[1] > intervals[i][1]
          && processedLines == 0) {
        if (tmpResult[0] <= intervals[i][0]) {
        } else {
          tmpResult[0] = intervals[i][0];
        }
        tmpResult[1] = newInterval[1];
        flag = true;
        result.add(tmpResult);
      }

      //не последний массив
      if (newInterval[1] > intervals[i][0] && newInterval[1] > intervals[i][1]
          && processedLines > 0 && newInterval[1] >= intervals[i + 1][0]) {
        leftSize = tmpResult[0];
      }
    }

    if (processedLines == 0 && flag == false) {
      Integer[] tmpResult = new Integer[2];
      tmpResult[0] = newInterval[0];
      tmpResult[1] = newInterval[1];
      result.add(tmpResult);
    }

    //Данные из "ArrayList<Integer[]> result" помещаем в двумерный массив.
    return result.stream()
        .map(current -> {
          //Преобразуем Integer[] в int[]
          return Arrays.stream(current)
              .mapToInt(value -> value)
              .toArray();
        })
        .toArray(int[][]::new);
  }

  public static void main(String[] args) {
    int[][] intervals = {{1, 4}, {9, 12}, {19, 22}};
    int[] newInterval = {7, 13};

    var solution = new Solution0057();
    int[][] result = solution.insert(intervals, newInterval);
    for (int[] currentArr : result) {
      System.out.println(Arrays.toString(currentArr));
    }
  }
}
