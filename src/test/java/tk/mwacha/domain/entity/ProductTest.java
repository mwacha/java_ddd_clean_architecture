package tk.mwacha.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import tk.mwacha.domain.product.entity.Product;

class ProductTest {

  @Test
  void should_throw_error_when_id_is_null() {

    assertThrows(
            RuntimeException.class,
            () -> {
              new Product(null, "Product1", BigDecimal.valueOf(100));
            })
        .getMessage()
        .equals("Id is required");
  }

  @Test
  void should_throw_error_when_name_is_empty() {

    assertThrows(
            RuntimeException.class,
            () -> {
              new Product(UUID.randomUUID(), "", BigDecimal.valueOf(100));
            })
        .getMessage()
        .equals("Name is required");
  }

  @Test
  void should_throw_error_when_price_is_invalid() {

    assertThrows(
            RuntimeException.class,
            () -> {
              new Product(UUID.randomUUID(), "Teste", BigDecimal.ZERO);
            })
        .getMessage()
        .equals("Price is required");
  }

  @Test
  void should_change_name() {
    final var product = new Product(UUID.randomUUID(), "Teste", BigDecimal.TEN);
    product.changeName("Product 2");

    assertEquals(product.getName(), "Product 2");
  }

  @Test
  void should_change_price() {
    final var product = new Product(UUID.randomUUID(), "Teste", BigDecimal.TEN);
    product.changePrice(BigDecimal.valueOf(20));

    assertEquals(product.getPrice(), BigDecimal.valueOf(20));
  }
}
