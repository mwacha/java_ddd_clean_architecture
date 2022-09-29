package tk.mwacha.usecase.customer.list;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockHelper;
import tk.mwacha.infrastructure.customer.gateway.CustomerGateway;
import tk.mwacha.usecase.customer.find.InputFindCustomerDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ListCustomerUseCaseTest {

    @Autowired
    private CustomerGateway gateway;


    @Test
    void should_list_a_customer() {
        final var customers = MockHelper.buildCustomers();

        final var useCase = new ListCustomerUseCase(gateway);

        gateway.createAll(customers);
        final var input = InputListCustomerDTO.with();


        final var output = useCase.execute(input);

        assertEquals(output.customers().size(), 2);


    }

}