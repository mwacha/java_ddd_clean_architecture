package tk.mwacha.domain.customer.valueobject;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Address {

  private String street;
  private int number;
  private String zip;
  private String city;

  public Address(final String street, final int number, final String zip, final String city) {
    this.street = street;
    this.number = number;
    this.zip = zip;
    this.city = city;

    this.validate();
  }

  private void validate() {
    if (this.street == null || this.street.isEmpty()) {
      throw new RuntimeException("Street is required");
    }

    if (this.number == 0) {
      throw new RuntimeException("Number is required");
    }

    if (this.zip == null || this.zip.isEmpty()) {
      throw new RuntimeException("Zip is required");
    }

    if (this.city == null || this.city.isEmpty()) {
      throw new RuntimeException("City is required");
    }
  }
}
