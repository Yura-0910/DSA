package ru.lainer.leetcode.easy;

/*
 * Задача на анно-граммы.
 * Дано:: дан список строк (слова-анно-граммы и обычные слова:: часть из которых повторяется)
 * Вариант № 1:: Уникальны слова выдаем + выдаем анно-граммы с заданным номером вхождения.
 * Вариант № 2:: Уникальные слова пропускаем + выдаем анно-граммы с заданным номером вхождения.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Anagram {

  //number - номер вхождения анаграммы
  public List<String> anagram(List<String> data, int number) {
    Map<String, Integer> map = new HashMap<>();
    List<String> resultList = new ArrayList<>();
    for (String currentStr : data) {
      String key = sortingChar(currentStr);

      /*
       * Если в Map еще нет такого ключа, то помещаем в Map
       */
      map.putIfAbsent(key, number);

      //Если Map содержит ключ, то уменьшаем value на единицу.
      if (map.containsKey(key)) {
        int localNumber = map.get(key);
        map.put(key, --localNumber);
      }

      //Если нашли анно-грамму с нужным номером, то помещаем ее в List.
      if (map.get(key).equals(0)) {
        resultList.add(currentStr);
      }
    }

    /*
     * Вариант № 1:: Уникальны слова выдаем, но значение берем из Map (т.е из ключей),
     * а ключ - это строка, где буквы отсортированы
     */
    /*map.forEach((key, value) -> {
      if (value.equals(number - 1)) {//Т.е только одно вхождение
        resultList.add(key);
      }
    });*/

    //Вариант № 2:: Уникальные слова пропускаем
    /*map.forEach((key, value) -> {
      if (value < (number - 1)) {//Два вхождения это:: 1 < (3-1)
        resultList.add(key);
      }
    });*/

    return resultList;
  }

  //Сортируем буквы в слове
  public String sortingChar(String str) {
    char[] charArray = str.toCharArray();
    Arrays.sort(charArray);
    return new String(charArray);
  }

  public static void main(String[] args) {
    List<String> src = new ArrayList<>();
    src.add("тест");
    src.add("тест10"); //Вариант № 1:: Уникальны слова выдаем
    src.add("тест9");  //Вариант № 2:: Уникальные слова пропускаем
    src.add("тест9");  //Вариант № 2:: Уникальные слова пропускаем
    src.add("тест9");  //Вариант № 2:: Уникальные слова пропускаем

    src.add("ток"); //1-ое вхождение
    src.add("кот"); //2-ое вхождение
    src.add("отк"); //3- е вхождение
    src.add("етст");
    src.add("рот");
    src.add("тор");
    src.add("отр");
    src.add("сетт");

    Anagram anagram = new Anagram();
    int number = 3; //Ищем анно-грамму с этим порядковым номером
    List<String> resultList = anagram.anagram(src, number);
    System.out.println("Анно-граммы с порядковым номером " + number);
    for (String currentStr : resultList) {
      System.out.println(currentStr);
    }
  }
}
