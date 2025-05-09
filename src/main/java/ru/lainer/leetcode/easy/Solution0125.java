package ru.lainer.leetcode.easy;

import java.util.stream.Collectors;

public class Solution0125 {

  public boolean isPalindrome(String s) {
    s = s.toLowerCase();
    String strSource = s.chars()
        .filter(c -> Character.isLetterOrDigit(c))
        .mapToObj(c -> (char) c)
        .map(str -> String.valueOf(str))
        .collect(Collectors.joining());

    for (int i = 0, j = strSource.length() - 1; i < strSource.length() && j >= 0; i++, j--) {
      if (strSource.charAt(i) != strSource.charAt(j)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String source = "Aman,aplan,acanal:Panama";
    Solution0125 solution0125 = new Solution0125();
    boolean result = solution0125.isPalindrome(source);
    System.out.println("Is palindrome:: " + result);
  }

}
