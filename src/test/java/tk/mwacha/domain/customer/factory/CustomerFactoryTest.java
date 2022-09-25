package tk.mwacha.domain.customer.factory;

import org.junit.jupiter.api.Test;
import tk.mwacha.domain.customer.valueobject.Address;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    @Test
    void should_create_a_customer() {
        final var customer = CustomerFactory.create("John");

        assertNotNull(customer.getId());
        assertEquals(customer.getName(), "John");
        assertNull(customer.getAddress());
    }

    @Test
    void should_create_a_customer_with_an_address() {
        final var address = Address.builder()
                .street("Rua tal")
                .city("Joinville")
                .zip("123456")
                .number(1)
                .build();

        final var customer = CustomerFactory.createWithAddress("John", address);


        assertNotNull(customer.getId());
        assertEquals(customer.getName(), "John");
        assertNotNull(customer.getAddress());
        assertEquals(customer.getAddress(), address);
    }

}