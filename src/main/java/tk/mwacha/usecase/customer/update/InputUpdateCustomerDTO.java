package tk.mwacha.usecase.customer.update;

import tk.mwacha.domain.customer.valueobject.Address;

import java.util.UUID;

public record InputUpdateCustomerDTO(UUID id, String name, Address address) {

    public static InputUpdateCustomerDTO with(final UUID id, final String name, final Address address) {
        return new InputUpdateCustomerDTO(id, name, address);
    }
}
