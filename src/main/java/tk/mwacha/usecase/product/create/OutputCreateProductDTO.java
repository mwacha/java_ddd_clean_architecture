package tk.mwacha.usecase.product.create;

import tk.mwacha.domain.product.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record OutputCreateProductDTO(UUID id,
                                     String name,
                                     BigDecimal price) {

    public static OutputCreateProductDTO from(final Product product) {
        return new OutputCreateProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
