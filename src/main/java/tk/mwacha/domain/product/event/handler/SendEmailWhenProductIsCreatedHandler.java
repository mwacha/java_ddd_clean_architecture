package tk.mwacha.domain.product.event.handler;

import tk.mwacha.domain.product.event.ProductCreatedEvent;
import tk.mwacha.domain.shared.event.EventHandlerInterface;

public class SendEmailWhenProductIsCreatedHandler implements EventHandlerInterface<ProductCreatedEvent> {
    @Override
    public void handle(ProductCreatedEvent event) {
        System.out.println("Sending email to ....");
    }
}
