package ru.lainer.Contest.TBank1;

import java.util.Arrays;

public class Main03 {

  private int n; //Длина строки с входными данными.

  public int maxArea(String s){
    int[] sArray = s.chars()
        .map(el -> el - '0')
        .toArray();

    n = sArray.length;
    int[][] aArray = new int[n][n];

    //строим таблицу "а" из задания.
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        aArray[i][j] = sArray[Math.floorMod(i - j, n)];
      }
    }

    //Формируем гистограмму высот из aArray
    int[][] aHistogram  =  heightHistogram(aArray);

    //Находим максимальную площадь для каждой строки из aHistogram
    int[] tmpAreaArray = new int[n];
    int index = 0; //Для запллнения tmpAreaArray
    for(int[] current: aHistogram){
      tmpAreaArray[index] = maxAreaForRow(current);
      index++;
    }

    //Находим максимальный элемент среди всех площадей в массиве.
    return Arrays.stream(tmpAreaArray).max().getAsInt();
  }

  //Формируем гистограмму высот из aArray
  public int[][] heightHistogram(int[][] aArray){
    int[][] aHistogram = new int[n][n];

    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        if(aArray[i][j] != 0){
          if(i == 0){
            aHistogram[i][j] = aHistogram[i][j] + 1;
          }
          else{
            aHistogram[i][j] = aHistogram[i-1][j] + 1;
          }
        }
        else{
          aHistogram[i][j] = 0;
        }
      }
    }

    return aHistogram;
  }

  //Возвращает максимальную площадь для конкретной строки из aHistogram
  public int maxAreaForRow(int[] singleRow){

    //Тут храним максимальную площадь для каждого столбца.
    int[] maxAreaArray = new int[n];
    int width = 0;//Ширина столбца

    for(int i = 0; i < n; i++){
      //Вычисляем ширину
      if(singleRow[i] != 0){
        width++;
      }
      else{
        width = 0;
      }

      maxAreaArray[i] = width * singleRow[i];
    }

    //Находим максимальный элемент среди всех площадей в массиве.
    return Arrays.stream(maxAreaArray).max().getAsInt();
  }

  public static void main(String[] args) {
    String s = "011";
    Main03 main = new Main03();
    System.out.println("Исходные данные:: " + s);
    System.out.println("max площадь := " +main.maxArea(s));
    s = "11011";
    System.out.println("Исходные данные:: " + s);
    System.out.println("max площадь := " +main.maxArea(s));
    s = "01101";
    System.out.println("Исходные данные:: " + s);
    System.out.println("max площадь := " +main.maxArea(s));
  }
}
