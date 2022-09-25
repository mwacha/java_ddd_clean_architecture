package tk.mwacha.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;

class CustomerTest {

  @Test
  void should_change_name_is_valid() {
    final var customer = new Customer(UUID.randomUUID(), "Marcelo");
    customer.changeName("Wacha");

    assertEquals(customer.getName(), "Wacha");
  }

  @Test
  void should_throw_error_when_id_is_null() {

    assertThrows(
            RuntimeException.class,
            () -> {
              new Customer(null, "Marcelo");
            })
        .getMessage()
        .equals("Id is required");
  }

  @Test
  void should_throw_error_when_name_is_null() {

    assertThrows(
            RuntimeException.class,
            () -> {
              new Customer(UUID.randomUUID(), "");
            })
        .getMessage()
        .equals("Name is required");
  }

  @Test
  void should_throw_error_when_change_name_is_invalid() {
    final var customer = new Customer(UUID.randomUUID(), "Marcelo");

    assertThrows(
            RuntimeException.class,
            () -> {
              customer.changeName("");
            })
        .getMessage()
        .equals("Name is required");
  }

  @Test
  void should_activate_customer() {
    final var customer = new Customer(UUID.randomUUID(), "Marcelo");
    final var address = new Address("Rua um", 10, "12345-678", "Santa Catarina");
    customer.changeAddress(address);

    customer.activate();

    assertTrue(customer.isActive());
  }

  @Test
  void should_throw_error_when_activate_customer_address_undefined() {
    final var customer = new Customer(UUID.randomUUID(), "Marcelo");

    assertThrows(
            RuntimeException.class,
            () -> {
              customer.activate();
            })
        .getMessage()
        .equals("Address is required to activate a customer");
  }

  @Test
  void should_deactivate_customer() {
    final var customer = new Customer(UUID.randomUUID(), "Marcelo");

    customer.deactivate();

    assertFalse(customer.isActive());
  }

  @Test
  void should_add_reward_points() {
    final var customer = new Customer(UUID.randomUUID(), "Marcelo");

    assertEquals(customer.getRewardPoints(), 0);

    customer.addRewardPoints(10);

    assertEquals(customer.getRewardPoints(), 10);

    customer.addRewardPoints(10);

    assertEquals(customer.getRewardPoints(), 20);
  }
}
