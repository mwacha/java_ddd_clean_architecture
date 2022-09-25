package tk.mwacha.domain.checkout.factory;

import org.junit.jupiter.api.Test;
import tk.mwacha.domain.checkout.entity.Order;
import tk.mwacha.domain.checkout.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    @Test
    void should_create_an_order() {
        final var items = List.of(
                OrderItem.builder()
                        .id(UUID.randomUUID())
                        .name("Product 1")
                        .productId(UUID.randomUUID())
                        .price(BigDecimal.TEN)
                        .quantity(1).build());

        final var orderProps = new OrderFactory.OrderFactoryProps(UUID.randomUUID(), UUID.randomUUID(), items);


        final var order = OrderFactory.create(orderProps);

        assertEquals(order.getId(), orderProps.id);
        assertEquals(order.getCustomerId(), orderProps.customerId);
        assertEquals(order.getItems().size(), 1);
    }

}