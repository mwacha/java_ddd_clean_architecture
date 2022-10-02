package tk.mwacha.usecase.customer.find;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockCustomerHelper;
import tk.mwacha.infrastructure.customer.gateway.CustomerGateway;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FindCustomerUseCaseTest {

    @Autowired
    private CustomerGateway gateway;


    @Test
    void should_find_a_customer() {
        final var customerToSave = MockCustomerHelper.buildCustomer();
        final var useCase = new FindCustomerUseCase(gateway);

        gateway.create(customerToSave);

        final var input = InputFindCustomerDTO.with(customerToSave.getId());

        final var output = OutputFindCustomerDTO.from(customerToSave);

        final var result = useCase.execute(input);

        assertThat(output)
                .usingRecursiveComparison()
                .isEqualTo(result);
    }

    @Test
    void should_not_find_a_customer() {
        final var customerToSave = MockCustomerHelper.buildCustomer();
        final var useCase = new FindCustomerUseCase(gateway);

        gateway.create(customerToSave);

        final var input = InputFindCustomerDTO.with(UUID.randomUUID());

        Assertions.assertThrows(RuntimeException.class, () -> useCase.execute(input));
    }

}