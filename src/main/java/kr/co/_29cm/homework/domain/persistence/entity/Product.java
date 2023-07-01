package kr.co._29cm.homework.domain.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Product {

  @Id
  private Long id;

  private String name;

  private Integer price;

  private Integer quantity;

  @OneToMany(mappedBy = "product")
  private List<OrdersDetail> ordersDetails;

}
