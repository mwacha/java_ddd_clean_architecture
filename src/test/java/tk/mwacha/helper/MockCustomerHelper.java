package tk.mwacha.helper;

import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;

import java.util.List;
import java.util.UUID;

public final class MockCustomerHelper {
  public static Customer buildCustomer() {
    var customer = new Customer(UUID.randomUUID(), "Customer");
    customer.changeAddress(builderAddress());
    customer.addRewardPoints(1);
    customer.activate();
    return customer;
  }

  public static List<Customer> buildCustomers() {
    var customer1 = new Customer(UUID.randomUUID(), "Customer");
    customer1.changeAddress(builderAddress());
    customer1.addRewardPoints(1);
    customer1.activate();

    var customer2 = new Customer(UUID.randomUUID(), "Customer2");
    customer2.changeAddress(builderAddress2());
    customer2.addRewardPoints(2);
    customer2.activate();

    return List.of(customer1, customer2);
  }

  public static Address builderAddress() {
    return Address.builder().street("Street1").number(1).zip("Zipcode1").city("City1").build();
  }
  public static Address builderAddress2() {
    return Address.builder().street("Street2").number(1).zip("Zipcode2").city("City2").build();
  }

}
