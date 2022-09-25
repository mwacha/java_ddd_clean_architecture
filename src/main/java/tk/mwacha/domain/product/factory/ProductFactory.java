package tk.mwacha.domain.product.factory;

import tk.mwacha.domain.product.entity.Product;
import tk.mwacha.domain.product.entity.ProductB;
import tk.mwacha.domain.product.entity.ProductInterface;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductFactory {

    public static ProductInterface create(final String type, final String name, final BigDecimal price) {
        switch (type) {
            case "a":
                return new Product(UUID.randomUUID(), name, price);
            case "b":
                return new ProductB(UUID.randomUUID(), name, price);
            default:
                throw new RuntimeException("Product type not supported");
        }
    }
}
