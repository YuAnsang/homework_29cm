package kr.co._29cm.homework.domain.persistence.dao;

import java.util.List;
import kr.co._29cm.homework.common.exception.EntityNotExistsException;
import kr.co._29cm.homework.domain.persistence.entity.Product;
import kr.co._29cm.homework.domain.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductDao {

  private final ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAllByOrderByIdDesc();
  }

  public Product findOnePessimistic(Long id) {
    return productRepository.findByIdPessimistic(id)
        .orElseThrow(() -> new EntityNotExistsException(
            String.format("상품번호 %s는 존재하지 않는 상품입니다.", id)));
  }

}
