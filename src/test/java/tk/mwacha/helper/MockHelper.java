package tk.mwacha.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.product.entity.Product;
import tk.mwacha.domain.checkout.entity.OrderItem;
import tk.mwacha.domain.customer.valueobject.Address;

public final class MockHelper {
  public static Customer buildCustomer() {
    var customer = new Customer(UUID.randomUUID(), "Customer");
    customer.changeAddress(
        Address.builder().street("Street1").number(1).zip("Zipcode1").city("City1").build());
    customer.addRewardPoints(1);
    customer.activate();
    return customer;
  }

  public static Product buildProduct() {
    return Product.builder()
        .id(UUID.randomUUID())
        .name("Product")
        .price(BigDecimal.valueOf(100))
        .build();
  }

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
