package kr.co._29cm.homework.domain.service;

import java.util.List;
import kr.co._29cm.homework.common.dto.response.ProductDto;
import kr.co._29cm.homework.domain.mapper.ProductMapper;
import kr.co._29cm.homework.domain.persistence.dao.ProductDao;
import kr.co._29cm.homework.domain.persistence.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductDao productDao;

  @Transactional(readOnly = true)
  public List<ProductDto> getAll() {
    List<Product> products = productDao.findAll();
    return products.stream().map(ProductMapper::toDto).toList();
  }

}
