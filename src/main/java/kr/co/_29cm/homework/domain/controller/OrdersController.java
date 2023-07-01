package kr.co._29cm.homework.domain.controller;

import java.util.List;
import kr.co._29cm.homework.common.dto.request.OrdersRequest;
import kr.co._29cm.homework.common.dto.response.OrdersDto;
import kr.co._29cm.homework.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrdersController {

  private final OrderService orderService;

  public OrdersDto order(List<OrdersRequest> requests) {
    return orderService.order(requests);
  }

}
