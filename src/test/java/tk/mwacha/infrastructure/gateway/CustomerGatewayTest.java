package tk.mwacha.infrastructure.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.helper.MockHelper;
import tk.mwacha.infrastructure.customer.gateway.CustomerGateway;

@SpringBootTest
@Transactional
class CustomerGatewayTest {

  @Autowired private CustomerGateway gateway;

  @Test
  void should_create_a_customer() {
    final var customerToSave = MockHelper.buildCustomer();

    gateway.create(customerToSave);

    final var customer = gateway.find(customerToSave.getId());

    assertThat(customer.getId()).isEqualTo(customerToSave.getId());
    assertThat(customer.getName()).isEqualTo(customerToSave.getName());
    assertThat(customer.isActive()).isEqualTo(customerToSave.isActive());

    assertThat(customer.getAddress())
        .usingRecursiveComparison()
        .isEqualTo(customerToSave.getAddress());

    assertThat(customer.getRewardPoints()).isEqualTo(customerToSave.getRewardPoints());
  }

  @Test
  void should_update_a_customer() {
    final var customerToSave = MockHelper.buildCustomer();

    gateway.create(customerToSave);

    final var customerModel = gateway.find(customerToSave.getId());

    final var customerPersisted = customerModel;

    customerPersisted.changeName("Teste 2");

    gateway.update(customerPersisted);

    final var customerModelUpdated = gateway.find(customerPersisted.getId());

    assertThat(customerModelUpdated.getId()).isEqualTo(customerPersisted.getId());
    assertThat(customerModelUpdated.getName()).isEqualTo(customerPersisted.getName());
    assertThat(customerModelUpdated.isActive()).isEqualTo(customerPersisted.isActive());

    assertThat(customerModelUpdated.getAddress())
        .usingRecursiveComparison()
        .isEqualTo(customerPersisted.getAddress());

    assertThat(customerModelUpdated.getRewardPoints())
        .isEqualTo(customerPersisted.getRewardPoints());
  }

  @Test
  void should_find_a_customer() {

    final var customer = MockHelper.buildCustomer();

    gateway.create(customer);

    final var customerResult = gateway.find(customer.getId());

    assertThat(customer).usingRecursiveComparison().ignoringFields("id").isEqualTo(customerResult);
  }

  @Test
  void should_throw_an_error_when_find_a_customer() {

    final var customer = MockHelper.buildCustomer();

    gateway.create(customer);

    Assertions.assertThrows(RuntimeException.class, () -> gateway.find(UUID.randomUUID()));
  }

  @Test
  void should_find_all_customer() {

    final var customer = MockHelper.buildCustomer();

    final var customer2 = MockHelper.buildCustomer();
    customer2.changeName("Test2");
    final var customers = List.of(customer, customer2);

    gateway.createAll(customers);

    final var foundCustomers = gateway.findAll();
    assertThat(foundCustomers).hasSize(2);
    assertTrue(foundCustomers.contains(customer));
    assertTrue(foundCustomers.contains(customer2));
  }
}
