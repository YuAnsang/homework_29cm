package kr.co._29cm.homework.domain.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrdersDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Orders order;

  @ManyToOne
  private Product product;

  private Integer quantity;

  public OrdersDetail(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public void addOrder(Orders order) {
    this.order = order;
  }

}
