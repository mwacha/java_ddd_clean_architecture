package tk.mwacha.domain.checkout.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.checkout.entity.OrderItem;

public class OrderService {

  public static BigDecimal total(final List<Order> orders) {
    return orders.stream().map(o -> o.total()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
  }

  public static Order placeOrder(final Customer customer, final List<OrderItem> items) {
    if (items.isEmpty()) {
      throw new RuntimeException("Order must have at least one item");
    }

    final var order = new Order(UUID.randomUUID(), customer.getId(), items);
    customer.addRewardPoints(order.total().divide(BigDecimal.valueOf(2)).intValue());
    return order;
  }
}
