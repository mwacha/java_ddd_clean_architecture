package tk.mwacha.usecase.product.update;

import tk.mwacha.domain.product.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record OutputUpdateProductDTO(UUID id,
                                     String name,
                                     BigDecimal price) {

    public static OutputUpdateProductDTO from(final Product product) {
        return new OutputUpdateProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
