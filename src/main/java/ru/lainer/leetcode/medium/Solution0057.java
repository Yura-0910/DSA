package ru.lainer.leetcode.medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

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

  public int[][] insert2(int[][] intervals, int[] newInterval) {
    int[][] arrSrc = new int[intervals.length + 1][2];
    int indexSrc = 0; //Индекс для заполнения arrSrc
    boolean addedToList = false;

    for(int[] currentArr: intervals){
      arrSrc[indexSrc] = currentArr;
      indexSrc++;
    }
    //Добавляем еще newInterval
    arrSrc[indexSrc] = newInterval;

    //Сортируем массив по первому элементу каждого одномерного массива
    Arrays.sort(arrSrc, (el1, el2) -> Integer.compare(el1[0], el2[0]));

    List<int[]> listResult = new ArrayList<>();

    //Инициализируем начальными данными
    int curLeft  = arrSrc[0][0];
    int curRight = arrSrc[0][1];

    //Обрабатываем массив arrSrc
    for(int i = 1; i < arrSrc.length; i++){
      if(arrSrc[i][0] <= curRight){
        curRight = Math.max(curRight, arrSrc[i][1]);
        addedToList = false;
      }
      else{
        int[] pair = new int[2];
        pair[0] = curLeft;
        pair[1] = curRight;
        listResult.add(pair);
        addedToList = true;
        curLeft  = arrSrc[i][0];
        curRight = arrSrc[i][1];
      }
    }

    //Добавляем пересекающиеся отрезки
    if(addedToList == false){
      int[] pair = new int[2];
      pair[0] = curLeft;
      pair[1] = curRight;
      listResult.add(pair);
    }
    //Добавляем последний отрезок
    if(addedToList == true){
      listResult.add(arrSrc[arrSrc.length - 1]);
    }

    return listResult.toArray(new int[listResult.size()][2]);
  }

  public static void main(String[] args) {
    int[][] intervals = {{1, 4}, {9, 12}, {19, 22}};
    int[] newInterval = {7, 13};

    var solution = new Solution0057();
    //int[][] result = solution.insert(intervals, newInterval);
    int[][] result = solution.insert2(intervals, newInterval);
    for (int[] currentArr : result) {
      System.out.println(Arrays.toString(currentArr));
    }
  }
}
