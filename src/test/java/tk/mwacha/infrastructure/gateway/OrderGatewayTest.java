package tk.mwacha.infrastructure.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockHelper;
import tk.mwacha.infrastructure.customer.gateway.CustomerGateway;
import tk.mwacha.infrastructure.order.gateway.OrderGateway;
import tk.mwacha.infrastructure.product.gateway.ProductGateway;

@SpringBootTest
@Transactional
class OrderGatewayTest {

  @Autowired private OrderGateway orderGateway;
  @Autowired private CustomerGateway customerGateway;
  @Autowired private ProductGateway productGateway;

  @Test
  void should_create_a_new_order() {
    final var customer = MockHelper.buildCustomer();

    customerGateway.create(customer);

    final var product = MockHelper.buildProduct();

    productGateway.create(product);

    final var orderItem = MockHelper.buildOrderItem(product);

    final var order = MockHelper.buildOrder(customer, List.of(orderItem));
    orderGateway.create(order);

    final var orderPersisted = orderGateway.find(order.getId());

    assertThat(orderPersisted.getId()).isEqualTo(order.getId());
    assertThat(orderPersisted.getCustomerId()).isEqualTo(order.getCustomerId());
    assertThat(orderPersisted.total()).isEqualTo(order.total());

    assertThat(orderPersisted.getItems()).usingRecursiveComparison().isEqualTo(order.getItems());
  }

  @Test
  void should_update_a_order() {
    final var customer = MockHelper.buildCustomer();

    customerGateway.create(customer);

    final var product = MockHelper.buildProduct();
    final var product2 = MockHelper.buildProduct();
    product2.changeName("Product2");

    productGateway.createAll(List.of(product, product2));

    final var orderItem = MockHelper.buildOrderItem(product);

    final var order = MockHelper.buildOrder(customer, List.of(orderItem));
    orderGateway.create(order);

    final var orderPersisted = orderGateway.find(order.getId());

    final var orderItem2 = MockHelper.buildOrderItem(product2);

    orderPersisted.addItem(orderItem2);

    orderGateway.update(orderPersisted);

    final var orderUpdated = orderGateway.find(orderPersisted.getId());

    assertThat(orderUpdated.getId()).isEqualTo(orderPersisted.getId());
    assertThat(orderUpdated.getCustomerId()).isEqualTo(orderPersisted.getCustomerId());
    assertThat(orderUpdated.total()).isEqualTo(orderPersisted.total());
    assertThat(orderUpdated.getItems().size()).isEqualTo(orderUpdated.getItems().size());
  }

  @Test
  void should_find_a_order() {

    final var customer = MockHelper.buildCustomer();

    customerGateway.create(customer);

    final var product = MockHelper.buildProduct();

    productGateway.create(product);

    final var orderItem = MockHelper.buildOrderItem(product);

    final var order = MockHelper.buildOrder(customer, List.of(orderItem));
    orderGateway.create(order);

    final var orderResult = orderGateway.find(order.getId());

    assertThat(order).usingRecursiveComparison().ignoringFields("id").isEqualTo(orderResult);
  }

  @Test
  void should_throw_an_error_when_find_a_order() {

    final var customer = MockHelper.buildCustomer();

    customerGateway.create(customer);

    final var product = MockHelper.buildProduct();

    productGateway.create(product);

    final var orderItem = MockHelper.buildOrderItem(product);

    final var order = MockHelper.buildOrder(customer, List.of(orderItem));
    orderGateway.create(order);

    Assertions.assertThrows(RuntimeException.class, () -> orderGateway.find(UUID.randomUUID()));
  }

  @Test
  void should_find_all_orders() {

    final var customer = MockHelper.buildCustomer();

    customerGateway.create(customer);

    final var product = MockHelper.buildProduct();
    final var product2 = MockHelper.buildProduct();
    product2.changeName("Product2");

    productGateway.createAll(List.of(product, product2));

    final var orderItem = MockHelper.buildOrderItem(product);
    final var orderItem2 = MockHelper.buildOrderItem(product2);

    final var order = MockHelper.buildOrder(customer, List.of(orderItem));
    final var order2 = MockHelper.buildOrder(customer, List.of(orderItem2));
    orderGateway.createAll(List.of(order, order2));

    final var foundOrders = orderGateway.findAll();
    assertThat(foundOrders).hasSize(2);
    assertTrue(foundOrders.contains(order));
    assertTrue(foundOrders.contains(order2));
  }
}
