package tk.mwacha.infrastructure.gateway;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockHelper;
import tk.mwacha.infrastructure.product.gateway.ProductGateway;

@SpringBootTest
@Transactional
class ProductGatewayTest {

  @Autowired private ProductGateway gateway;

  @Test
  void should_create_a_product() {
    final var product = MockHelper.buildProduct();

    gateway.create(product);

    final var productModel = gateway.find(product.getId());

    assertThat(product)
        .usingRecursiveComparison()
        .ignoringFields("id", "name", "price")
        .isEqualTo(productModel);
  }

  @Test
  void should_update_a_product() {
    final var product = MockHelper.buildProduct();

    gateway.create(product);

    final var productModel = gateway.find(product.getId());

    assertThat(product)
        .usingRecursiveComparison()
        .ignoringFields("id", "name", "price")
        .isEqualTo(productModel);

    final var productPersisted = productModel;

    productPersisted.changeName("Teste 2");
    productPersisted.changePrice(BigDecimal.valueOf(200));

    gateway.update(productPersisted);

    final var productModelUpdated = gateway.find(productPersisted.getId());

    assertThat(productPersisted)
        .usingRecursiveComparison()
        .ignoringFields("id", "name", "price")
        .isEqualTo(productModelUpdated);
  }

  @Test
  void should_find_a_product() {
    final var product = MockHelper.buildProduct();

    gateway.create(product);

    final var productModel = gateway.find(product.getId());

    assertThat(product)
        .usingRecursiveComparison()
        .ignoringFields("id", "name", "price")
        .isEqualTo(productModel);
  }

  @Test
  void should_find_all_product() {

    final var product = MockHelper.buildProduct();

    final var product2 = MockHelper.buildProduct();
    product2.changeName("Product 2");

    final var products = List.of(product, product2);

    gateway.createAll(products);

    final var foundProducts = gateway.findAll();

    Assertions.assertIterableEquals(products, foundProducts);
  }
}
