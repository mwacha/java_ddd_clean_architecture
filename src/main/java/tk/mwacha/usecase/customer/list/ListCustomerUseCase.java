package tk.mwacha.usecase.customer.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mwacha.domain.customer.repository.CustomerRepositoryInterface;

@Service
@RequiredArgsConstructor
public class ListCustomerUseCase {

    private final CustomerRepositoryInterface customerRepository;

    public OutputListCustomerDTO execute(final InputListCustomerDTO input) {

        final var customer = customerRepository.findAll();

        return OutputListCustomerDTO.from(customer);
    }
}
