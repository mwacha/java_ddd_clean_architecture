package tk.mwacha.usecase.product.find;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockHelper;
import tk.mwacha.infrastructure.product.gateway.ProductGateway;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FindProductUseCaseTest {

    @Autowired
    private ProductGateway gateway;


    @Test
    void should_find_a_product() {
        final var productToSave = MockHelper.buildProduct();
        final var useCase = new FindProductUseCase(gateway);

        gateway.create(productToSave);

        final var input = InputFindProductDTO.with(productToSave.getId());

        final var output = OutputFindProductDTO.from(productToSave);

        final var result = useCase.execute(input);

        assertThat(output)
                .usingRecursiveComparison()
                .isEqualTo(result);
    }

    @Test
    void should_not_find_a_product() {
        final var productToSave = MockHelper.buildProduct();
        final var useCase = new FindProductUseCase(gateway);

        gateway.create(productToSave);

        final var input = InputFindProductDTO.with(UUID.randomUUID());

        Assertions.assertThrows(RuntimeException.class, () -> useCase.execute(input));
    }

}
