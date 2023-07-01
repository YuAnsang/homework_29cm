package kr.co._29cm.homework.domain.persistence.repository;

import kr.co._29cm.homework.domain.persistence.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {

}
