package kr.co._29cm.homework.presentation;

import static kr.co._29cm.homework.common.SpringProfiles.LOCAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kr.co._29cm.homework.common.dto.request.OrdersRequest;
import kr.co._29cm.homework.common.dto.response.OrdersDto;
import kr.co._29cm.homework.common.dto.response.ProductDto;
import kr.co._29cm.homework.common.exception.EntityNotExistsException;
import kr.co._29cm.homework.common.exception.SoldOutException;
import kr.co._29cm.homework.domain.controller.OrdersController;
import kr.co._29cm.homework.domain.controller.ProductController;
import kr.co._29cm.homework.presentation.enums.Command;
import kr.co._29cm.homework.presentation.utils.PrintSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Profile(LOCAL)
@RequiredArgsConstructor
@Component
public class HomeworkMainView implements ApplicationListener<ApplicationStartedEvent> {

  private static boolean commandFlag = true;

  private static boolean orderFlag = false;

  private final ProductController productController;

  private final OrdersController orderController;

  @Override
  public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
    printMainView();
  }


  public void printMainView() {
    commandFlag = true;
    orderFlag = false;
    printCommander();
    if (orderFlag) {
      printProducts();
      printOrder();
    }
  }

  private void printCommander() {
    while (commandFlag) {
      System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
      Scanner scanner = new Scanner(System.in);

      Command command = Command.getByString(scanner.nextLine());
      switch (command) {
        case QUIT -> {
          commandFlag = false;
          orderFlag = false;
          System.out.println("고객님의 주문 감사합니다.");
        }
        case ORDER -> {
          commandFlag = false;
          orderFlag = true;
        }
        case UNKNOWN -> System.out.println("알 수 없는 명령어입니다. 올바르게 입력 해주세요.");
      }
    }
  }

  private void printProducts() {
    int[] columnWidths = new int[]{15, 60, 15, 10}; // init width
    String[] headers = {"상품번호", "상품명", "판매가격", "재고수"};

    List<ProductDto> products = productController.getAll();

    // Print the header
    PrintSupport.printRow(headers, columnWidths);

    // Print the products
    for (ProductDto product : products) {
      PrintSupport.printRow(product.toArray(), columnWidths);
    }
  }

  private void printOrder() {
    List<OrdersRequest> requests = new ArrayList<>();
    try {
      while (orderFlag) {
        System.out.print("상품번호 : ");
        Scanner scanner = new Scanner(System.in);
        String productId = scanner.nextLine();

        if (" ".equals(productId) && requests.size() != 0) {
          OrdersDto order = orderController.order(requests);
          order.print();
          printMainView();
          break;
        }

        if (!" ".equals(productId)) {
          System.out.print("수량 : ");
          String quantity = scanner.nextLine();
          OrdersRequest request = new OrdersRequest(Long.valueOf(productId), Integer.valueOf(quantity));
          requests.add(request);
        }
      }
    } catch (SoldOutException | EntityNotExistsException e) {
      System.out.println(e.getMessage());
      printMainView();
    } catch (NumberFormatException e) {
      System.out.println("올바른 수량을 입력해주세요.");
      printMainView();
    }
  }

}
