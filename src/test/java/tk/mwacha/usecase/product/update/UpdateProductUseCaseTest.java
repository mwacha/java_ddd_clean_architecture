package tk.mwacha.usecase.product.update;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockProductHelper;
import tk.mwacha.infrastructure.product.gateway.ProductGateway;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UpdateProductUseCaseTest {

    @Autowired
    private ProductGateway gateway;

    @Test
    void should_update_a_Product() {
        final var productMock = MockProductHelper.buildProduct();

        gateway.create(productMock);

        final var useCaseUpdate = new UpdateProductUseCase(gateway);

        productMock.changeName("Product3");
        productMock.changePrice(BigDecimal.valueOf(500));

        final var input = InputUpdateProductDTO.with(productMock.getId(), productMock.getName(),  productMock.getPrice());

        final var output = useCaseUpdate.execute(input);

        assertThat(output.name()).isEqualTo(input.name());
        assertThat(output.price())
                .usingRecursiveComparison()
                .isEqualTo(input.price());

    }
}
