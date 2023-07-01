package kr.co._29cm.homework.domain.mapper;

import java.util.List;
import kr.co._29cm.homework.common.dto.response.OrdersDetailDto;
import kr.co._29cm.homework.common.dto.response.OrdersDto;
import kr.co._29cm.homework.domain.persistence.entity.Orders;
import kr.co._29cm.homework.domain.persistence.entity.OrdersDetail;

public class OrdersMapper {

  public static OrdersDto toDto(Orders order) {
    List<OrdersDetailDto> orderDetails = order.getOrderDetails().stream()
        .map(OrdersMapper::toDto)
        .toList();
    return new OrdersDto(
        order.getId(),
        order.getTotalAmount(),
        order.getShippingAmount(),
        orderDetails,
        order.getOrderedAt()
    );
  }

  private static OrdersDetailDto toDto(OrdersDetail orderDetail) {
    return new OrdersDetailDto(
        orderDetail.getId(),
        orderDetail.getQuantity()
    );
  }
}
