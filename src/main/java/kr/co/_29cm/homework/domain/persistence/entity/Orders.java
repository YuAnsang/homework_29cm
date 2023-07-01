package kr.co._29cm.homework.domain.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer totalAmount;

  private Integer shippingAmount;

  @OneToMany(mappedBy = "order")
  private List<OrdersDetail> orderDetails;

  private LocalDateTime orderedAt;

  public Orders(List<OrdersDetail> orderDetails) {
    this.orderDetails = orderDetails;
    this.totalAmount = orderDetails.stream().mapToInt(orderDetail -> orderDetail.getProduct().getPrice() * orderDetail.getQuantity()).sum();
    this.shippingAmount = totalAmount >= 50000 ? 0 : 2500;
    this.orderedAt = LocalDateTime.now();
    orderDetails.forEach(orderDetail -> orderDetail.addOrder(this));
  }

}
