package tk.mwacha.usecase.product.find;

import tk.mwacha.domain.product.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record OutputFindProductDTO(UUID id,
                                   String name,
                                   BigDecimal price) {

    public static OutputFindProductDTO from(final Product product) {
        return new OutputFindProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
