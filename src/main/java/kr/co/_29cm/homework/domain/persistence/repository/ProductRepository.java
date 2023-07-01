package kr.co._29cm.homework.domain.persistence.repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import kr.co._29cm.homework.domain.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Lock(value = LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT p FROM Product p WHERE p.id = :id")
  Optional<Product> findByIdPessimistic(@NonNull Long id);

}
