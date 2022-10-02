package tk.mwacha.usecase.customer.update;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.domain.customer.valueobject.Address;
import tk.mwacha.helper.MockCustomerHelper;
import tk.mwacha.infrastructure.customer.gateway.CustomerGateway;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UpdateCustomerUseCaseTest {

    @Autowired
    private CustomerGateway gateway;

    @Test
    void should_update_a_customer() {
        final var customerMock = MockCustomerHelper.buildCustomer();

        gateway.create(customerMock);

        final var useCaseUpdate = new UpdateCustomerUseCase(gateway);

        customerMock.changeName("Marcelo Wacha Ferreira");
        customerMock.changeAddress(
                Address.builder()
                        .street("Rua tal")
                        .number(1211)
                        .zip("Zipcode2")
                        .city("City2")
                        .build());

        final var input = InputUpdateCustomerDTO.with(customerMock.getId(), customerMock.getName(),  customerMock.getAddress());

        final var output = useCaseUpdate.execute(input);

        assertThat(output.name()).isEqualTo(input.name());
        assertThat(output.address())
                .usingRecursiveComparison()
                .isEqualTo(input.address());

    }
}
