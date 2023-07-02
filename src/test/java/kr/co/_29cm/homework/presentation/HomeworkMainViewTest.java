package kr.co._29cm.homework.presentation;

import static kr.co._29cm.homework.common.SpringProfiles.TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import kr.co._29cm.homework.domain.controller.OrdersController;
import kr.co._29cm.homework.domain.controller.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(TEST)
class HomeworkMainViewTest {

  private HomeworkMainView homeworkMainView;

  @MockBean
  private ProductController productController;

  @MockBean
  private OrdersController orderController;

  @BeforeEach
  public void init() {
    homeworkMainView = new HomeworkMainView(productController, orderController);
  }

  @DisplayName("입력 시 종료 명렁에 대해 테스트한다.")
  @ValueSource(strings = {"q", "quit"})
  @ParameterizedTest
  public void quit_test(String command) {
    // given
    InputStream in = new ByteArrayInputStream(command.getBytes());
    System.setIn(in);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // when
    homeworkMainView.printMainView();

    // then
    assertThat(outputStream.toString()).contains("고객님의 주문 감사합니다.");
  }

  @DisplayName("입력 시 잘못된 명렁에 대해 테스트한다.")
  @Test
  public void invalid_command_test() {
    // given
    String command = "t";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    System.setIn(in);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // when & then
    // TODO 흐름을 테스트 할 수 있는 방법 없는지 확인 필요 (실제 Order에 대한 Integration 테스트용으로 필요할듯)
//    homeworkMainView.printMainView();
    assertThatThrownBy(() -> homeworkMainView.printMainView())
        .isInstanceOf(NoSuchElementException.class); // 두번째 명령어에 대한 테스트
    assertThat(outputStream.toString()).contains("알 수 없는 명령어입니다. 올바르게 입력 해주세요.");
  }

}