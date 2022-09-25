package tk.mwacha.domain.product.entity;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductInterface {

    UUID getId();
    String getName();
    BigDecimal getPrice();
}
