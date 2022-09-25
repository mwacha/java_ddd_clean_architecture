package tk.mwacha.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tk.mwacha.domain.product.entity.Product;
import tk.mwacha.domain.product.service.ProductService;

class ProductServiceTest {

  private ProductService service;

  @BeforeEach
  void setup() {
    service = new ProductService();
  }

  @Test
  void should_change_the_price_of_all_products() {
    var product1 = new Product(UUID.randomUUID(), "Product1", BigDecimal.TEN);
    var product2 = new Product(UUID.randomUUID(), "Product2", BigDecimal.valueOf(20));

    var products = List.of(product1, product2);
    service.increasePrice(products, 100);

    assertEquals(product1.getPrice(), BigDecimal.valueOf(20));
    assertEquals(product2.getPrice(), BigDecimal.valueOf(40));
  }
}
