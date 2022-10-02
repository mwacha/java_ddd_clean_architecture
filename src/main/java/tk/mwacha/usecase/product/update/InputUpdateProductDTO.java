package tk.mwacha.usecase.product.update;

import java.math.BigDecimal;
import java.util.UUID;

public record InputUpdateProductDTO(UUID id, String name, BigDecimal price) {

    public static InputUpdateProductDTO with(final UUID id, final String name, final BigDecimal price) {
        return new InputUpdateProductDTO(id, name, price);
    }
}
