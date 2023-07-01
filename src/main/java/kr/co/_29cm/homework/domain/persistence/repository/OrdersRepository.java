package kr.co._29cm.homework.domain.persistence.repository;

import kr.co._29cm.homework.domain.persistence.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
