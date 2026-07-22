package ru.lainer.CodeRun.BackEnd;

import java.util.Scanner;

public class Main002 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    String[] arrLine = line.split(" ");
    int stroki = Integer.parseInt(arrLine[0]);
    int stolbci = Integer.parseInt(arrLine[1]);

    //Создали матрицу с весами
    int[][] arrayVesi = new int[stroki][stolbci];

    //Заполняем матрицу весами
    for (int stroka = 0; stroka < stroki; stroka++) {
      String strokaVesi = scanner.nextLine();
      arrLine = strokaVesi.split(" ");
      for (int stolbec = 0; stolbec < stolbci; stolbec++) {
        arrayVesi[stroka][stolbec] = Integer.parseInt(arrLine[stolbec]);
      }
    }

    //Создаем таблицу для динамического программирования
    int[][] dp = new int[stroki][stolbci];

    //Заполняем нулевую строку матрицы dp, перебирая нулевую строку матрицы arrayVesi
    int summaVesov = 0;
    for (int stolbec = 0; stolbec < stolbci; stolbec++) {
      summaVesov = summaVesov + arrayVesi[0][stolbec];
      dp[0][stolbec] = summaVesov;
    }

    //Заполняем нулевой столбец матрицы dp, перебирая нулевой столбец матрицы arrayVesi
    summaVesov = 0;
    for (int stroka = 0; stroka < stroki; stroka++) {
      summaVesov = summaVesov + arrayVesi[stroka][0];
      dp[stroka][0] = summaVesov;
    }

    /*
     * Для каждой клетки (i, j) (начиная с (1,1)) мы знаем, что попасть туда можно только
     * из клетки сверху (i-1, j) или слева (i, j-1).
     * Мы выбираем тот вариант, где накопленная стоимость меньше,
     * и прибавляем стоимость текущей клетки
     */
    for (int stroka = 1; stroka < stroki; stroka++) {
      for (int stolbec = 1; stolbec < stolbci; stolbec++) {
        dp[stroka][stolbec] = Math.min(dp[stroka - 1][stolbec], dp[stroka][stolbec - 1])
            + arrayVesi[stroka][stolbec];
      }
    }

    System.out.println(dp[stroki - 1][stolbci - 1]);
    scanner.close();
  }

}
