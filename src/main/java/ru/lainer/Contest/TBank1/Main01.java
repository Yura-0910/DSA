package ru.lainer.Contest.TBank1;

import java.util.Arrays;

public class Main01 {

  public static String test(String s) {
    String[] strArray =  s.split("");

    //Из массива String формируем массив int
    int[] intArray = Arrays.stream(strArray)
        .mapToInt(element -> Integer.parseInt(element))
        .toArray();

    //Сортируем массив
    Arrays.sort(intArray);

    //Ищем первый ненулевой элемент
    int index = -1;
    for (int i: intArray){
      index++ ;
      if(i != 0){
        break;
      }
    }

    if(intArray[0] == 0){
      //Меняем местами первый нулевой элемент и первый не нулевой элемент
      int tmp = intArray[0];
      intArray[0] = intArray[index];
      intArray[index] = tmp;
    }

    //Из массива получаем строку
    StringBuilder result = new StringBuilder();
    for (int i: intArray){
      result.append(i);
    }

    return result.toString();
  }

  public static void main(String[] args) {
    String result1 = test("682911220");
    System.out.println(result1);

    String result2 = test("01");
    System.out.println(result2);

    String result3 = test("0002234501");
    System.out.println(result3);
  }

}
