package kr.co._29cm.homework.common.dto.request;

public record OrdersRequest(
    Long productId,
    Integer quantity
) {

}
