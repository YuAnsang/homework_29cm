package kr.co._29cm.homework.domain.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import kr.co._29cm.homework.common.exception.SoldOutException;
import lombok.Getter;

@Getter
@Entity
public class Product {

  @Id
  private Long id;

  private String name;

  private Integer price;

  private Integer stockQuantity;

  @OneToMany(mappedBy = "product")
  private List<OrdersDetail> ordersDetails;

  public void order(Integer orderQuantity) {
    if (this.stockQuantity - orderQuantity < 0) {
      throw new SoldOutException(String.format("SoldOutException 발생. 주문한 상품량(%d)이 재고량(%d)보다 큽니다.", orderQuantity, stockQuantity));
    }
    this.stockQuantity = this.stockQuantity - orderQuantity;
  }

}
