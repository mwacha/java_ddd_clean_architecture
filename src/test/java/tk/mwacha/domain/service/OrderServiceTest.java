package tk.mwacha.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import tk.mwacha.domain.checkout.service.OrderService;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.checkout.entity.OrderItem;

class OrderServiceTest {

  @Test
  void should_get_total_of_all_orders() {
    var customerId = UUID.randomUUID();
    final var item =
        new OrderItem(UUID.randomUUID(), UUID.randomUUID(), "Item 1", BigDecimal.valueOf(100), 1);
    final var item2 =
        new OrderItem(UUID.randomUUID(), UUID.randomUUID(), "Item 2", BigDecimal.valueOf(200), 2);

    final var order = new Order(UUID.randomUUID(), customerId, List.of(item));
    final var order2 = new Order(UUID.randomUUID(), customerId, List.of(item2));

    final var total = OrderService.total(List.of(order, order2));

    assertEquals(total, BigDecimal.valueOf(500));
  }

  @Test
  void should_place_an_order() {
    final var customer = new Customer(UUID.randomUUID(), "Marcelo");
    final var item =
        new OrderItem(UUID.randomUUID(), UUID.randomUUID(), "Item 1", BigDecimal.valueOf(10), 1);

    final var order = OrderService.placeOrder(customer, List.of(item));

    assertEquals(customer.getRewardPoints(), 5);
    assertEquals(order.total(), BigDecimal.valueOf(10));
  }
}
