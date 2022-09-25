package tk.mwacha.infrastructure.order.model;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tk.mwacha.domain.checkout.entity.OrderItem;
import tk.mwacha.infrastructure.product.model.ProductModel;

@Entity
@Table(name = "order_items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItemModel {

  @Id private UUID id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "productId")
  private ProductModel productModel;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "orderId")
  private OrderModel orderModel;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private BigDecimal price;

  public static OrderItem to(final OrderItemModel orderItemModel) {
    return OrderItem.builder()
        .id(orderItemModel.id)
        .quantity(orderItemModel.quantity)
        .productId(orderItemModel.productModel.getId())
        .price(orderItemModel.price)
        .name(orderItemModel.name)
        .build();
  }

  public static OrderItemModel of(final OrderItem orderItem) {
    return OrderItemModel.builder()
        .id(orderItem.getId())
        .quantity(orderItem.getQuantity())
        .productModel(ProductModel.builder().id(orderItem.getProductId()).build())
        .price(orderItem.getPrice())
        .name(orderItem.getName())
        .build();
  }
}
