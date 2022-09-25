package tk.mwacha.domain.product.event;

import tk.mwacha.domain.shared.event.EventInterface;

import java.time.LocalDateTime;


public class ProductCreatedEvent implements EventInterface {
    private LocalDateTime dateTimeOccurred;
    private Object eventData;

    public ProductCreatedEvent(Object event){
        dateTimeOccurred = LocalDateTime.now();
        eventData = event;
    }
}
