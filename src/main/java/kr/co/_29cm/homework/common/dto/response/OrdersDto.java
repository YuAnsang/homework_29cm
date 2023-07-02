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

  public void print() {
    System.out.println("--------------------------------------");
    List<OrdersDetailDto> ordersDetails = this.orderDetails();
    for (OrdersDetailDto ordersDetail : ordersDetails) {
      System.out.printf("%s - %d개\n", ordersDetail.productName(), ordersDetail.quantity());
    }
    System.out.println("--------------------------------------");
    System.out.println("주문금액 : " + this.totalAmount());
    if (this.shippingAmount() != 0) {
      System.out.println("배송비 : " + this.shippingAmount());
    }
    System.out.println("--------------------------------------");
    System.out.println("지불금액 : " + (this.totalAmount() + this.shippingAmount()));
    System.out.println("--------------------------------------");
  }

}
