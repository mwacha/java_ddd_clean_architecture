package tk.mwacha.helper;

import tk.mwacha.domain.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public final class MockProductHelper {


  public static Product buildProduct() {
    return Product.builder()
        .id(UUID.randomUUID())
        .name("Product")
        .price(BigDecimal.valueOf(100))
        .build();
  }

  public static Product buildProduct2() {
    return Product.builder()
            .id(UUID.randomUUID())
            .name("Product2")
            .price(BigDecimal.valueOf(200))
            .build();
  }

  public static List<Product> buildProducts() {
    return List.of(buildProduct(), buildProduct2());
  }

}
