package tk.mwacha.domain.product.factory;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductFactoryTest {

    @Test
    void should_create_a_product_type_A() {
        var product = ProductFactory.create("a", "Product A", BigDecimal.ONE);

        assertNotNull(product.getId());
        assertEquals(product.getName(), "Product A");
        assertEquals(product.getPrice(), BigDecimal.ONE);
        assertEquals(product.getClass().getSimpleName(), "Product");

    }

    @Test
    void should_create_a_product_type_B() {
        var product = ProductFactory.create("b", "Product B", BigDecimal.ONE);

        assertNotNull(product.getId());
        assertEquals(product.getName(), "Product B");
        assertEquals(product.getPrice(), BigDecimal.valueOf(2));
        assertEquals(product.getClass().getSimpleName(), "ProductB");

    }

    @Test
    void should_create_a_product_type_C() {
        assertThatThrownBy(() -> ProductFactory.create("c", "Product C", BigDecimal.ONE))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Product type not supported");
    }
}