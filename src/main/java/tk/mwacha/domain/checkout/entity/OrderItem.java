package tk.mwacha.domain.checkout.entity;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
/**
 * Aggregate
 */
public class OrderItem {

  private UUID id;
  private UUID productId;
  private String name;
  private BigDecimal price;
  private int quantity;

  public BigDecimal price() {
    return this.price.multiply(BigDecimal.valueOf(this.quantity));
  }
}
