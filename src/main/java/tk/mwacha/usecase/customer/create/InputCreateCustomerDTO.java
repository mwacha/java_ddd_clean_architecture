package tk.mwacha.usecase.customer.create;

import tk.mwacha.domain.customer.valueobject.Address;

public record InputCreateCustomerDTO(String name, Address address) {

    public static InputCreateCustomerDTO with(final String name, final Address address) {
        return new InputCreateCustomerDTO(name, address);
    }
}
