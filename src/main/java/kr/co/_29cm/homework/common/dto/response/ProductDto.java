package kr.co._29cm.homework.common.dto.response;

public record ProductDto(
    Long id,
    String name,
    Integer price,
    Integer quantity
) {

  public String[] toArray() {
    return new String[]{String.valueOf(id), name, String.valueOf(price), String.valueOf(quantity)};
  }

}
