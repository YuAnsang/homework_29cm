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
    return productRepository.findAll();
  }

  public Product findOnePessimistic(Long id) {
    return productRepository.findByIdPessimistic(id)
        .orElseThrow(() -> new EntityNotExistsException(
            String.format("product id : %d is not exists.", id)));
  }

}
