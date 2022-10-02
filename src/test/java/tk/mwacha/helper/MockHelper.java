package tk.mwacha.helper;

import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.checkout.entity.OrderItem;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;
import tk.mwacha.domain.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public final class MockHelper {
  public static Customer buildCustomer() {
    var customer = new Customer(UUID.randomUUID(), "Customer");
    customer.changeAddress(builderAddress());
    customer.addRewardPoints(1);
    customer.activate();
    return customer;
  }

  public static List<Customer> buildCustomers() {
    var customer1 = new Customer(UUID.randomUUID(), "Customer");
    customer1.changeAddress(builderAddress());
    customer1.addRewardPoints(1);
    customer1.activate();

    var customer2 = new Customer(UUID.randomUUID(), "Customer2");
    customer2.changeAddress(builderAddress2());
    customer2.addRewardPoints(2);
    customer2.activate();

    return List.of(customer1, customer2);
  }

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

  public static Address builderAddress() {
    return Address.builder().street("Street1").number(1).zip("Zipcode1").city("City1").build();
  }
  public static Address builderAddress2() {
    return Address.builder().street("Street2").number(1).zip("Zipcode2").city("City2").build();
  }

}
