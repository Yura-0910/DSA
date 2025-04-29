package ru.lainer.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution0003 {

  //скорость алгоритма 99 миллисекунд (Beats 6.82%), память:: Beats 10.43%
  public int lengthOfLongestSubstring(String s) {
    int current;
    boolean noDuplicate = true;
    Set<String> subStringSet = new HashSet<>();
    int maxCountOfElement = 0;

    for (int i = 0; i < s.length(); i++) {
      current = i;
      noDuplicate = true;
      while (noDuplicate && current < s.length()) {
        if ((current + 1) <= s.length()) {
          noDuplicate = subStringSet.add(s.substring(current, current + 1));
          current++;
        }
      }

      if (maxCountOfElement < subStringSet.size()) {
        maxCountOfElement = subStringSet.size();
      }

      subStringSet.clear();
    }

    return maxCountOfElement;
  }

  //Оптимизированная версия:: исчерпан лимит времени
  public int lengthOfLongestSubstring2(String s) {
    int maxCountOfElement = 0;
    int tmpCount = 0;
    boolean noDuplicate = true;
    String[] uniqueArray = new String[s.length()]; //Заменить на массив char
    for (int i = 0; i <= s.length() - 1; i++) {
      for (int current = i; current <= s.length() - 1; current++) { //Заменить на while
        String fromS = s.substring(current, current + 1);//Заменить на charAt()

        //Ищем дубликаты в uniqueArray
        for (String fromUniqueArray : uniqueArray) {
          if (fromUniqueArray != null && fromUniqueArray.equals(fromS)) {
            noDuplicate = false;
            break;
          }
          if (fromUniqueArray == null) {
            break;
          }
        }

        if (noDuplicate) {
          uniqueArray[current - i] = fromS;
        } else {
          break;
        }
      }

      tmpCount = (int) Arrays.stream(uniqueArray).filter(Objects::nonNull).count();
      if (maxCountOfElement < tmpCount) {
        maxCountOfElement = tmpCount;
      }

      //Очищаем массив uniqueArray
      Arrays.fill(uniqueArray, null);
      noDuplicate = true;
    }

    return maxCountOfElement;
  }

  //Оптимизированная версия:: алгоритм "скользящего окна" + HashSet
  //скорость:: 06 миллисекунд (Beats 70.43%), память 45.02Мб (Beats 23.73%)
  public int lengthOfLongestSubstring3(String s) {
    Set<Character> set = new HashSet<>();
    int maxLength = 0;
    int left = 0;

    for (int right = 0; right < s.length(); right++) {
      char currentChar = s.charAt(right);

      // Если символ уже есть в множестве, двигаем левую границу
      while (set.contains(currentChar)) {
        set.remove(s.charAt(left));
        left++;
      }

      set.add(currentChar);
      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  //Заменяем substring на charAt():: скорость 62 миллисекунды (Beats 10.96%)
  public int lengthOfLongestSubstring4(String s) {
    int current;
    boolean noDuplicate = true;
    Set<Character> subStringSet = new HashSet<>();
    int maxCountOfElement = 0;

    for (int i = 0; i < s.length(); i++) {
      current = i;
      noDuplicate = true;
      while (noDuplicate && current < s.length()) {
        if ((current + 1) <= s.length()) {
          noDuplicate = subStringSet.add(s.charAt(current));
          current++;
        }
      }

      if (maxCountOfElement < subStringSet.size()) {
        maxCountOfElement = subStringSet.size();
      }

      subStringSet.clear();
    }

    return maxCountOfElement;
  }

  public static void main(String[] args) {
    String s = "abcabcbb";
    Solution0003 solution0003 = new Solution0003();
    System.out.println(solution0003.lengthOfLongestSubstring4(s));
  }
}
