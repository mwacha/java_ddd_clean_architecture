package tk.mwacha.domain.event.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.mwacha.domain.product.entity.Product;
import tk.mwacha.domain.product.event.ProductCreatedEvent;
import tk.mwacha.domain.product.event.handler.SendEmailWhenProductIsCreatedHandler;
import tk.mwacha.domain.shared.event.EventDispatcher;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class EventDispatcherTest {

    @Mock
    private SendEmailWhenProductIsCreatedHandler eventHandler;
    @Test
    void should_register_event_handler() {
        final var eventDispatcher = new EventDispatcher();


        eventDispatcher.register("ProductCreatedEvent", eventHandler);

        assertTrue(eventDispatcher.getEventHandlers().containsKey("ProductCreatedEvent"));
        assertTrue(eventDispatcher.getEventHandlers().containsValue(eventHandler));
        assertEquals(eventDispatcher.getEventHandlers().size(), 1);
    }


    @Test
    void should_unregister_event_handler() {
        final var eventDispatcher = new EventDispatcher();


        eventDispatcher.register("ProductCreatedEvent", eventHandler);

        assertTrue(eventDispatcher.getEventHandlers().containsValue(eventHandler));

        eventDispatcher.unregister("ProductCreatedEvent", eventHandler);

        assertFalse(eventDispatcher.getEventHandlers().containsKey("ProductCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers().containsValue(eventHandler));
        assertEquals(eventDispatcher.getEventHandlers().size(), 0);
    }


    @Test
    void should_unregister_all_event_handler() {
        final var eventDispatcher = new EventDispatcher();


        eventDispatcher.register("ProductCreatedEvent", eventHandler);

        assertTrue(eventDispatcher.getEventHandlers().containsValue(eventHandler));

        eventDispatcher.unregisterAll();

        assertFalse(eventDispatcher.getEventHandlers().containsKey("ProductCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers().containsValue(eventHandler));
        assertEquals(eventDispatcher.getEventHandlers().size(), 0);
    }

    @Test
    void should_notify_all_event_handler() {
        final var eventDispatcher = new EventDispatcher();

        final var spyEventHandler = Mockito.spy(eventHandler);

        eventDispatcher.register("ProductCreatedEvent", eventHandler);

        assertTrue(eventDispatcher.getEventHandlers().containsValue(eventHandler));

        final var productCreatedEvent = new ProductCreatedEvent(
                Product.builder()
                        .id(UUID.randomUUID())
                        .name("Product 1")
                        .price(BigDecimal.TEN)
                        .build());

        eventDispatcher.notify(productCreatedEvent);

        then(eventHandler).should(Mockito.times(1)).handle(productCreatedEvent);
    }
}