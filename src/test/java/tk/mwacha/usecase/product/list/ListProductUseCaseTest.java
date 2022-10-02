package tk.mwacha.usecase.product.list;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockProductHelper;
import tk.mwacha.infrastructure.product.gateway.ProductGateway;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ListProductUseCaseTest {

    @Autowired
    private ProductGateway gateway;


    @Test
    void should_list_a_product() {
        final var products = MockProductHelper.buildProducts();

        final var useCase = new ListProductUseCase(gateway);

        gateway.createAll(products);
        final var input = InputListProductDTO.with();


        final var output = useCase.execute(input);

        assertEquals(output.products().size(), 2);


    }

}
