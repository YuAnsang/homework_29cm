package kr.co._29cm.homework.common.dto.response;

public record ProductDto(
    Long id,
    String name,
    Integer price,
    Integer quantity
) {

}
