package kr.co._29cm.homework.domain.persistence.dao;

import kr.co._29cm.homework.domain.persistence.entity.Orders;
import kr.co._29cm.homework.domain.persistence.repository.OrdersDetailRepository;
import kr.co._29cm.homework.domain.persistence.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Repository
public class OrdersDao {

  private final OrdersRepository orderRepository;

  private final OrdersDetailRepository orderDetailRepository;

  public Orders save(Orders order) {
    Orders saved = orderRepository.save(order);
    orderDetailRepository.saveAll(order.getOrderDetails());
    return saved;
  }

}
