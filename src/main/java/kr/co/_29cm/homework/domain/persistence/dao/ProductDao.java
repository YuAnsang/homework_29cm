package kr.co._29cm.homework.domain.persistence.dao;

import java.util.List;
import kr.co._29cm.homework.domain.persistence.entity.Product;
import kr.co._29cm.homework.domain.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductDao {

  private final ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product findById(Long id) {
    // TODO throw하는 exception 변경 필요.
    return productRepository.findById(id)
        .orElseThrow();
  }

}
