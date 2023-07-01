package kr.co._29cm.homework.domain.controller;


import static kr.co._29cm.homework.common.SpringProfiles.TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kr.co._29cm.homework.TestApplication;
import kr.co._29cm.homework.common.dto.request.OrdersRequest;
import kr.co._29cm.homework.common.dto.response.OrdersDto;
import kr.co._29cm.homework.common.exception.EntityNotExistsException;
import kr.co._29cm.homework.common.exception.SoldOutException;
import kr.co._29cm.homework.domain.persistence.entity.Product;
import kr.co._29cm.homework.domain.persistence.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup(value = {
    @Sql("/sql/init.sql"),
    @Sql(value = "/sql/truncate.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
@ActiveProfiles(TEST)
@SpringBootTest(classes = TestApplication.class)
class OrdersControllerTest {

  @Autowired
  private OrdersController orderController;

  @Autowired
  private ProductRepository productRepository;

  @DisplayName("상품 단건 주문 -> 성공")
  @CsvSource(value = {
      "778422:1:6",
      "748943:10:79",
      "779943:15:74",
  }, delimiter = ':')
  @ParameterizedTest
  void order_success(Long productId, Integer quantity, Integer expectedRemainingQuantity) {
    // given
    List<OrdersRequest> requests = new ArrayList<>();
    OrdersRequest request = new OrdersRequest(productId, quantity);
    requests.add(request);

    // when
    OrdersDto saved = orderController.order(requests);
    Product product = productRepository.findById(productId).orElseThrow();

    // then
    assertThat(saved.id()).isNotNull();
    assertThat(saved.shippingAmount()).isEqualTo(saved.totalAmount() >= 50000 ? 0 : 2500);
    assertThat(saved.orderedAt()).isNotNull();
    assertThat(product.getStockQuantity()).isEqualTo(expectedRemainingQuantity);
  }

  @DisplayName("상품 단건 주문 -> 실패 (재고보다 주문량이 많음)")
  @CsvSource(value = {
      "768848:46",
      "748943:90",
      "779989:44",
      "779943:90",
      "768110:80",
      "517643:27",
      "706803:82",
      "759928:86",
      "213341:100",
      "377169:61",
      "744775:36",
      "779049:65",
      "611019:8",
      "628066:9",
      "502480:42",
      "782858:51",
      "760709:71",
      "778422:8",
      "648418:6",
  }, delimiter = ':')
  @ParameterizedTest
  void order_failure_cause_sold_out(Long productId, Integer quantity) {
    // given
    List<OrdersRequest> requests = new ArrayList<>();
    OrdersRequest request = new OrdersRequest(productId, quantity);
    requests.add(request);

    // when & Then
    assertThatThrownBy(() -> orderController.order(requests))
        .isInstanceOf(SoldOutException.class)
        .hasMessageContaining("SoldOutException 발생");
  }

  @DisplayName("상품 단건 주문 -> 실패 (존재하지 않는 상품)")
  @Test
  void order_failure_cause_not_exists_entity() {
    // given
    Long productId = 999999L;
    List<OrdersRequest> requests = new ArrayList<>();
    OrdersRequest request = new OrdersRequest(productId, 100);
    requests.add(request);

    // when & Then
    assertThatThrownBy(() -> orderController.order(requests))
        .isInstanceOf(EntityNotExistsException.class)
        .hasMessageContaining(String.format("product id : %d is not exists.", productId));
  }

  @Test
  void order_multi_thread_test() throws Exception {
    // given
    int numberOfThreads = 100;
    ExecutorService executorService = Executors.newFixedThreadPool(100);
    CountDownLatch latch = new CountDownLatch(numberOfThreads);
    Set<Long> duplicateSet = Collections.synchronizedSet(new HashSet<>());
    Long productId = 760709L;

    // when
    for (int i = 0; i < numberOfThreads; i++) {
      executorService.execute(() -> {
        while (latch.getCount() != 0) {
          try {
            List<OrdersRequest> requests = new ArrayList<>();
            OrdersRequest request = new OrdersRequest(productId, 1);
            requests.add(request);
            OrdersDto order = orderController.order(requests);
            duplicateSet.add(order.id());
          } catch (SoldOutException e) {
            latch.countDown();
          }
        }
      });
    }
    latch.await(60, TimeUnit.SECONDS);
    Product product = productRepository.findById(productId).orElseThrow();

    // then
    assertThat(duplicateSet).hasSize(70);
    assertThat(product.getStockQuantity()).isEqualTo(0);

  }

}