package kr.co._29cm.homework.domain.service;

import java.util.ArrayList;
import java.util.List;
import kr.co._29cm.homework.common.dto.request.OrdersRequest;
import kr.co._29cm.homework.common.dto.response.OrdersDto;
import kr.co._29cm.homework.domain.mapper.OrdersMapper;
import kr.co._29cm.homework.domain.persistence.dao.OrdersDao;
import kr.co._29cm.homework.domain.persistence.dao.ProductDao;
import kr.co._29cm.homework.domain.persistence.entity.Orders;
import kr.co._29cm.homework.domain.persistence.entity.OrdersDetail;
import kr.co._29cm.homework.domain.persistence.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

  private final OrdersDao orderDao;

  private final ProductDao productDao;

  @Transactional
  public OrdersDto order(List<OrdersRequest> requests) {
    List<OrdersDetail> orderDetails = this.createDetails(requests);
    Orders order = new Orders(orderDetails);
    Orders saved = orderDao.save(order);
    return OrdersMapper.toDto(saved);
  }

  private List<OrdersDetail> createDetails(List<OrdersRequest> requests) {
    List<OrdersDetail> orderDetails = new ArrayList<>();
    for (OrdersRequest request : requests) {
      Product product = productDao.findOnePessimistic(request.productId());
      product.order(request.quantity());
      OrdersDetail orderDetail = new OrdersDetail(product, request.quantity());
      orderDetails.add(orderDetail);
    }
    return orderDetails;
  }

}
