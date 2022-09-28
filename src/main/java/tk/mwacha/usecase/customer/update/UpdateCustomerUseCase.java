package tk.mwacha.usecase.customer.update;

import lombok.RequiredArgsConstructor;
import tk.mwacha.domain.customer.factory.CustomerFactory;
import tk.mwacha.domain.customer.repository.CustomerRepositoryInterface;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerRepositoryInterface customerRepository;

    public OutputUpdateCustomerDTO execute(final InputUpdateCustomerDTO input) {


        final var customer = Optional.of(customerRepository.find(input.id())).orElseThrow();

        customer.changeName(input.name());
        customer.changeAddress(input.address());

        customerRepository.update(customer);

        return OutputUpdateCustomerDTO.from(customer);
    }
}
