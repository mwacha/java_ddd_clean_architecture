package tk.mwacha.usecase.customer.find;

import lombok.RequiredArgsConstructor;
import tk.mwacha.domain.customer.repository.CustomerRepositoryInterface;

@RequiredArgsConstructor
public class FindCustomerUseCase {

    private final CustomerRepositoryInterface customerRepository;

    public OutputFindCustomerDTO execute(final InputFindCustomerDTO input) {

        final var customer = customerRepository.find(input.id());

        return OutputFindCustomerDTO.from(customer);

    }
}
