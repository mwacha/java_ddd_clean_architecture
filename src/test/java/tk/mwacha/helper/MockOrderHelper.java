package tk.mwacha.helper;

import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.checkout.entity.OrderItem;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.product.entity.Product;

import java.util.List;
import java.util.UUID;

public final class MockOrderHelper {

  public static OrderItem buildOrderItem(final Product product) {
    return OrderItem.builder()
        .id(UUID.randomUUID())
        .name(product.getName())
        .price(product.getPrice())
        .productId(product.getId())
        .quantity(2)
        .build();
  }

  public static Order buildOrder(final Customer customer, final List<OrderItem> orders) {
    return new Order(UUID.randomUUID(), customer.getId(), orders);
  }

}
