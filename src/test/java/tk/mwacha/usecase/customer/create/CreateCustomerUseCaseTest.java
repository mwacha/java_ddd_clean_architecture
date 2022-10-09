package tk.mwacha.usecase.customer.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.domain.customer.factory.CustomerFactory;
import tk.mwacha.domain.customer.valueobject.Address;
import tk.mwacha.domain.shared.exception.ErrorException;
import tk.mwacha.helper.MockCustomerHelper;
import tk.mwacha.infrastructure.customer.gateway.CustomerGateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CreateCustomerUseCaseTest {
    @Autowired
    private CustomerGateway gateway;

    @Test
    void should_create_a_customer() {
        final var customerMock = MockCustomerHelper.buildCustomer();

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

    @Test
    void should_not_create_a_customer_invalid_name() {
        final var customerMock = MockCustomerHelper.buildCustomer();

        assertThrows(
                ErrorException.class,
                () -> {
                    customerMock.changeName("");
                })
                .getMessage()
                .equals("customer: Name is required");

    }

    @Test
    void should_not_create_a_customer_invalid_address() {
        final var customerMock = MockCustomerHelper.buildCustomer();

        assertThrows(
                RuntimeException.class,
                () -> {
                    customerMock.changeAddress(
                            Address.builder()
                                    .street("")
                                    .number(1)
                                    .zip("Zipcode1")
                                    .city("City1")
                                    .build());
                })
                .getMessage()
                .equals("Street is required");

    }

}
