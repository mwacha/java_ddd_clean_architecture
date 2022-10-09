package tk.mwacha.domain.customer.entity;

import java.util.Optional;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import tk.mwacha.domain.customer.factory.CustomerValidatorFactory;
import tk.mwacha.domain.customer.valueobject.Address;
import tk.mwacha.domain.shared.AbstractEntity;
import tk.mwacha.domain.shared.exception.ErrorException;
import tk.mwacha.domain.shared.notification.Error;

@Getter
@EqualsAndHashCode
public class Customer extends AbstractEntity {

  private String name;
  private Address address;
  private boolean active;

  private int rewardPoints;

  public Customer(final UUID id, final String name) {
    super();
    this.id = id;
    this.name = name;
    this.validate();

    if (this.notification.hasErrors()) {
      throw new ErrorException(this.notification.getErrors());
    }
  }

  public void changeName(final String name) {
    this.name = name;
    this.validate();

    if (this.notification.hasErrors()) {
      throw new ErrorException(this.notification.getErrors());
    }
  }

  public void changeAddress(final Address address) {
    this.address = address;
  }

  public void activate() {
    if (this.address == null) {
      throw new RuntimeException("Address is required to activate a customer");
    }
    this.active = true;
  }

  public void deactivate() {
    this.active = false;
  }

  public void addRewardPoints(final int points) {
    this.rewardPoints += points;
  }

  private void validate() {
    CustomerValidatorFactory.create().validate(this);
  }
}
