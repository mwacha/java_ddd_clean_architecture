package tk.mwacha.domain.checkout.factory;

import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.checkout.entity.OrderItem;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderFactory {

    public static class OrderFactoryProps {
      public UUID id;
      public UUID customerId;
      public List<OrderItem> orderItem;

        public OrderFactoryProps(UUID id, UUID customerId, List<OrderItem> items) {
            this.id = id;
            this.customerId = customerId;
            this.orderItem = items;
        }
    }

    public static Order create(final OrderFactoryProps orderFactoryProps) {
        final var items = orderFactoryProps.orderItem.stream().map(i -> {
            return new OrderItem(i.getId(), i.getProductId(), i.getName(), i.price(), i.getQuantity());
        }).collect(Collectors.toList());

        return new Order(orderFactoryProps.id, orderFactoryProps.customerId, items);
    }
}
