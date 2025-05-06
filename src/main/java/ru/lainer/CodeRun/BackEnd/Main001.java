package ru.lainer.CodeRun.BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main001 {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    String strSource = reader.readLine();
    String[] strArraySource = strSource.split(" ");

    //Конвертируем в массив String в массив int
    int[] intArraySource = new int[strArraySource.length];
    for (int i = 0; i < strArraySource.length; i++) {
      intArraySource[i] = Integer.parseInt(strArraySource[i]);
    }

    Arrays.sort(intArraySource);

    writer.write(String.valueOf(intArraySource[1]));

    reader.close();
    writer.close();
  }
}