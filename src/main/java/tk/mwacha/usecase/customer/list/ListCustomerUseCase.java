package tk.mwacha.usecase.customer.list;

import lombok.RequiredArgsConstructor;
import tk.mwacha.domain.customer.repository.CustomerRepositoryInterface;

@RequiredArgsConstructor
public class ListCustomerUseCase {

    private final CustomerRepositoryInterface customerRepository;

    public OutputListCustomerDTO execute(final InputListCustomerDTO input) {

        final var customer = customerRepository.findAll();

        return OutputListCustomerDTO.from(customer);
    }
}
