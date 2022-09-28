package tk.mwacha.usecase.customer.update;

import lombok.EqualsAndHashCode;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;

import java.util.UUID;

public record OutputUpdateCustomerDTO(UUID id,
                                      String name,
                                      Address address) {

    public static OutputUpdateCustomerDTO from(final Customer customer) {
        return new OutputUpdateCustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }
}
