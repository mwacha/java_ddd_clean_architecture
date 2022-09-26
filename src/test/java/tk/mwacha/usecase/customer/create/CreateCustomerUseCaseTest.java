package tk.mwacha.usecase.customer.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.domain.customer.factory.CustomerFactory;
import tk.mwacha.helper.MockHelper;
import tk.mwacha.infrastructure.customer.gateway.CustomerGateway;
import tk.mwacha.usecase.customer.find.FindCustomerUseCase;
import tk.mwacha.usecase.customer.find.InputFindCustomerDTO;
import tk.mwacha.usecase.customer.find.OutputFindCustomerDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CreateCustomerUseCaseTest {
    @Autowired
    private CustomerGateway gateway;

    @Test
    void should_create_a_customer() {
        final var customerMock = MockHelper.buildCustomer();

        final var customerToSave = CustomerFactory.createWithAddress(customerMock.getName(), customerMock.getAddress());

        final var useCase = new CreateCustomerUseCase(gateway);

        final var input = InputCreateCustomerDTO.with(customerMock.getName(),  customerMock.getAddress());

        final var output = useCase.execute(input);
        final var result = OutputCreateCustomerDTO.from(customerToSave);

        assertThat(output.name()).isEqualTo(result.name());
        assertThat(output.address())
                .usingRecursiveComparison()
                .isEqualTo(result.address());

    }

}