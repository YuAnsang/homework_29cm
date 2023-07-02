package kr.co._29cm.homework.presentation.utils;

public class PrintSupport {

  public static void printRow(String[] row, int[] columnWidths) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < row.length; i++) {
      String value = row[i];
      int width = columnWidths[i];
      sb.append(padRight(value, width));
      sb.append("  ");
    }
    System.out.println(sb);
  }

  public static String padRight(String s, int width) {
    if (s.length() >= width) {
      return s;
    }
    return String.format("%-" + width + "s", s);
  }

}
