package kr.co._29cm.homework.domain.controller;

import static kr.co._29cm.homework.common.SpringProfiles.TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import kr.co._29cm.homework.TestApplication;
import kr.co._29cm.homework.common.dto.response.ProductDto;
import org.junit.jupiter.api.Test;
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
class ProductControllerTest {

  @Autowired
  private ProductController productController;

  @Test
  public void find_all_products() {
    // given

    // when
    List<ProductDto> products = productController.getAll();

    // then
    assertThat(products).hasSize(19);
    ProductDto tempProduct = products.get(0);
    for (int i = 1; i < products.size(); i++) {
      assertThat(products.get(i).id()).isLessThan(tempProduct.id());
      tempProduct = products.get(i);
    }
  }

}