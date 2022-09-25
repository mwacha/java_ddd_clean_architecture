package tk.mwacha.infrastructure.customer.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tk.mwacha.domain.customer.entity.Customer;
import tk.mwacha.domain.customer.valueobject.Address;

@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class CustomerModel {

  @Id private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private int number;

  @Column(nullable = false)
  private String zipcode;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private boolean active;

  @Column(nullable = false)
  private int rewardPoints;

  public static Customer to(CustomerModel model) {
    var customer = new Customer(model.id, model.name);
    customer.changeAddress(
        Address.builder()
            .city(model.city)
            .number(model.number)
            .street(model.street)
            .zip(model.zipcode)
            .build());

    if (model.active) {
      customer.activate();
    }

    customer.addRewardPoints(model.rewardPoints);

    return customer;
  }

  public static CustomerModel of(Customer customer) {
    return CustomerModel.builder()
        .id(customer.getId())
        .name(customer.getName())
        .city(customer.getAddress().getCity())
        .number(customer.getAddress().getNumber())
        .street(customer.getAddress().getStreet())
        .zipcode(customer.getAddress().getZip())
        .active(customer.isActive())
        .rewardPoints(customer.getRewardPoints())
        .build();
  }
}
