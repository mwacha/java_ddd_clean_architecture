package tk.mwacha.domain.customer.factory;

import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;

import java.util.UUID;

public class CustomerFactory {

    public static Customer create(final String name) {
        return new Customer(UUID.randomUUID(), name);
    }

    public static Customer createWithAddress(final String name, final Address address) {
        final var customer = new Customer(UUID.randomUUID(), name);
        customer.changeAddress(address);

        return customer;
    }
}
