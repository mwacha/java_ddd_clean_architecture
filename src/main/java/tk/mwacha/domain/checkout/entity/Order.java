package tk.mwacha.domain.checkout.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Order {
  private UUID id;
  private UUID customerId;
  private List<OrderItem> items;

  public Order(final UUID id, final UUID customerId, final List<OrderItem> items) {
    this.id = id;
    this.customerId = customerId;
    this.items = items;

    validate();
  }

  public void addItem(final OrderItem item) {
    this.items.add(item);
  }

  public BigDecimal total() {
    return this.items.stream().map(i -> i.price()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
  }

  private void validate() {

    if (this.id == null) {
      throw new RuntimeException("Id is required");
    }

    if (this.customerId == null) {
      throw new RuntimeException("Customer Id is required");
    }

    if (this.items == null || this.items.isEmpty()) {
      throw new RuntimeException("Item qtd must be greater than zero");
    }

    this.items.stream()
        .filter(i -> i.getQuantity() <= 0)
        .findAny()
        .ifPresent(
            e -> {
              throw new RuntimeException("Quantity must be greater than zero");
            });
  }
}
