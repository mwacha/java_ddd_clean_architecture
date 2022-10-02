package tk.mwacha.usecase.product.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.domain.product.entity.Product;
import tk.mwacha.domain.product.factory.ProductFactory;
import tk.mwacha.helper.MockProductHelper;
import tk.mwacha.infrastructure.product.gateway.ProductGateway;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CreateProductUseCaseTest {
    @Autowired
    private ProductGateway gateway;

    @Test
    void should_create_a_product() {
        final var productMock = MockProductHelper.buildProduct();

        final var productToSave = ProductFactory.create("a", productMock.getName(), productMock.getPrice());

        final var useCase = new CreateProductUseCase(gateway);

        final var input = InputCreateProductDTO.with(productMock.getName(),  productMock.getPrice());

        final var output = useCase.execute(input);
        final var result = OutputCreateProductDTO.from((Product) productToSave);

        assertThat(output.name()).isEqualTo(result.name());
        assertThat(output.price())
                .usingRecursiveComparison()
                .isEqualTo(result.price());

    }

    @Test
    void should_not_create_a_Product_invalid_name() {
        final var productMock = MockProductHelper.buildProduct();
        ;

        assertThrows(
                RuntimeException.class,
                () -> {
                    productMock.changeName("");
                })
                .getMessage()
                .equals("Name is required");

    }

    @Test
    void should_not_create_a_Product_invalid_price() {
        final var productMock = MockProductHelper.buildProduct();

        assertThrows(
                RuntimeException.class,
                () -> {
                    productMock.changePrice(BigDecimal.ZERO);
                })
                .getMessage()
                .equals("Price is required");

    }

}
