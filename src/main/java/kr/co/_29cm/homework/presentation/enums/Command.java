package kr.co._29cm.homework.presentation.enums;

import lombok.Getter;

@Getter
public enum Command {

  ORDER,
  QUIT,
  UNKNOWN;

  public static Command getByString(String command) {
    switch (command) {
      case "o", "order" -> {
        return ORDER;
      }
      case "q", "quit" -> {
        return QUIT;
      }
      default -> {
        return UNKNOWN;
      }
    }
  }

}
