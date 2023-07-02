package kr.co._29cm.homework.common.dto.response;

public record OrdersDetailDto(
    Long id,
    Long productId,
    String productName,
    Integer quantity
) {

}
