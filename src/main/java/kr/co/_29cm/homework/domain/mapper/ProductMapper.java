package kr.co._29cm.homework.domain.mapper;

import kr.co._29cm.homework.common.dto.response.ProductDto;
import kr.co._29cm.homework.domain.persistence.entity.Product;

public class ProductMapper {

  public static ProductDto toDto(Product product) {
    return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getStockQuantity()
    );
  }

}
