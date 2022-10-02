package tk.mwacha.usecase.customer.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mwacha.domain.customer.factory.CustomerFactory;
import tk.mwacha.domain.customer.repository.CustomerRepositoryInterface;

@RequiredArgsConstructor
@Service
public class CreateCustomerUseCase {

    private final CustomerRepositoryInterface customerRepository;

    public OutputCreateCustomerDTO execute(final InputCreateCustomerDTO input) {

        final var customer = CustomerFactory.createWithAddress(input.name(), input.address());

        customerRepository.create(customer);

        return OutputCreateCustomerDTO.from(customer);
    }
}
