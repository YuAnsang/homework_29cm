package kr.co._29cm.homework.common.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record OrdersDto(
    Long id,

    Integer totalAmount,

    Integer shippingAmount,

    List<OrdersDetailDto> orderDetails,

    LocalDateTime orderedAt
) {

}
