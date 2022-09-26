package tk.mwacha.usecase.customer.create;

import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;

import java.util.UUID;

public record OutputCreateCustomerDTO(UUID id,
                                      String name,
                                      Address address) {

    public static OutputCreateCustomerDTO from(final Customer customer) {
        return new OutputCreateCustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }
}
