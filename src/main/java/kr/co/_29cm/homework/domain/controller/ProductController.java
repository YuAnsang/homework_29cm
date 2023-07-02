package kr.co._29cm.homework.domain.controller;

import java.util.List;
import kr.co._29cm.homework.common.dto.response.ProductDto;
import kr.co._29cm.homework.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductController {

  private final ProductService productService;

  public List<ProductDto> getAll() {
    return productService.getAll();
  }

}
