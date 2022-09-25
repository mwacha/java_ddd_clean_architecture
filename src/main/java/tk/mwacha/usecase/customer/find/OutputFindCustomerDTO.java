package tk.mwacha.usecase.customer.find;

import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;

import java.util.UUID;

public record OutputFindCustomerDTO(UUID id,
                                    String name,
                                    Address address) {

    public static OutputFindCustomerDTO from(final Customer customer) {
        return new OutputFindCustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }
}
