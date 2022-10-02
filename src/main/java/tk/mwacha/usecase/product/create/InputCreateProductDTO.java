package tk.mwacha.usecase.product.create;

import java.math.BigDecimal;

public record InputCreateProductDTO(String name, BigDecimal price) {

    public static InputCreateProductDTO with(final String name, final BigDecimal price) {
        return new InputCreateProductDTO(name, price);
    }
}
