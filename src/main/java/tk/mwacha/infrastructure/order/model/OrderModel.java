package tk.mwacha.infrastructure.order.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.infrastructure.customer.model.CustomerModel;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class OrderModel {

  @Id private UUID id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customerId")
  private CustomerModel customerModel;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "orderModel", cascade = CascadeType.MERGE)
  private List<OrderItemModel> items;

  @Column(nullable = false)
  private BigDecimal total;

  public static Order to(OrderModel model) {
    return new Order(
        model.id,
        model.customerModel.getId(),
        model.items.stream().map(OrderItemModel::to).collect(Collectors.toList()));
  }

  public static OrderModel of(Order order) {
    return OrderModel.builder()
        .id(order.getId())
        .customerModel(CustomerModel.builder().id(order.getCustomerId()).build())
        .items(order.getItems().stream().map(OrderItemModel::of).collect(Collectors.toList()))
        .total(order.total())
        .build();
  }
}
